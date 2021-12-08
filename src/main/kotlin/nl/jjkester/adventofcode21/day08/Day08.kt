package nl.jjkester.adventofcode21.day08

import nl.jjkester.adventofcode21.boilerplate.*

object Day08 : D {
    override val day = day(8) {
        val values by input("digits.txt") {
            lineSeparated()
                .notBlank()
                .map { line ->
                    line.pipeSeparated()
                        .map { it.whitespaceSeparated().strings() }
                        .toPair()
                }
        }

        part {
            answer("Number of digits 1, 4, 7 and 8") {
                countOfOneFourSevenEightDigits(values)
            }
        }
        part {
            answer("Sum of all output values") {
                sumOfAllDigits(values)
            }
        }
    }

    fun countOfOneFourSevenEightDigits(values: List<Pair<List<String>, List<String>>>): Int = values.map { it.second }
        .sumOf { sequence ->
            sequence.map { str -> str.length }
                .count { it == 2 || it == 4 || it == 3 || it == 7 }
        }

    fun sumOfAllDigits(values: List<Pair<List<String>, List<String>>>): Int = values.sumOf { line ->
        val uniqueSequences = line.toList()
            .flatMap { digits ->
                digits.map { it.toSet() }
            }
            .distinct()

        require(uniqueSequences.size == 10)

        val one = uniqueSequences.selectDigit { it.size == 2 }
        val four = uniqueSequences.selectDigit { it.size == 4 }
        val seven = uniqueSequences.selectDigit { it.size == 3 }
        val eight = uniqueSequences.selectDigit { it.size == 7 }

        val nine = uniqueSequences.selectDigit { it.size == 6 && it.containsAll(four) }
        val zero = uniqueSequences.selectDigit { it.size == 6 && it != nine && it.containsAll(one) }
        val six = uniqueSequences.selectDigit { it.size == 6 && it != nine && it != zero }
        val three = uniqueSequences.selectDigit { it.size == 5 && it.containsAll(one) }
        val five = uniqueSequences.selectDigit { it.size == 5 && it != three && it.containsAll(six.intersect(one)) }
        val two = uniqueSequences.selectDigit { it.size == 5 && it != three && it != five }

        val lookup = mapOf(
            zero to 0,
            one to 1,
            two to 2,
            three to 3,
            four to 4,
            five to 5,
            six to 6,
            seven to 7,
            eight to 8,
            nine to 9
        )

        line.second.joinToString("") { requireNotNull(lookup[it.toSet()]).toString() }
            .toInt()
    }

    fun List<Set<Char>>.selectDigit(filter: (Set<Char>) -> Boolean) = requireNotNull(filter(filter).also { require(it.size == 1) }.first())

    @JvmStatic
    fun main(args: Array<String>) {
        Day08.main()
    }
}
