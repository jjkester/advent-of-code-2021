package nl.jjkester.adventofcode21.day22

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day22Test {
    val instructions = listOf(
        Instruction(true, Region(-20..26, -36..17, -47..7)),
        Instruction(true, Region(-20..33, -21..23, -26..28)),
        Instruction(true, Region(-22..28, -29..23, -38..16)),
        Instruction(true, Region(-46..7, -6..46, -50..-1)),
        Instruction(true, Region(-49..1, -3..46, -24..28)),
        Instruction(true, Region(2..47, -22..22, -23..27)),
        Instruction(true, Region(-27..23, -28..26, -21..29)),
        Instruction(true, Region(-39..5, -6..47, -3..44)),
        Instruction(true, Region(-30..21, -8..43, -13..34)),
        Instruction(true, Region(-22..26, -27..20, -29..19)),
        Instruction(false, Region(-48..-32, 26..41, -47..-37)),
        Instruction(true, Region(-12..35, 6..50, -50..-2)),
        Instruction(false, Region(-48..-32, -32..-16, -15..-5)),
        Instruction(true, Region(-18..26, -33..15, -7..46)),
        Instruction(false, Region(-40..-22, -38..-28, 23..41)),
        Instruction(true, Region(-16..35, -41..10, -47..6)),
        Instruction(false, Region(-32..-23, 11..30, -14..3)),
        Instruction(true, Region(-49..-5, -3..45, -29..18)),
        Instruction(false, Region(18..30, -20..-8, -3..13)),
        Instruction(true, Region(-41..9, -7..43, -33..15)),
        Instruction(true, Region(-54112..-39298, -85059..-49293, -27449..7877)),
        Instruction(true, Region(967..23432, 45373..81175, 27513..53682)),
    )

    @Test
    fun turnedOnCubesInsideRegion() {
        assertThat(Day22.turnedOnCubesInsideRegion(instructions, Region(-50..50, -50..50, -50..50)))
            .isEqualTo(590784)
    }
}
