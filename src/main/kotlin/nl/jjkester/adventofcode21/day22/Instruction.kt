package nl.jjkester.adventofcode21.day22

data class Region(val isOn: Boolean, val x: IntRange, val y: IntRange, val z: IntRange) {
    val size: Long
        get() = x.count().toLong() * y.count().toLong() * z.count().toLong()
}

fun Region.overlapsWith(other: Region): Boolean {
    return x.overlapsWith(other.x) && y.overlapsWith(other.y) && z.overlapsWith(other.z)
}

fun Region.limitTo(other: Region): Region? {
    val newX = x.limitTo(other.x)
    val newY = y.limitTo(other.y)
    val newZ = z.limitTo(other.z)

    return if (newX.isEmpty() || newY.isEmpty() || newZ.isEmpty()) {
        null
    } else if (x == newX && y == newY && z == newZ) {
        this
    } else {
        copy(x = newX, y = newY, z = newZ)
    }
}

private fun IntRange.limitTo(other: IntRange): IntRange {
    return if (overlapsWith(other)) {
        maxOf(first, other.first)..minOf(last, other.last)
    } else {
        IntRange.EMPTY
    }
}

private fun IntRange.overlapsWith(other: IntRange) =
    other.first <= last && other.last >= first
