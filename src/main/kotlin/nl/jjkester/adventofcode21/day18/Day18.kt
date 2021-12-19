package nl.jjkester.adventofcode21.day18

import nl.jjkester.adventofcode21.boilerplate.*

object Day18 : Day {
    override val builder = day(18) {
        val numbers by input("numbers.txt") {
            lineSeparated().notBlank().map { SnailfishNumber.parse(it.string()) }
        }

        part {
            answer("Magnitude of final sum") {
                magnitudeOfFinalSum(numbers)
            }
        }
        part {
            answer("Larged magnitude adding any two numbers") {
                largestMagnitudeAddingTwo(numbers)
            }
        }
    }

    fun magnitudeOfFinalSum(numbers: List<SnailfishNumber>): Int {
        return computeFinalSum(numbers).magnitude
    }

    fun largestMagnitudeAddingTwo(numbers: List<SnailfishNumber>): Int {
        val combinations = sequence {
            numbers.forEach { first ->
                numbers.forEach { second ->
                    if (first != second) {
                        yield(first to second)
                    }
                }
            }
        }

        return combinations.maxOf { (first, second) -> (first + second).magnitude }
    }

    fun computeFinalSum(numbers: List<SnailfishNumber>): SnailfishNumber {
        return numbers.reduce { first, second -> first + second }
    }
}
