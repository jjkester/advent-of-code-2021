package nl.jjkester.adventofcode21.day18

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import kotlin.math.ceil
import kotlin.math.floor

sealed class SnailfishNumber {
    abstract val magnitude: Int

    fun add(other: SnailfishNumber): SnailfishNumber {
        return Pair(this, other)
    }

    fun reduce(): SnailfishNumber {
        var result = this

        while (true) {
            val exploded = result.explode()

            if (exploded !== result) {
                result = exploded
                continue
            }

            val split = exploded.split()

            if (split !== result) {
                result = split
                continue
            }

            break
        }

        return result
    }

    fun explode(): SnailfishNumber {
        return explodeInternal(0).first
    }

    fun split(): SnailfishNumber {
        return splitInternal().first
    }

    abstract fun splitInternal(): kotlin.Pair<SnailfishNumber, Boolean>

    protected abstract fun explodeInternal(depth: Int): kotlin.Pair<SnailfishNumber, kotlin.Pair<Int, Int>?>

    protected abstract fun addLeft(value: Int): SnailfishNumber

    protected abstract fun addRight(value: Int): SnailfishNumber

    data class Leaf(val value: Int) : SnailfishNumber() {
        override val magnitude: Int
            get() = value

        override fun explodeInternal(depth: Int): kotlin.Pair<SnailfishNumber, kotlin.Pair<Int, Int>?> {
            return this to null
        }

        override fun splitInternal(): kotlin.Pair<SnailfishNumber, Boolean> {
            return if (value >= 10) {
                Pair(Leaf(floor(value.toDouble() / 2).toInt()), Leaf(ceil(value.toDouble() / 2).toInt())) to true
            } else {
                this to false
            }
        }

        override fun addLeft(value: Int): SnailfishNumber {
            return if (value == 0) {
                this
            } else {
                Leaf(this.value + value)
            }
        }

        override fun addRight(value: Int): SnailfishNumber {
            return if (value == 0) {
                this
            } else {
                Leaf(this.value + value)
            }
        }

        override fun toString(): String {
            return value.toString()
        }
    }

    data class Pair(val x: SnailfishNumber, val y: SnailfishNumber) : SnailfishNumber() {
        override val magnitude: Int
            get() = 3 * x.magnitude + 2 * y.magnitude

        override fun explodeInternal(depth: Int): kotlin.Pair<SnailfishNumber, kotlin.Pair<Int, Int>?> {
            return if (depth == 4) {
                require(x is Leaf && y is Leaf) { "Trying to explode a pair consisting of other pairs" }
                Leaf(0) to (x.value to y.value)
            } else {
                explodeLeft(depth) ?: explodeRight(depth) ?: (this to null)
            }
        }

        override fun splitInternal(): kotlin.Pair<SnailfishNumber, Boolean> {
            return splitLeft() ?: splitRight() ?: (this to false)
        }

        override fun addLeft(value: Int): SnailfishNumber {
            return if (value == 0) {
                this
            } else {
                Pair(x.addLeft(value), y)
            }
        }

        override fun addRight(value: Int): SnailfishNumber {
            return if (value == 0) {
                this
            } else {
                Pair(x, y.addRight(value))
            }
        }

        private fun explodeLeft(depth: Int): kotlin.Pair<SnailfishNumber, kotlin.Pair<Int, Int>?>? {
            val (new, exploded) = x.explodeInternal(depth + 1)

            return exploded?.let { Pair(new, y.addLeft(it.second)) to (it.first to 0) }
        }

        private fun explodeRight(depth: Int): kotlin.Pair<SnailfishNumber, kotlin.Pair<Int, Int>?>? {
            val (new, exploded) = y.explodeInternal(depth + 1)

            return exploded?.let { Pair(x.addRight(it.first), new) to (0 to it.second) }
        }

        private fun splitLeft(): kotlin.Pair<SnailfishNumber, Boolean>? {
            val (new, split) = x.splitInternal()

            return if (split) {
                Pair(new, y) to true
            } else {
                null
            }
        }

        private fun splitRight(): kotlin.Pair<SnailfishNumber, Boolean>? {
            val (new, split) = y.splitInternal()

            return if (split) {
                Pair(x, new) to true
            } else {
                null
            }
        }

        override fun toString(): String {
            return "[$x,$y]"
        }
    }

    companion object {
        fun parse(value: String): SnailfishNumber {
            return Gson().fromJson(value, JsonArray::class.java).toSnailfishNumber()
        }

        private fun JsonElement.toSnailfishNumber(): SnailfishNumber = when (this) {
            is JsonArray -> {
                assert(size() == 2)
                Pair(first().toSnailfishNumber(), last().toSnailfishNumber())
            }
            is JsonPrimitive -> {
                assert(isNumber)
                Leaf(asInt)
            }
            else -> throw IllegalStateException()
        }
    }
}

operator fun SnailfishNumber.plus(other: SnailfishNumber): SnailfishNumber {
    val added = add(other)
    return added.reduce()
}
