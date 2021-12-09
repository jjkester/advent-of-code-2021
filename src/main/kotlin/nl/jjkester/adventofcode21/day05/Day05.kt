package nl.jjkester.adventofcode21.day05

import nl.jjkester.adventofcode21.boilerplate.*

object Day05 : Day {
    override val builder = day(5) {
        val lines by input("lines.txt") {
            lineSeparated()
                .notEmpty()
                .map { line ->
                    line.arrowSeparated()
                        .map { coordinates ->
                            coordinates.commaSeparated()
                                .ints()
                                .toPair()
                        }
                        .toPair()
                }
                .toLines()
        }

        part {
            answer("Points where at least two horizontal and/or vertical lines overlap") {
                pointsWhereAtLeastTwoNotDiagonalOverlap(lines)
            }
        }
        part {
            answer("Points where at least two horizontal, vertical and/or diagonal lines overlap") {
                pointsWhereAtLeastTwoOverlap(lines)
            }
        }
    }

    fun pointsWhereAtLeastTwoNotDiagonalOverlap(lines: List<Line>): Int = lines
        .filter { it.isHorizontal || it.isVertical }
        .run(this::pointsWhereAtLeastTwoOverlap)

    fun pointsWhereAtLeastTwoOverlap(lines: List<Line>): Int = lines
        .run(this::overlappingPoints)
        .filter { (_, count) -> count >= 2 }
        .count()


    fun overlappingPoints(lines: List<Line>) = lines
            .flatten()
            .groupingBy { it }
            .eachCount()

    fun List<Pair<Pair<Int, Int>, Pair<Int, Int>>>.toLines() = map { it.toLine() }

    private fun Pair<Pair<Int, Int>, Pair<Int, Int>>.toLine() = Line(first.toPoint(), second.toPoint())

    private fun Pair<Int, Int>.toPoint() = Point(first, second)

    @JvmStatic
    fun main(args: Array<String>) {
        Day05.main()
    }
}
