package nl.jjkester.adventofcode21.day02

import nl.jjkester.adventofcode21.boilerplate.*

object Day02 : D {
    override val day = day(2) {
        val values = input { "course.txt" }.notEmptyLines()
        val instructions = parseInstructions(values)

        part {
            answer("Multiplication of horizontal position and depth") {
                horizontalAndDepth(instructions)
            }
        }

        part {
            answer("Multiplication of horizontal position and depth considering aim") {
                horizontalAndDepthWithAim(instructions)
            }
        }
    }

    fun parseInstructions(values: List<String>) = values.asSequence()
        .map { it.split(" ", limit = 2) }
        .map { it[0] to it[1].toInt() }
        .toList()

    fun horizontalAndDepth(instructions: List<Pair<String, Int>>) = instructions.asSequence()
        .map { it.instructionAsVerticalMovement() }
        .requireNoNulls()
        .reduce { first, second -> first.combine(second) }
        .let { (first, second) -> first * second }

    fun horizontalAndDepthWithAim(instructions: List<Pair<String, Int>>) = instructions.asSequence()
        .map { it.instructionAsPitch() }
        .requireNoNulls()
        .reduce { first, second -> first.combine(second) }
        .let { (first, second) -> first * second }

    private fun Pair<String, Int>.instructionAsVerticalMovement() = when (first) {
        "forward" -> second to 0
        "up" -> 0 to -1 * second
        "down" -> 0 to second
        else -> null
    }

    private fun Pair<String, Int>.instructionAsPitch() = when (first) {
        "forward" -> Triple(second, 0, 0)
        "up" -> Triple(0, 0, -1 * second)
        "down" -> Triple(0, 0, second)
        else -> null
    }

    private fun verticalMovementFromHorizontalAndPitch(second: Triple<Int, Int, Int>, first: Triple<Int, Int, Int>) =
        if (second.second == 0 && second.third == 0) {
            first.third * second.first
        } else {
            0
        }

    private fun Pair<Int, Int>.combine(other: Pair<Int, Int>) = first + other.first to this.second + other.second

    private fun Triple<Int, Int, Int>.combine(second: Triple<Int, Int, Int>) = Triple(
        first + second.first,
        this.second + verticalMovementFromHorizontalAndPitch(second, this),
        third + second.third
    )

    @JvmStatic
    fun main(args: Array<String>) {
        main()
    }
}
