package nl.jjkester.adventofcode21.day01

import nl.jjkester.adventofcode21.boilerplate.*

object Day01 : Day {
    override val builder = day(1) {
        val values by input("sonar.txt") {
            lineSeparated()
                .notEmpty()
                .ints()
        }

        part {
            answer("Number of increasing individual depth measurements") {
                individualIncreasing(values)
            }
        }

        part {
            answer("Number of increasing groups of 3 depth measurements") {
                windowOfThreeIncreasing(values)
            }
        }
    }

    fun individualIncreasing(values: List<Int>) = values.asSequence()
        .windowedPairs()
        .count { it.isIncreasing() }

    fun windowOfThreeIncreasing(values: List<Int>) = values.asSequence()
        .windowed(3)
        .map { it.sum() }
        .windowedPairs()
        .count { it.isIncreasing() }

    private fun <T> Sequence<T>.windowedPairs() = this.windowed(2).map { it[0] to it[1] }

    private fun Pair<Int, Int>.isIncreasing() = first < second

    @JvmStatic
    fun main(args: Array<String>) {
        main()
    }
}
