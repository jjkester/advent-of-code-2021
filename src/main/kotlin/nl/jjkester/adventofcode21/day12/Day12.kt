package nl.jjkester.adventofcode21.day12

import nl.jjkester.adventofcode21.boilerplate.*

object Day12 : Day {
    override val builder = day(12) {
        val edges by input("edges.txt") {
            lineSeparated()
                .notBlank()
                .map { line ->
                    line.dashSeparated()
                        .map { it.string() }
                        .toPair()
                }
        }

        part {
            answer("Number of paths visiting small caves at most once") {
                numberOfPathsVisitingSmallCaves(edges, false)
            }
        }
        part {
            answer("Number of paths visiting small caves at most twice") {
                numberOfPathsVisitingSmallCaves(edges, true)
            }
        }
    }

    fun numberOfPathsVisitingSmallCaves(edges: List<Pair<String, String>>, allowedVisitSmallTwice: Boolean): Int {
        return paths(edges, allowedVisitSmallTwice).size
    }

    fun paths(edges: List<Pair<String, String>>, allowedVisitSmallTwice: Boolean): List<List<String>> {
        return visit(listOf("start"), edges.toEdgeMap(), allowedVisitSmallTwice)
    }

    private fun List<Pair<String, String>>.toEdgeMap() =
        flatMap { listOf(it, it.second to it.first) }
            .distinct()
            .groupBy { it.first }
            .map { (k, v) ->
                k to v.map { it.second }.toSet()
            }
            .toMap()

    private fun visit(
        path: List<String>,
        edges: Map<String, Set<String>>,
        allowedVisitSmallTwice: Boolean
    ): List<List<String>> {
        // Current node is last of path
        require(path.isNotEmpty())
        val node = path.last()

        // We're not exiting the end node again
        if (node.isEndNode()) {
            return listOf(path)
        }

        // Assure we can only visit a single small node twice, short-circuit or determine
        val allowedVisitSmallTwiceNext = allowedVisitSmallTwice && path.filter { it.isSmallCave() }.let { it.distinct().size == it.size }

        return edges[node]
            ?.filter { next ->
                if (next.isSmallCave()) {
                    if (allowedVisitSmallTwiceNext) {
                        // Only visit the start node once
                        !next.isStartNode()
                    } else {
                        // Already visited one small node twice, so next must be unique
                        next !in path
                    }
                } else {
                    // We can visit large nodes as many times as we like (apparently, this could cause cycles normally)
                    true
                }
            }
            // All paths from this node
            ?.flatMap { visit(path.plusElement(it), edges, allowedVisitSmallTwiceNext) }
        // If we did not reach the end node and there are no further paths, disregard this path
            ?: emptyList()
    }

    private fun String.isStartNode() = this == "start"

    private fun String.isEndNode() = this == "end"

    private fun String.isSmallCave() = all { it.isLowerCase() }

    @JvmStatic
    fun main(args: Array<String>) {
        Day12.main()
    }
}
