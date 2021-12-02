package nl.jjkester.adventofcode21

import nl.jjkester.adventofcode21.boilerplate.format
import nl.jjkester.adventofcode21.day01.Day01
import nl.jjkester.adventofcode21.day02.Day02

val days = setOf(
    Day01,
    Day02,
)

fun main() {
    days.map { it.day() }
        .sorted()
        .map { it.format() }
        .forEach(::println)
}
