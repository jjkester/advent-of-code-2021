package nl.jjkester.adventofcode21.day09

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day09Test {
    val heightmap = Heightmap(
        listOf(
            listOf(2, 1, 9, 9, 9, 4, 3, 2, 1, 0),
            listOf(3, 9, 8, 7, 8, 9, 4, 9, 2, 1),
            listOf(9, 8, 5, 6, 7, 8, 9, 8, 9, 2),
            listOf(8, 7, 6, 7, 8, 9, 6, 7, 8, 9),
            listOf(9, 8, 9, 9, 9, 6, 5, 6, 7, 8),
        )
    )

    @Test
    fun sumOfRiskLevelsOfLowPoints() {
        assertThat(Day09.sumOfRiskLevelsOfLowPoints(heightmap))
            .isEqualTo(15)
    }

    @Test
    fun productOfSizesOfThreeLargestBasins() {
        assertThat(Day09.productOfSizesOfThreeLargestBasins(heightmap))
            .isEqualTo(1134)
    }

    @Test
    fun findLowPoints() {
        assertThat(Day09.findLowPoints(heightmap))
            .containsExactlyInAnyOrder(
                1 to 0,
                9 to 0,
                2 to 2,
                6 to 4,
            )
    }

    @Test
    fun findBasins() {
        assertThat(Day09.findBasins(heightmap))
            .containsExactlyInAnyOrder(
                setOf(0 to 0, 0 to 1, 1 to 0),
                setOf(5 to 0, 6 to 0, 7 to 0, 8 to 0, 9 to 0, 6 to 1, 8 to 1, 9 to 1, 9 to 2),
                setOf(0 to 3, 1 to 2, 1 to 3, 1 to 4, 2 to 1, 2 to 2, 2 to 3, 3 to 1, 3 to 2, 3 to 3, 4 to 1, 4 to 2, 4 to 3, 5 to 2),
                setOf(7 to 2, 6 to 3, 7 to 3, 8 to 3, 5 to 4, 6 to 4, 7 to 4, 8 to 4, 9 to 4),
            )
    }
}
