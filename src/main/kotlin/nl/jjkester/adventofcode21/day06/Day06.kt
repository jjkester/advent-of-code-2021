package nl.jjkester.adventofcode21.day06

import nl.jjkester.adventofcode21.boilerplate.*

object Day06 : D {
    override val day = day(6) {
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
    }

    fun countAfterDays(initial: List<UInt>, days: Int): Int {
        val total = initial.toMutableList()

        repeat(days) {
            var new = 0

            total.forEachIndexed { i, n ->
                if (n > 0u) {
                    total[i] = n - 1u
                } else {
                    total[i] = 6u
                    ++new
                }
            }

            repeat(new) { total.add(8u) }
        }

        return total.count()
    }

    @JvmStatic
    fun main(args: Array<String>) {
        Day06.main()
    }
}
