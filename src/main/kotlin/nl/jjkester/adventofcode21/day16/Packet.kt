package nl.jjkester.adventofcode21.day16

sealed class Packet {
    abstract val version: Int
    abstract val type: Int
    abstract val value: Long

    data class Value(override val version: Int, override val value: Long) : Packet() {
        override val type: Int = 4
    }

    sealed class Operator(override val type: Int) : Packet() {
        abstract val subpackets: List<Packet>
    }

    data class Sum(override val version: Int, override val subpackets: List<Packet>) : Operator(0) {
        override val value: Long
            get() = subpackets.sumOf { it.value }
    }

    data class Product(override val version: Int, override val subpackets: List<Packet>) : Operator(1) {
        override val value: Long
            get() = subpackets.map { it.value }.reduce { a, b -> a * b }
    }

    data class Minimum(override val version: Int, override val subpackets: List<Packet>) : Operator(2) {
        override val value: Long
            get() = subpackets.minOf { it.value }
    }

    data class Maximum(override val version: Int, override val subpackets: List<Packet>) : Operator(3) {
        override val value: Long
            get() = subpackets.maxOf { it.value }
    }

    data class GreaterThan(override val version: Int, override val subpackets: List<Packet>) : Operator(5) {
        init {
            require(subpackets.size == 2) { "Invalid number of subpackages for comparison operation" }
        }

        override val value: Long
            get() = if (subpackets.first().value > subpackets.last().value) 1 else 0
    }

    data class LessThan(override val version: Int, override val subpackets: List<Packet>) : Operator(6) {
        init {
            require(subpackets.size == 2) { "Invalid number of subpackages for comparison operation" }
        }

        override val value: Long
            get() = if (subpackets.first().value < subpackets.last().value) 1 else 0
    }

    data class EqualTo(override val version: Int, override val subpackets: List<Packet>) : Operator(7) {
        init {
            require(subpackets.size == 2) { "Invalid number of subpackages for comparison operation" }
        }

        override val value: Long
            get() = if (subpackets.first().value == subpackets.last().value) 1 else 0
    }
}
