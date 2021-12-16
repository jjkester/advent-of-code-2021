package nl.jjkester.adventofcode21.day16

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PacketParserTest {

    @Test
    fun hexadecimalInput() {
        assertThat(PacketParser.hexadecimal("38006F45291200").binaryString)
            .isEqualTo("00111000000000000110111101000101001010010001001000000000")
    }

    @Test
    fun parseSingleValue() {
        assertThat(PacketParser.binary("110100101111111000101000").parse())
            .containsExactly(
                Packet.Value(6, 2021)
            )
    }

    @Test
    fun parseSingleOperatorWithValuesByLength() {
        assertThat(PacketParser.binary("00111000000000000110111101000101001010010001001000000000").parse())
            .containsExactly(
                Packet.LessThan(1, listOf(
                    Packet.Value(6, 10),
                    Packet.Value(2, 20),
                ))
            )
    }

    @Test
    fun parseSingleOperatorWithValuesByCount() {
        assertThat(PacketParser.binary("11101110000000001101010000001100100000100011000001100000").parse())
            .containsExactly(
                Packet.Maximum(7, listOf(
                    Packet.Value(2, 1),
                    Packet.Value(4, 2),
                    Packet.Value(1, 3),
                ))
            )
    }

    @Test
    fun parseNestedOperators() {
        assertThat(PacketParser.hexadecimal("8A004A801A8002F478").parse())
            .containsExactly(
                Packet.Minimum(4, listOf(
                    Packet.Minimum(1, listOf(
                        Packet.Minimum(5, listOf(
                            Packet.Value(6, 15)
                        ))
                    ))
                ))
            )
    }
}
