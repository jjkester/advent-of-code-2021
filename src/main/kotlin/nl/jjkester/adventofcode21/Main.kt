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
import nl.jjkester.adventofcode21.day10.Day10
import nl.jjkester.adventofcode21.day11.Day11
import nl.jjkester.adventofcode21.day12.Day12
import nl.jjkester.adventofcode21.day13.Day13
import nl.jjkester.adventofcode21.day14.Day14
import nl.jjkester.adventofcode21.day15.Day15
import nl.jjkester.adventofcode21.day16.Day16
import nl.jjkester.adventofcode21.day17.Day17

val days = setOf(
    Day01,
    Day02,
    Day03,
    Day04,
    Day05,
    Day06,
    Day07,
    Day08,
    Day09,
    Day10,
    Day11,
    Day12,
    Day13,
    Day14,
    Day15,
    Day16,
    Day17,
)

fun main(args: Array<String>) {
    days.map { it.builder.day }
        .fold(Int.MAX_VALUE to Int.MIN_VALUE) { p, d -> minOf(p.first, d) to maxOf(p.second, d) }
        .let { (min, max) -> (min..max).minus(days.map { it.builder.day }.toSet()) }
        .let { missing -> require(missing.isEmpty()) { "There are days missing: ${missing.joinToString()}" } }

    days.groupBy { it.builder.day }
        .forEach { (dayNumber, objects) ->
            require(objects.size == 1) {
                "There is more than one implementation for day $dayNumber: ${objects.joinToString { it.javaClass.name }}"
            }
        }

    require(args.size <= 1)

    days.map { it.builder }
        .run {
            when (val arg = args.getOrNull(0)) {
                "latest" -> this.maxByOrNull { it.day }?.let { listOf(it) }
                null -> this
                else -> arg.toIntOrNull()?.let { i -> this.filter { it.day == i } }
            } ?: emptyList()
        }
        .asSequence()
        .sortedBy { it.day }
        .map { it() }
        .forEach { println(it.format()) }
}
