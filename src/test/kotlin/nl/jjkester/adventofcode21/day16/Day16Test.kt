package nl.jjkester.adventofcode21.day16

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day16Test {

    @Test
    fun packetVersionSum() {
        assertThat(Day16.packetVersionSum("8A004A801A8002F478"))
            .isEqualTo(16)
        assertThat(Day16.packetVersionSum("620080001611562C8802118E34"))
            .isEqualTo(12)
        assertThat(Day16.packetVersionSum("C0015000016115A2E0802F182340"))
            .isEqualTo(23)
        assertThat(Day16.packetVersionSum("A0016C880162017C3686B18A3D4780"))
            .isEqualTo(31)
    }

    @Test
    fun compute() {
        assertThat(Day16.compute("C200B40A82"))
            .isEqualTo(3)
        assertThat(Day16.compute("04005AC33890"))
            .isEqualTo(54)
        assertThat(Day16.compute("880086C3E88112"))
            .isEqualTo(7)
        assertThat(Day16.compute("CE00C43D881120"))
            .isEqualTo(9)
        assertThat(Day16.compute("D8005AC2A8F0"))
            .isEqualTo(1)
        assertThat(Day16.compute("F600BC2D8F"))
            .isEqualTo(0)
        assertThat(Day16.compute("9C005AC2F8F0"))
            .isEqualTo(0)
        assertThat(Day16.compute("9C0141080250320F1802104A08"))
            .isEqualTo(1)
    }
}
