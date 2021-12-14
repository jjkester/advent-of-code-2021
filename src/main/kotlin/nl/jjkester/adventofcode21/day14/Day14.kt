package nl.jjkester.adventofcode21.day14

import nl.jjkester.adventofcode21.boilerplate.*

object Day14 : Day {
    override val builder = day(14) {
        val template by input("polymers.txt") {
            sectionSeparated().first()
                .lineSeparated().first()
                .string()
        }

        val rules by input("polymers.txt") {
            sectionSeparated().skip(1)
                .flatMap { it.lineSeparated() }
                .notBlank()
                .map { it.arrowSeparated().toPair() }
                .associate { it.first.string() to it.second.char() }
        }

        part {
            answer("Quantity of most common element minus quantity of least common element after 10 steps") {
                mostCommonMinusLeastCommon(template, rules, 10)
            }
        }
    }

    fun mostCommonMinusLeastCommon(template: String, rules: Map<String, Char>, steps: Int): Int {
        val counts = applySteps(template, rules, steps).groupingBy { it }.eachCount()

        return counts.maxOf { it.value } - counts.minOf { it.value }
    }

    fun applySteps(template: String, rules: Map<String, Char>, times: Int): String {
        return (1..times).fold(template) { previous, _ ->
            previous.apply(rules)
        }
    }

    private fun String.apply(rules: Map<String, Char>): String {
        return first() + windowedSequence(2)
            .flatMap { it -> sequenceOf(rules[it]!!, it.last()) }
            .joinToString("")
    }
}
