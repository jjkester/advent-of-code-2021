@file:JvmName("Main")
package nl.jjkester.adventofcode21

import nl.jjkester.adventofcode21.boilerplate.format
import nl.jjkester.adventofcode21.day01.Day01
import nl.jjkester.adventofcode21.day02.Day02
import nl.jjkester.adventofcode21.day03.Day03

val days = setOf(
    Day01,
    Day02,
    Day03,
)

fun main(args: Array<String>) {
    require(args.size <= 1)

    days.map { it.day }
        .run {
            when (args.getOrNull(0)) {
                "latest" -> this.maxByOrNull { it.day }?.let(::listOf) ?: emptyList()
                null -> this
                else -> emptyList()
            }
        }
        .map { it() }
        .sorted()
        .map { it.format() }
        .forEach(::println)
}
