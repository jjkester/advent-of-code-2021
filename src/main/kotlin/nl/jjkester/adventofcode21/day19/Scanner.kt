package nl.jjkester.adventofcode21.day19

import kotlin.math.absoluteValue

data class Scanner(val name: String, val measurements: List<Coordinate>) {
    val distances by lazy {
        measurements.associateWith { measurement ->
            measurements.map { (measurement - it).length }.toSet()
        }
    }
}

typealias Translation = Coordinate.() -> Coordinate

data class Coordinate(val x: Int, val y: Int, val z: Int) {

    operator fun plus(other: Coordinate) = Coordinate(x + other.x, y + other.y, z + other.z)

    operator fun plus(other: Vector) = Coordinate(x + other.x, y + other.y, z + other.z)

    operator fun minus(other: Coordinate) = Vector(x - other.x, y - other.y, z - other.z)

    companion object {
        val zero = Coordinate(0, 0, 0)

        private val flips = setOf<Translation>(
            { this },
            { Coordinate(x, y, -z) },
            { Coordinate(x, -y, z) },
            { Coordinate(x, -y, -z) },
            { Coordinate(-x, y, z) },
            { Coordinate(-x, y, -z) },
            { Coordinate(-x, -y, z) },
            { Coordinate(-x, -y, -z) },
        )

        private val rotations = setOf<Translation>(
            { this },
            { Coordinate(x, z, y) },
            { Coordinate(y, x, z) },
            { Coordinate(y, z, x) },
            { Coordinate(z, x, y) },
            { Coordinate(z, y, x) },
        )

        private val permutations = flips.flatMap { flip ->
            rotations.map { rotate ->
                { c: Coordinate -> c.flip().rotate() }
            }
        }.toSet()

        val translations = permutations
    }
}

data class Vector(val x: Int, val y: Int, val z: Int) {
    val length: Int
        get() = x * x + y * y + z * z
    
    val manhattan: Int
        get() = x.absoluteValue + y.absoluteValue + z.absoluteValue
}
