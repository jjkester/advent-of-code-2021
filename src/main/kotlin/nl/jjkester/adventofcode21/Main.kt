@file:JvmName("Main")
package nl.jjkester.adventofcode21

import nl.jjkester.adventofcode21.boilerplate.format
import nl.jjkester.adventofcode21.day01.Day01
import nl.jjkester.adventofcode21.day02.Day02
import nl.jjkester.adventofcode21.day03.Day03
import nl.jjkester.adventofcode21.day04.Day04
import nl.jjkester.adventofcode21.day05.Day05
import nl.jjkester.adventofcode21.day06.Day06
import nl.jjkester.adventofcode21.day07.Day07
import nl.jjkester.adventofcode21.day08.Day08
import nl.jjkester.adventofcode21.day09.Day09

val days = setOf(
    Day01,
    Day02,
    Day03,
    Day04,
    Day05,
    Day06,
    Day07,
    Day08,
    Day09
)

fun main(args: Array<String>) {
    require(args.size <= 1)

    days.map { it.day }
        .run {
            when (val arg = args.getOrNull(0)) {
                "latest" -> this.maxByOrNull { it.day }?.let(::listOf)
                null -> this
                else -> arg.toIntOrNull()?.let { i -> this.filter { it.day == i } }
            } ?: emptyList()
        }
        .map { it() }
        .sorted()
        .map { it.format() }
        .forEach(::println)
}
