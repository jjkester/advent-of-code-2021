package nl.jjkester.adventofcode21.day11

import nl.jjkester.adventofcode21.boilerplate.*
import java.lang.Integer.max
import java.lang.Integer.min

object Day11 : Day {
    override val builder = day(11) {
        val initial by input("matrix.txt") {
            lineSeparated()
                .notBlank()
                .map { it.charSeparated().ints() }
        }

        part {
            answer("Total flashes after 100 steps") {
                totalFlashesInSteps(initial, 100)
            }
        }
        part {
            answer("First step where flashing is synchronized") {
                firstSynchronizedStep(initial)
            }
        }
    }

    fun totalFlashesInSteps(initial: List<List<Int>>, steps: Int): Int {
        val state = mutableStateFrom(initial)

        return (0 until steps).sumOf {
            step(state, initial.sizeX, initial.sizeY)
        }
    }

    fun firstSynchronizedStep(initial: List<List<Int>>): Int {
        val state = mutableStateFrom(initial)
        var step = 1

        while (step(state, initial.sizeX, initial.sizeY) < (initial.sizeX * initial.sizeY)) {
            step += 1
        }

        return step;
    }

    private fun mutableStateFrom(initial: List<List<Int>>) = initial
        .flatMapIndexed { y, row ->
            row.mapIndexed { x, it ->
                (x to y) to it
            }
        }
        .toMap()
        .toMutableMap()

    private fun step(
        state: MutableMap<Pair<Int, Int>, Int>,
        sizeX: Int,
        sizeY: Int
    ): Int {
        var flashes = 0

        // Increment energy level
        state.forEach { (index, it) -> state[index] = it + 1 }

        // Find new flashing
        val nowFlashing = state.mapNotNull { (index, it) ->
            if (it > 9) index else null
        }.toMutableList()

        while (nowFlashing.isNotEmpty()) {
            nowFlashing.toList().forEach { index ->
                nowFlashing.remove(index)

                // Record flashing
                flashes += 1

                // Set energy level of flashing to 0
                state[index] = 0

                // For each neighbouring cell
                (max(0, index.first - 1)..min(index.first + 1, sizeX - 1)).forEach { x ->
                    (max(0, index.second - 1)..min(index.second + 1, sizeY - 1)).forEach { y ->
                        val key = x to y
                        val value = state[key]!!

                        // Increment if not already flashing
                        if (value > 0) {
                            state[key] = value + 1
                            if (value == 9) {
                                nowFlashing.add(key)
                            }
                        }
                    }
                }
            }
        }

        return flashes
    }

    private val List<List<Int>>.sizeX get() = firstOrNull()?.size ?: 0
    private val List<List<Int>>.sizeY get() = size

    @JvmStatic
    fun main(args: Array<String>) {
        Day11.main()
    }
}
