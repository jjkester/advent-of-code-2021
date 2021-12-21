package nl.jjkester.adventofcode21.day19

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SolverTest {
    @Test
    fun solve2dAlreadyOverlapping() {
        val scanners = listOf(
            Scanner("Scanner 0", listOf(Coordinate(0, 2, 0), Coordinate(4, 1, 0), Coordinate(3, 3, 0))),
            Scanner("Scanner 1", listOf(Coordinate(-1, -1, 0), Coordinate(-5, 0, 0), Coordinate(-2, 1, 0))),
        )
        assertThat(Solver(3).alignScanners(scanners))
            .isEqualTo(
                mapOf(
                    Scanner("Scanner 0", listOf(Coordinate(0, 2, 0), Coordinate(4, 1, 0), Coordinate(3, 3, 0))) to Coordinate(0, 0, 0),
                    Scanner("Scanner 1", listOf(Coordinate(-1, -1, 0), Coordinate(-5, 0, 0), Coordinate(-2, 1, 0))) to Coordinate(5, 2, 0),
                )
            )
    }
}
