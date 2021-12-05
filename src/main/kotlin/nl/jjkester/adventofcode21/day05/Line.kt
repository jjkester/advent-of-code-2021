package nl.jjkester.adventofcode21.day05

import kotlin.math.absoluteValue

data class Point(val x: Int, val y: Int)

data class Line(val start: Point, val end: Point) : Iterable<Point> {
    val isHorizontal = start.x == end.x
    val isVertical = start.y == end.y
    val isDiagonal = (start.x - end.x).absoluteValue == (start.y - end.y).absoluteValue

    override fun iterator(): Iterator<Point> {
        require(isHorizontal || isVertical || isDiagonal) { "Line must be horizontal, vertical, or diagonal at exactly 45 degrees" }

        return if (isHorizontal) {
            (start.y upOrDownTo end.y)
                .map { Point(start.x, it) }
                .iterator()
        } else if (isVertical) {
            (start.x upOrDownTo end.x)
                .map { Point(it, start.y) }
                .iterator()
        } else if (isDiagonal) {
            val xs = (start.x upOrDownTo end.x).toList()
            val ys = (start.y upOrDownTo end.y).toList()

            List(xs.size) { Point(xs[it], ys[it]) }
                .iterator()
        } else {
            emptyList<Point>().iterator()
        }
    }
}

infix fun Int.upOrDownTo(other: Int) = if (this > other) {
    this downTo other
} else {
    this..other
}
