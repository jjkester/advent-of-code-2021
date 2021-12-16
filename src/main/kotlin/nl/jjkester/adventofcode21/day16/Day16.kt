package nl.jjkester.adventofcode21.day16

import nl.jjkester.adventofcode21.boilerplate.*

object Day16 : Day {
    override val builder = day(16) {
        val input by input("input.txt") {
            lineSeparated().first().string()
        }

        part {
            answer("Sum of versions in all packets") {
                packetVersionSum(input)
            }
        }
        part {
            answer("Result of expression") {
                compute(input)
            }
        }
    }

    fun packetVersionSum(input: String): Long {
        return PacketParser.hexadecimal(input).parse().sumOf { it.versionSum() }
    }

    fun compute(input: String): Long {
        return PacketParser.hexadecimal(input).parse().single().value
    }

    fun Packet.versionSum(): Long = when (this) {
        is Packet.Value -> version.toLong()
        is Packet.Operator -> version + subpackets.sumOf { it.versionSum() }
    }
}
