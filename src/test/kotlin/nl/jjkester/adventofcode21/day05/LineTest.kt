package nl.jjkester.adventofcode21.day05

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class LineTest {
    @Test
    fun iteratorHorizontal() {
        val line = Line(Point(0, 0), Point(5, 0))

        assertThat(line.toList()).containsExactly(
            Point(0, 0),
            Point(1, 0),
            Point(2, 0),
            Point(3, 0),
            Point(4, 0),
            Point(5, 0),
        )
    }

    @Test
    fun iteratorVertical() {
        val line = Line(Point(0, 0), Point(0, 5))

        assertThat(line.toList()).containsExactly(
            Point(0, 0),
            Point(0, 1),
            Point(0, 2),
            Point(0, 3),
            Point(0, 4),
            Point(0, 5),
        )
    }

    @Test
    fun iteratorDiagonalBothIncreasing() {
        val line = Line(Point(0, 0), Point(5, 5))

        assertThat(line.toList()).containsExactly(
            Point(0, 0),
            Point(1, 1),
            Point(2, 2),
            Point(3, 3),
            Point(4, 4),
            Point(5, 5)
        )
    }

    @Test
    fun iteratorDiagonalOneIncreasingOneDecreasing() {
        val line = Line(Point(5, 0), Point(0, 5))

        assertThat(line.toList()).containsExactly(
            Point(5, 0),
            Point(4, 1),
            Point(3, 2),
            Point(2, 3),
            Point(1, 4),
            Point(0, 5)
        )
    }
}
