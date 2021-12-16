package nl.jjkester.adventofcode21.day15

import nl.jjkester.adventofcode21.day15.Day15.toCoordinateMap
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day15Test {
    val matrix = listOf(
        listOf(1, 1, 6, 3, 7, 5, 1, 7, 4, 2),
        listOf(1, 3, 8, 1, 3, 7, 3, 6, 7, 2),
        listOf(2, 1, 3, 6, 5, 1, 1, 3, 2, 8),
        listOf(3, 6, 9, 4, 9, 3, 1, 5, 6, 9),
        listOf(7, 4, 6, 3, 4, 1, 7, 1, 1, 1),
        listOf(1, 3, 1, 9, 1, 2, 8, 1, 3, 7),
        listOf(1, 3, 5, 9, 9, 1, 2, 4, 2, 1),
        listOf(3, 1, 2, 5, 4, 2, 1, 6, 3, 9),
        listOf(1, 2, 9, 3, 1, 3, 8, 5, 2, 1),
        listOf(2, 3, 1, 1, 9, 4, 4, 5, 8, 1),
    )

    @Test
    fun lowestRiskForPathFromTopLeftToBottomRight() {
        assertThat(Day15.lowestRiskForPathFromTopLeftToBottomRight(matrix))
            .isEqualTo(40)
    }

    @Test
    fun lowestRiskForPathFromTopLeftToBottomRightRepeated() {
        assertThat(Day15.lowestRiskForPathFromTopLeftToBottomRightRepeated(matrix, 5))
            .isEqualTo(315)
    }

    @Test
    fun dijkstra() {
        val shortestPath = Day15.dijkstra(matrix.toCoordinateMap(), 0 to 0, 9 to 9)
        assertThat(shortestPath.second)
            .isEqualTo(40)
        assertThat(shortestPath.first)
            .hasSize(19)
            .contains(
                0 to 0,
                0 to 1,
                0 to 2,
                1 to 2,
                2 to 2,
                3 to 2,
                4 to 2,
                5 to 2,
                6 to 2,
                6 to 3,
                7 to 3,
                7 to 4,
                8 to 4, // or 7 to 5
                8 to 5,
                8 to 6,
                8 to 7,
                8 to 8,
                9 to 8,
                9 to 9
            )
    }
}
