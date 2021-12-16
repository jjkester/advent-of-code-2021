package nl.jjkester.adventofcode21.day16

class PacketParser private constructor(val binaryString: String) {
    private var position = 0

    fun parse(): List<Packet> {
        val packets = mutableListOf<Packet>()

        while (position < binaryString.length && binaryString.length - position > 7) {
            packets.add(parsePacket())
        }

        return packets
    }

    private fun parsePacket(): Packet {
        val version = read(3).binary()
        val type = read(3).binary()

        return when (type) {
            0 -> Packet.Sum(version, parseSubpackets())
            1 -> Packet.Product(version, parseSubpackets())
            2 -> Packet.Minimum(version, parseSubpackets())
            3 -> Packet.Maximum(version, parseSubpackets())
            4 -> Packet.Value(version, parseLiteralData())
            5 -> Packet.GreaterThan(version, parseSubpackets())
            6 -> Packet.LessThan(version, parseSubpackets())
            7 -> Packet.EqualTo(version, parseSubpackets())
            else -> throw IllegalStateException()
        }
    }

    private fun parseLiteralData(): Long {
        return readLiteralChunks().joinToString("").toLong(2)
    }

    private fun parseSubpackets(): List<Packet> {
        val lengthType = read(1).binary()

        return if (lengthType == 0) {
            val bitLength = read(15).binary()
            parseSubpacketsByBitLength(bitLength)
        } else {
            val count = read(11).binary()
            parseSubpacketsByCount(count)
        }
    }

    private fun parseSubpacketsByBitLength(length: Int): List<Packet> {
        val packets = mutableListOf<Packet>()
        val end = position + length

        while (position < end) {
            packets.add(parsePacket())
        }

        return packets
    }

    private fun parseSubpacketsByCount(count: Int): List<Packet> {
        val packets = mutableListOf<Packet>()

        while (packets.size < count) {
            packets.add(parsePacket())
        }

        return packets
    }

    private fun read(length: Int): String {
        val result = binaryString.substring(position, position + length)
        position += length
        return result
    }

    private fun readLiteralChunks(): Sequence<String> {
        return sequence {
            var isLast = false
            while (!isLast) {
                isLast = read(1) == "0"
                yield(read(4))
            }
        }
    }

    private fun String.binary() = toInt(2)

    companion object {
        fun binary(string: String): PacketParser {
            require(string.matches(Regex("^[01]*$"))) { "Invalid characters" }

            return PacketParser(string)
        }

        fun hexadecimal(string: String): PacketParser {
            require(string.matches(Regex("^[0-9A-F]*$"))) { "Invalid characters" }

            val hexString = string.asSequence()
                .map { it.toString().toInt(16).toString(2).padStart(4, '0') }
                .joinToString("")

            return PacketParser(hexString)
        }
    }
}
