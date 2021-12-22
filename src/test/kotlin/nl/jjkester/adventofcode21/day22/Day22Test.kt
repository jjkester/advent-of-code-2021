package nl.jjkester.adventofcode21.day22

import nl.jjkester.adventofcode21.day22.Day22.withoutOverlapFrom
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day22Test {
    val instructionsSmall = listOf(
        Region(true, 10..12, 10..12, 10..12),
        Region(true, 11..13, 11..13, 11..13),
        Region(false, 9..11, 9..11, 9..11),
        Region(true, 10..10, 10..10, 10..10),
    )

    val instructionsPartOne = listOf(
        Region(true, -20..26, -36..17, -47..7),
        Region(true, -20..33, -21..23, -26..28),
        Region(true, -22..28, -29..23, -38..16),
        Region(true, -46..7, -6..46, -50..-1),
        Region(true, -49..1, -3..46, -24..28),
        Region(true, 2..47, -22..22, -23..27),
        Region(true, -27..23, -28..26, -21..29),
        Region(true, -39..5, -6..47, -3..44),
        Region(true, -30..21, -8..43, -13..34),
        Region(true, -22..26, -27..20, -29..19),
        Region(false, -48..-32, 26..41, -47..-37),
        Region(true, -12..35, 6..50, -50..-2),
        Region(false, -48..-32, -32..-16, -15..-5),
        Region(true, -18..26, -33..15, -7..46),
        Region(false, -40..-22, -38..-28, 23..41),
        Region(true, -16..35, -41..10, -47..6),
        Region(false, -32..-23, 11..30, -14..3),
        Region(true, -49..-5, -3..45, -29..18),
        Region(false, 18..30, -20..-8, -3..13),
        Region(true, -41..9, -7..43, -33..15),
        Region(true, -54112..-39298, -85059..-49293, -27449..7877),
        Region(true, 967..23432, 45373..81175, 27513..53682),
    )

    @Test
    fun turnedOnCubesInsideRegionSmall() {

        assertThat(Day22.turnedOnCubesInsideRegion(instructionsSmall.take(1)))
            .isEqualTo(27)
        assertThat(Day22.turnedOnCubesInsideRegion(instructionsSmall.take(2)))
            .isEqualTo(46)
        assertThat(Day22.turnedOnCubesInsideRegion(instructionsSmall.take(3)))
            .isEqualTo(38)
        assertThat(Day22.turnedOnCubesInsideRegion(instructionsSmall))
            .isEqualTo(39)
    }

    @Test
    fun turnedOnCubesInsideRegionBounded() {
        assertThat(Day22.turnedOnCubesInsideRegion(instructionsPartOne, Region(false, -50..50, -50..50, -50..50)))
            .isEqualTo(590784)
    }

    @Test
    fun withoutOverlapFromMiddle() {
        val included = Region(true, -1..1, -1..1, -1..1)
        val excluded = Region(false, 0..0, 0..0, 0..0)

        val result = setOf(included).withoutOverlapFrom(excluded)

        assertThat(result).containsExactly(
            Region(true, -1..-1, -1..1, -1..1),
            Region(true, 1..1, -1..1, -1..1),
            Region(true, 0..0, -1..-1, -1..1),
            Region(true, 0..0, 1..1, -1..1),
            Region(true, 0..0, 0..0, -1..-1),
            Region(true, 0..0, 0..0, 1..1),
        )
        assertThat(result.sumOf { it.size }).isEqualTo(26)
    }
}
