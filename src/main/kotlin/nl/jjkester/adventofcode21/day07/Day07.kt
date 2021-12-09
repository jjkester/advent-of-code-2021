package nl.jjkester.adventofcode21.day07

import nl.jjkester.adventofcode21.boilerplate.*
import kotlin.math.absoluteValue

object Day07 : Day {
    override val builder = day(7) {
        val initial by input("crabs.txt") {
            commaSeparated()
                .ints()
        }

        part {
            answer("Lowest alignment cost assuming linear burn") {
                cheapestPositionLinearCost(initial)
            }
        }

        part {
            answer("Lowest alignment cost assuming increasing burn") {
                cheapestPositionIncreasingCost(initial)
            }
        }
    }

    fun cheapestPositionLinearCost(initial: List<Int>): Int = initial.positions()
        .minOf { target -> initial.sumOf { (target - it).absoluteValue } }

    fun cheapestPositionIncreasingCost(initial: List<Int>): Int = initial.positions()
        .minOf { target ->
            initial.map { (target - it).absoluteValue }
                .sumOf { it * (it + 1) / 2 }
        }

    private fun List<Int>.positions() = 1..(maxOrNull() ?: 0)

    @JvmStatic
    fun main(args: Array<String>) {
        Day07.main()
    }
}
