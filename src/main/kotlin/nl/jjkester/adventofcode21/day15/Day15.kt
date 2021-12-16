package nl.jjkester.adventofcode21.day15

import nl.jjkester.adventofcode21.boilerplate.*

object Day15 : Day {
    override val builder = day(15) {
        val matrix by input("matrix.txt") {
            lineSeparated().notBlank().map { line ->
                line.charSeparated().ints()
            }
        }

        part {
            answer("Lowest total risk of any path (single tile)") {
                lowestRiskForPathFromTopLeftToBottomRight(matrix)
            }
        }
        part {
            answer("Lowest total risk of any path (5x5 tiles)") {
                lowestRiskForPathFromTopLeftToBottomRightRepeated(matrix, 5)
            }
        }
    }

    fun lowestRiskForPathFromTopLeftToBottomRight(matrix: List<List<Int>>): Int {
        val coordinateMap = matrix.toCoordinateMap()

        return dijkstra(coordinateMap, 0 to 0, coordinateMap.bottomRight()!!).second
    }

    fun lowestRiskForPathFromTopLeftToBottomRightRepeated(matrix: List<List<Int>>, times: Int): Int {
        val coordinateMap = matrix.toCoordinateMap()

        val (maxX, maxY) = coordinateMap.bottomRight()!!

        val tiledCoordinateMap = (0 until times).flatMap { y ->
            val addY = y * (maxY + 1)
            (0 until times).flatMap { x ->
                val addX = x * (maxX + 1)
                coordinateMap
                    .map { (coordinate, cost) ->
                        (coordinate.first + addX to coordinate.second + addY) to maxOf(0, (cost + x + y - 1) % 9) + 1
                    }
            }
        }.toMap()

        return dijkstra(tiledCoordinateMap, 0 to 0, tiledCoordinateMap.bottomRight()!!).second
    }

    fun dijkstra(
        nodesWithCost: Map<Pair<Int, Int>, Int>,
        source: Pair<Int, Int>,
        destination: Pair<Int, Int>
    ): Pair<List<Pair<Int, Int>>, Int> {

        require(source in nodesWithCost.keys) { "Source outside problem space" }
        require(destination in nodesWithCost.keys) { "Destination outside problem space" }

        val visited = mutableSetOf<Pair<Int, Int>>() // A
        val found = mutableMapOf(source to (listOf(source) to 0)) // d
        val queued = mutableSetOf(source) // X

        while (queued.isNotEmpty()) {
            val node = queued.minByOrNull { found[it]!!.second }!! // x
            val (pathToNode, costToNode) = found[node]!!

            // Found path and cost is final
            queued.remove(node)
            visited.add(node)

            nodesWithCost.keys.adjacent(node).filter { it !in visited }.forEach { adjacentNode -> // z
                queued.add(adjacentNode) // no-op when present

                // Compute newly found path with associated cost estimate
                val newPathAndCost = pathToNode.plus(adjacentNode) to costToNode + nodesWithCost[adjacentNode]!!

                // Choose path with lowest cost estimate from new path and existing path, if any
                found.merge(adjacentNode, newPathAndCost) { first, second ->
                    listOf(first, second).minByOrNull { it.second }!!
                }
            }
        }

        assert(nodesWithCost.size == visited.size && queued.isEmpty()) { "Not all nodes were visited" }
        assert(destination in found) { "No path found to the requested destination" }

        return found[destination]!!
    }

    fun <T> Iterable<Iterable<T>>.toCoordinateMap() = this
        .flatMapIndexed { y, row ->
            row.mapIndexed { x, value ->
                (x to y) to value
            }
        }
        .toMap()

    private fun Map<Pair<Int, Int>, Int>.bottomRight() = keys.maxByOrNull { it.first + it.second }

    private fun Iterable<Pair<Int, Int>>.adjacent(cur: Pair<Int, Int>) = listOf(
        cur.first - 1 to cur.second,
        cur.first + 1 to cur.second,
        cur.first to cur.second - 1,
        cur.first to cur.second + 1
    )
        .filter { it in this }
}
