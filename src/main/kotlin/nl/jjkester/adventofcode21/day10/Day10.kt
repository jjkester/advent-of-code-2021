package nl.jjkester.adventofcode21.day10

import nl.jjkester.adventofcode21.boilerplate.*

object Day10 : Day {
    override val builder = day(10) {
        val code by input("code.txt") {
            lineSeparated()
                .notBlank()
                .strings()
        }

        part {
            answer("Total syntax error score") {
                syntaxErrorScore(code)
            }
        }
        part {
            answer("Middle autocompletion score") {
                middleScore(code)
            }
        }
    }

    fun syntaxErrorScore(code: List<String>): Int = code.asSequence()
        .map { analyzeLine(it) }
        .mapNotNull { it as? LineResult.Corrupt }
        .sumOf { it.points() }

    fun middleScore(code: List<String>): Long {
        val result = code.asSequence()
            .map { analyzeLine(it) }
            .mapNotNull { it as? LineResult.Incomplete }
            .map { it.points() }
            .sorted()
            .toList()

        require(result.size % 2 == 1) { "There must be an odd number of results" }

        return result[(result.size - 1) / 2]
    }

    private fun analyzeLine(line: String): LineResult {
        val stack = mutableListOf<Char>()

        for (char in line) {
            if (char.isOpening()) {
                stack.add(char)
            } else if (char.isClosing()) {
                if (stack.removeLastOrNull() != char.inverse()) {
                    return LineResult.Corrupt(char)
                }
            }
        }

        return if (stack.size == 0) {
            LineResult.Complete
        } else {
            LineResult.Incomplete(stack.reversed().joinToString("") { it.inverse().toString() })
        }
    }

    sealed class LineResult {
        object Complete : LineResult()
        data class Incomplete(val missing: String) : LineResult()
        data class Corrupt(val firstCorruptChar: Char) : LineResult()
    }

    private fun Char.isOpening() = this in "([{<"

    private fun Char.isClosing() = this in ")]}>"

    private fun Char.inverse() = when (this) {
        ')' -> '('
        ']' -> '['
        '}' -> '{'
        '>' -> '<'
        '(' -> ')'
        '[' -> ']'
        '{' -> '}'
        '<' -> '>'
        else -> throw IllegalArgumentException("Inverse only defined for opening and closing characters")
    }

    private fun LineResult.Corrupt.points() = when (firstCorruptChar) {
        ')' -> 3
        ']' -> 57
        '}' -> 1197
        '>' -> 25137
        else -> throw IllegalArgumentException("Points are only counted tor closing characters")
    }

    private fun LineResult.Incomplete.points() = missing
        .map { char ->
            when (char) {
                ')' -> 1
                ']' -> 2
                '}' -> 3
                '>' -> 4
                else -> throw IllegalArgumentException("Points are only counted tor closing characters")
            }
        }
        .fold(0L) { acc, it -> acc * 5 + it }

    @JvmStatic
    fun main(args: Array<String>) {
        Day10.main()
    }
}
