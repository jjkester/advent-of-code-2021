package nl.jjkester.adventofcode21.day14

import nl.jjkester.adventofcode21.day14.Day14.occurrence
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day14Test {
    val template = "NNCB"

    val rules = mapOf(
        "CH" to 'B',
        "HH" to 'N',
        "CB" to 'H',
        "NH" to 'C',
        "HB" to 'C',
        "HC" to 'B',
        "HN" to 'C',
        "NN" to 'C',
        "BH" to 'H',
        "NC" to 'B',
        "NB" to 'B',
        "BN" to 'B',
        "BB" to 'N',
        "BC" to 'B',
        "CC" to 'N',
        "CN" to 'C',
    )

    @Test
    fun occurrence() {
        assertThat("ABCDABCD".occurrence())
            .containsExactlyInAnyOrderEntriesOf(
                mapOf(
                    "AB" to 2L,
                    "BC" to 2L,
                    "CD" to 2L,
                    "DA" to 1L,
                )
            )
    }

    @Test
    fun applySteps1() {
        assertThat(Day14.applySteps(template, rules, 1))
            .isEqualTo("NCNBCHB".occurrence())
    }

    @Test
    fun applySteps2() {
        assertThat(Day14.applySteps(template, rules, 2))
            .isEqualTo("NBCCNBBBCBHCB".occurrence())
    }

    @Test
    fun applySteps3() {
        assertThat(Day14.applySteps(template, rules, 3))
            .isEqualTo("NBBBCNCCNBBNBNBBCHBHHBCHB".occurrence())
    }

    @Test
    fun applySteps4() {
        assertThat(Day14.applySteps(template, rules, 4))
            .isEqualTo("NBBNBNBBCCNBCNCCNBBNBBNBBBNBBNBBCBHCBHHNHCBBCBHCB".occurrence())
    }

    @Test
    fun mostCommonMinusLeastCommon10() {
        assertThat(Day14.mostCommonMinusLeastCommon(template, rules, 10))
            .isEqualTo(1588)
    }

    @Test
    fun mostCommonMinusLeastCommon40() {
        assertThat(Day14.mostCommonMinusLeastCommon(template, rules, 40))
            .isEqualTo(2188189693529)
    }
}
