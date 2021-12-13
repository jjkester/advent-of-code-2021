package nl.jjkester.adventofcode21.day13

import nl.jjkester.adventofcode21.boilerplate.*
import java.lang.IllegalArgumentException
import kotlin.math.max

object Day13 : Day {
    override val builder = day(13) {
        val dots by input("input.txt") {
            sectionSeparated().first()
                .lineSeparated()
                .notBlank()
                .map { line -> line.commaSeparated().ints().toPair() }
                .toSet()
        }

        val instructions by input("input.txt") {
            sectionSeparated().last()
                .lineSeparated()
                .notBlank()
                .strings()
                .map { it.removePrefix("fold along ").split("=").toPair() }
                .map { (axis, n) -> axis to n.toInt() }
        }

        part {
            answer("Number of visible dots after first fold") {
                visibleDotsAfterFirstFold(dots, instructions.first())
            }
        }

        part {
            answer("Activation code") {
                activationCode(dots, instructions)
            }
        }
    }

    fun visibleDotsAfterFirstFold(dots: Set<Pair<Int, Int>>, instruction: Pair<String, Int>): Int {
        return fold(dots, instruction).size
    }

    fun activationCode(dots: Set<Pair<Int, Int>>, instructions: List<Pair<String, Int>>): String {
        val outcome = instructions.fold(dots, ::fold)

        val (maxX, maxY) = outcome.reduce { (x0, y0), (x1, y1) -> max(x0, x1) to  max(y0, y1) }

        val formatted = (0..maxY).joinToString(System.lineSeparator()) { y ->
            (0..maxX).joinToString("") { x ->
                if (x to y in outcome) "#" else "."
            }
        }

        return formatted
    }

    private fun fold(dots: Set<Pair<Int, Int>>, instruction: Pair<String, Int>): Set<Pair<Int, Int>> {
        return when (instruction.first) {
            "x" -> foldVertical(dots, instruction.second)
            "y" -> foldHorizontal(dots, instruction.second)
            else -> throw IllegalArgumentException("Invalid instruction: ${instruction.first}")
        }
    }

    fun foldHorizontal(dots: Set<Pair<Int, Int>>, foldLineY: Int): Set<Pair<Int, Int>> {
        val (keep, invert) = dots.partition { (x, y) -> y < foldLineY }

        val inverted = invert.map { (x, y) -> x to foldLineY - (y - foldLineY) }

        return keep.plus(inverted).toSet()
    }

    fun foldVertical(dots: Set<Pair<Int, Int>>, foldLineX: Int): Set<Pair<Int, Int>> {
        val (keep, invert) = dots.partition { (x, y) -> x < foldLineX }

        val inverted = invert.map { (x, y) -> foldLineX - (x - foldLineX) to y }

        return keep.plus(inverted).toSet()
    }
}
