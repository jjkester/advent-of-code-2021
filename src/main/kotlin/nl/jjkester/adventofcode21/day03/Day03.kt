package nl.jjkester.adventofcode21.day03

import nl.jjkester.adventofcode21.boilerplate.*
import kotlin.math.pow

object Day03 : D {
    override val day = day(3) {
        val values by input("report.txt") {
            lineSeparated()
                .notEmpty()
                .strings()
        }

        part {
            answer("Power consumption") {
                powerConsumption(values)
            }
        }

        part {
            answer("Life support rating") {
                lifeSupportRating(values)
            }
        }
    }

    fun powerConsumption(report: List<String>): Int {
        val oneIsCommon = commonBits(report)

        val gamma = oneIsCommon
            .map { it.toInt() }
            .binaryToInt()

        val mask = 2.0.pow(report.maxLength()).toInt() - 1

        val epsilon = gamma xor mask

        return gamma * epsilon
    }

    fun lifeSupportRating(report: List<String>): Int {
        return oxygenGeneratorRating(report) * co2ScrubberRating(report)
    }

    fun oxygenGeneratorRating(report: List<String>): Int {
        val numbers = report.toMutableList()
        for (i in 0 until report.maxLength()) {
            val commonBits = commonBits(numbers)
            numbers.removeAll { it[i].digitToIntOrNull() != (commonBits[i]).toInt() }
            if (numbers.size == 1) break
        }
        return numbers.first().toInt(2)
    }

    fun co2ScrubberRating(report: List<String>): Int {
        val numbers = report.toMutableList()
        for (i in 0 until report.maxLength()) {
            val commonBits = commonBits(numbers)
            numbers.removeAll { it[i].digitToIntOrNull() == (commonBits[i]).toInt() }
            if (numbers.size == 1) break
        }
        return numbers.first().toInt(2)
    }

    private fun commonBits(report: List<String>) = report.binaryToInt()
        .reduce { first, second ->
            first.zip(second) { a, b -> a + b }
        }
        .map { 2 * it >= report.size }

    private fun Boolean.toInt() = if (this) 1 else 0

    private fun List<String>.binaryToInt(): List<List<Int>> = filter(String::isNotEmpty)
        .map { it.toIntOrNull(2) }
        .requireNoNulls()
        .map {
            it.toString(2)
                .padStart(maxLength(), '0')
                .toIntArray()
        }

    private fun List<String>.maxLength() = maxOf { it.length }

    private fun String.toIntArray() = map { it.digitToIntOrNull() }
        .requireNoNulls()

    private fun Iterable<Int>.binaryToInt() = apply { require(all { it == 0 || it == 1 }) }
        .joinToString("")
        .toInt(2)

    @JvmStatic
    fun main(args: Array<String>) {
        main()
    }
}
