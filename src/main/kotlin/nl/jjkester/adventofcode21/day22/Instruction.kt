package nl.jjkester.adventofcode21.day22

data class Instruction(val isOn: Boolean, val region: Region)

data class Region(val x: IntRange, val y: IntRange, val z: IntRange) : Iterable<Cube> {
    override fun iterator() = iterator {
        x.forEach { xi ->
            y.forEach { yi ->
                z.forEach { zi ->
                    yield(Cube(xi, yi, zi))
                }
            }
        }
    }
}

data class Cube(val x: Int, val y: Int, val z: Int)

fun Instruction.limitTo(region: Region): Instruction? {
    val newRegion = this.region.limitTo(region)

    return if (newRegion == null) {
        null
    } else if (region == newRegion) {
        this
    } else {
        copy(region = newRegion)
    }
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
    return if (other.first <= last || other.last >= first) {
        maxOf(first, other.first)..minOf(last, other.last)
    } else {
        IntRange.EMPTY
    }
}
