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
        part {
            answer("Quantity of most common element minus quantity of least common element after 40 steps") {
                mostCommonMinusLeastCommon(template, rules, 40)
            }
        }
    }

    fun mostCommonMinusLeastCommon(template: String, rules: Map<String, Char>, steps: Int): Long {
        val counts = applySteps(template, rules, steps)
            .flatMap { (key, value) -> key.map { it to value } }
            .groupBy { it.first }
            .mapValues { (key, value) ->
                if (key == template.first() || key == template.last()) {
                    (value.sumOf { it.second } + 1) / 2
                } else {
                    value.sumOf { it.second } / 2
                }
            }

        return counts.maxOf { it.value } - counts.minOf { it.value }
    }

    fun applySteps(template: String, rules: Map<String, Char>, times: Int): Map<String, Long> {
        return (1..times).fold(template.occurrence()) { previous, _ ->
            previous.apply(rules)
        }
    }

    fun String.occurrence(): Map<String, Long> {
        return windowedSequence(2)
            .groupingBy { it }
            .eachCount()
            .mapValues { it.value.toLong() }
    }

    private fun Map<String, Long>.apply(rules: Map<String, Char>): Map<String, Long> {
        val result = mutableMapOf<String, Long>()

        forEach { (pair, count) ->
            val insertedChar = rules[pair]!!
            val newPairLeft = buildString {
                append(pair[0])
                append(insertedChar)
            }
            val newPairRight = buildString {
                append(insertedChar)
                append(pair[1])
            }

            result.compute(newPairLeft) { _, current -> current?.plus(count) ?: count }
            result.compute(newPairRight) { _, current -> current?.plus(count) ?: count }
        }

        return result
    }
}
