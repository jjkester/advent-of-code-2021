package nl.jjkester.adventofcode21.day05

import nl.jjkester.adventofcode21.day05.Day05.toLines
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.lang.Integer.max

internal class Day05Test {
    val values = listOf(
        (0 to 9) to (5 to 9),
        (8 to 0) to (0 to 8),
        (9 to 4) to (3 to 4),
        (2 to 2) to (2 to 1),
        (7 to 0) to (7 to 4),
        (6 to 4) to (2 to 0),
        (0 to 9) to (2 to 9),
        (3 to 4) to (1 to 4),
        (0 to 0) to (8 to 8),
        (5 to 5) to (8 to 2),
    )

    val diagram = """
        1.1....11.
        .111...2..
        ..2.1.111.
        ...1.2.2..
        .112313211
        ...1.2....
        ..1...1...
        .1.....1..
        1.......1.
        222111....
        """.trimIndent()

    @Test
    fun pointsWhereAtLeastTwoNotDiagonalOverlap() {
        assertThat(Day05.pointsWhereAtLeastTwoNotDiagonalOverlap(values.toLines()))
            .isEqualTo(5)
    }

    @Test
    fun pointsWhereAtLeastTwoOverlap() {
        assertThat(Day05.pointsWhereAtLeastTwoOverlap(values.toLines()))
            .isEqualTo(12)
    }

    @Test
    fun overlappingPoints() {
        val result = Day05.overlappingPoints(values.toLines())

        assertThat(result.toDiagram())
            .isEqualTo(diagram)
    }

    private fun Map<Point, Int>.toDiagram(): String {
        val max = keys.reduce { first, second -> Point(max(first.x, second.x), max(first.y, second.y)) }

        return (0..max.y).map { y ->
            (0..max.x).map { x ->
                this[Point(x, y)]?.toString() ?: "."
            }.joinToString("")
        }.joinToString(System.lineSeparator())
    }
}
