package nl.jjkester.adventofcode21.day09

import nl.jjkester.adventofcode21.boilerplate.*

object Day09 : Day {
    override val builder = day(9) {
        val heightmap by input("heightmap.txt") {
            lineSeparated()
                .notBlank()
                .map { it.charSeparated().ints() }
                .let { Heightmap(it) }
        }

        part {
            answer("Sum of risk levels of all low points") {
                sumOfRiskLevelsOfLowPoints(heightmap)
            }
        }
        part {
            answer("Product of sizes of three largest basins") {
                productOfSizesOfThreeLargestBasins(heightmap)
            }
        }
    }

    fun sumOfRiskLevelsOfLowPoints(heightmap: Heightmap): Int = findLowPoints(heightmap)
        .map { requireNotNull(heightmap[it]) }
        .sumOf { it + 1 }

    fun productOfSizesOfThreeLargestBasins(heightmap: Heightmap): Int = findBasins(heightmap)
        .map { it.size }
        .sortedDescending()
        .take(3)
        .reduce(Int::times)

    fun findLowPoints(heightmap: Heightmap): List<Pair<Int, Int>> = heightmap.mapNotNull { (x, y) ->
        val it = requireNotNull(heightmap[x to y])
        val left = heightmap[x - 1 to y] ?: 10
        val right = heightmap[x + 1 to y] ?: 10
        val top = heightmap[x to y - 1] ?: 10
        val bottom = heightmap[x to y + 1] ?: 10

        if (left > it && right > it && top > it && bottom > it) {
            x to y
        } else {
            null
        }
    }

    fun findBasins(heightmap: Heightmap): List<Set<Pair<Int, Int>>> {

        return findLowPoints(heightmap)
            .map { lowPoint ->
                val seen = mutableSetOf<Pair<Int, Int>>()
                val candidates = mutableListOf(lowPoint)

                while (candidates.isNotEmpty()) {
                    val (x, y) = candidates.removeLast()

                    setOf(x - 1 to y, x + 1 to y, x to y - 1, x to y + 1)
                        .filter { (heightmap[it] ?: 10) < 9 }
                        .filter { !seen.contains(it) }
                        .run(candidates::addAll)

                    seen.add(x to y)
                }

                seen
            }
            .also {
                require(it.flatten().distinct().size == it.flatten().size) { "There are overlapping basins" }
            }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        Day09.main()
    }
}
