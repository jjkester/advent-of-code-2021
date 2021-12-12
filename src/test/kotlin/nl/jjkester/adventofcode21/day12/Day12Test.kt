package nl.jjkester.adventofcode21.day12

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day12Test {
    val smallEdges = listOf(
        "start" to "A",
        "start" to "b",
        "A" to "c",
        "A" to "b",
        "b" to "d",
        "A" to "end",
        "b" to "end"
    )

    val mediumEdges = listOf(
        "dc" to "end",
        "HN" to "start",
        "start" to "kj",
        "dc" to "start",
        "dc" to "HN",
        "LN" to "dc",
        "HN" to "end",
        "kj" to "sa",
        "kj" to "HN",
        "kj" to "dc",
    )

    @Test
    fun numberOfPathsVisitingSmallCavesOnceSmall() {
        assertThat(Day12.numberOfPathsVisitingSmallCaves(smallEdges, false))
            .isEqualTo(10)
    }

    @Test
    fun numberOfPathsVisitingSmallCavesOnceMedium() {
        assertThat(Day12.numberOfPathsVisitingSmallCaves(mediumEdges, false))
            .isEqualTo(19)
    }

    @Test
    fun numberOfPathsVisitingSmallCavesOnceOneTwiceSmall() {
        assertThat(Day12.numberOfPathsVisitingSmallCaves(smallEdges, true))
            .isEqualTo(36)
    }

    @Test
    fun numberOfPathsVisitingSmallCavesOnceOneTwiceMedium() {
        assertThat(Day12.numberOfPathsVisitingSmallCaves(mediumEdges, true))
            .isEqualTo(103)
    }

    @Test
    fun pathsOnceSmall() {
        assertThat(Day12.paths(smallEdges, false))
            .containsExactlyInAnyOrder(
                listOf("start", "A", "b", "A", "c", "A", "end"),
                listOf("start", "A", "b", "A", "end"),
                listOf("start", "A", "b", "end"),
                listOf("start", "A", "c", "A", "b", "A", "end"),
                listOf("start", "A", "c", "A", "b", "end"),
                listOf("start", "A", "c", "A", "end"),
                listOf("start", "A", "end"),
                listOf("start", "b", "A", "c", "A", "end"),
                listOf("start", "b", "A", "end"),
                listOf("start", "b", "end"),
            )
    }

    @Test
    fun pathOnceMedium() {
        assertThat(Day12.paths(mediumEdges, false))
            .containsExactlyInAnyOrder(
                listOf("start", "HN", "dc", "HN", "end"),
                listOf("start", "HN", "dc", "HN", "kj", "HN", "end"),
                listOf("start", "HN", "dc", "end"),
                listOf("start", "HN", "dc", "kj", "HN", "end"),
                listOf("start", "HN", "end"),
                listOf("start", "HN", "kj", "HN", "dc", "HN", "end"),
                listOf("start", "HN", "kj", "HN", "dc", "end"),
                listOf("start", "HN", "kj", "HN", "end"),
                listOf("start", "HN", "kj", "dc", "HN", "end"),
                listOf("start", "HN", "kj", "dc", "end"),
                listOf("start", "dc", "HN", "end"),
                listOf("start", "dc", "HN", "kj", "HN", "end"),
                listOf("start", "dc", "end"),
                listOf("start", "dc", "kj", "HN", "end"),
                listOf("start", "kj", "HN", "dc", "HN", "end"),
                listOf("start", "kj", "HN", "dc", "end"),
                listOf("start", "kj", "HN", "end"),
                listOf("start", "kj", "dc", "HN", "end"),
                listOf("start", "kj", "dc", "end"),
            )
    }

    @Test
    fun pathsOnceOneTwiceSmall() {
        assertThat(Day12.paths(smallEdges, true))
            .containsExactlyInAnyOrder(
                listOf("start", "A", "b", "A", "b", "A", "c", "A", "end"),
                listOf("start", "A", "b", "A", "b", "A", "end"),
                listOf("start", "A", "b", "A", "b", "end"),
                listOf("start", "A", "b", "A", "c", "A", "b", "A", "end"),
                listOf("start", "A", "b", "A", "c", "A", "b", "end"),
                listOf("start", "A", "b", "A", "c", "A", "c", "A", "end"),
                listOf("start", "A", "b", "A", "c", "A", "end"),
                listOf("start", "A", "b", "A", "end"),
                listOf("start", "A", "b", "d", "b", "A", "c", "A", "end"),
                listOf("start", "A", "b", "d", "b", "A", "end"),
                listOf("start", "A", "b", "d", "b", "end"),
                listOf("start", "A", "b", "end"),
                listOf("start", "A", "c", "A", "b", "A", "b", "A", "end"),
                listOf("start", "A", "c", "A", "b", "A", "b", "end"),
                listOf("start", "A", "c", "A", "b", "A", "c", "A", "end"),
                listOf("start", "A", "c", "A", "b", "A", "end"),
                listOf("start", "A", "c", "A", "b", "d", "b", "A", "end"),
                listOf("start", "A", "c", "A", "b", "d", "b", "end"),
                listOf("start", "A", "c", "A", "b", "end"),
                listOf("start", "A", "c", "A", "c", "A", "b", "A", "end"),
                listOf("start", "A", "c", "A", "c", "A", "b", "end"),
                listOf("start", "A", "c", "A", "c", "A", "end"),
                listOf("start", "A", "c", "A", "end"),
                listOf("start", "A", "end"),
                listOf("start", "b", "A", "b", "A", "c", "A", "end"),
                listOf("start", "b", "A", "b", "A", "end"),
                listOf("start", "b", "A", "b", "end"),
                listOf("start", "b", "A", "c", "A", "b", "A", "end"),
                listOf("start", "b", "A", "c", "A", "b", "end"),
                listOf("start", "b", "A", "c", "A", "c", "A", "end"),
                listOf("start", "b", "A", "c", "A", "end"),
                listOf("start", "b", "A", "end"),
                listOf("start", "b", "d", "b", "A", "c", "A", "end"),
                listOf("start", "b", "d", "b", "A", "end"),
                listOf("start", "b", "d", "b", "end"),
                listOf("start", "b", "end"),
            )
    }
}
