package nl.jjkester.adventofcode21.day06

import nl.jjkester.adventofcode21.boilerplate.*

object Day06 : Day {
    override val builder = day(6) {
        val initial by input("initial.txt") {
            commaSeparated()
                .notBlank()
                .ints()
                .map { it.toUInt() }
        }

        part {
            answer("Number of lanternfish after 80 days") {
                countAfterDays(initial, 80)
            }
        }

        part {
            answer("Number of lanternfish after 256 days") {
                countAfterDays(initial, 256)
            }
        }
    }

    fun countAfterDays(initial: List<UInt>, days: Int): Long {
        val adult = mutableMapOf<UInt, Long>()
        var child = initial.map { it to 1L }

        (1..days).forEach { day ->
            val key = day.toUInt() % 7u

            child = child.mapNotNull { (value, count) ->
                if (value > 0u) {
                    value - 1u to count
                } else {
                    adult.compute(key) { _, c -> (c ?: 0L) + count }
                    null
                }
            }

            adult[key]?.also { child = child + listOf(8u to it) }
        }

        return adult.values.sum() + child.sumOf { it.second }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        Day06.main()
    }
}
