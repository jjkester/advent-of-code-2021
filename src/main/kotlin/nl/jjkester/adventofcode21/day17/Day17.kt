package nl.jjkester.adventofcode21.day17

import nl.jjkester.adventofcode21.boilerplate.*

object Day17 : Day {
    override val builder = day(17)  {
        val targetArea by input("target.txt") {
            val str = lineSeparated().first().string()
            val components = Regex("^target area: x=(?<x0>-?\\d+)\\.\\.(?<x1>-?\\d+), y=(?<y0>-?\\d+)\\.\\.(?<y1>-?\\d+)$").matchEntire(str)?.groups

            requireNotNull(components) { "No valid target area found in input" }

            val x = listOf(components["x0"]!!, components["x1"]!!).map { it.value }
            val y = listOf(components["y0"]!!, components["y1"]!!).map { it.value }

            (x.minOf { it.toInt() } to y.maxOf { it.toInt() }) to (x.maxOf { it.toInt() } to y.minOf { it.toInt() })
        }

        part {
            answer("Highest reached y position") {
                highestTrajectory(targetArea).second.maxOf { it.second }
            }
        }
        part {
            answer("Number of valid velocity values") {
                numberOfValidVelocities(targetArea)
            }
        }
    }

    fun highestTrajectory(targetArea: Pair<Position, Position>): Pair<Velocity, List<Position>> {
        return determineTrajectories(targetArea).maxByOrNull { it.key.second }!!.toPair()
    }

    fun numberOfValidVelocities(targetArea: Pair<Position, Position>): Int {
        return determineTrajectories(targetArea).size
    }

    private fun determineTrajectories(targetArea: Pair<Position, Position>): Map<Velocity, List<Position>> {
        val validSetups = mutableMapOf<Velocity, List<Position>>()

        val startLocation = 0 to 0
        val velocities = (0..targetArea.second.first) to (targetArea.second.second..1000) // arbitrary max y velocity

        velocities.second.forEach { y ->
            velocities.first.forEach { x ->
                val velocity = x to y

                val path = simulate(startLocation, targetArea, velocity)
                val lastPosition = path.last()

                if (lastPosition in targetArea) {
                    validSetups[velocity] = path
                }
            }
        }

        return validSetups
    }

    private fun simulate(startLocation: Position, targetArea: Pair<Position, Position>, velocity: Velocity): List<Position> {
        val trajectory = mutableListOf(startLocation)
        var probe = Probe(startLocation, velocity)

        while (targetArea reachableFrom probe.position && probe.position !in targetArea) {
            probe = probe.step()
            trajectory.add(probe.position)
        }

        return trajectory
    }

    private infix fun Pair<Position, Position>.reachableFrom(position: Position): Boolean {
        return position.first <= second.first && position.second >= second.second
    }

    private operator fun Pair<Position, Position>.contains(position: Position): Boolean {
        return position.first in range(first.first, second.first) && position.second in range(first.second, second.second)
    }

    private fun range(first: Int, second: Int): IntRange {
        return minOf(first, second)..maxOf(first, second)
    }

    private fun Probe.step(): Probe {
        return Probe(position.applyVelocity(velocity), velocity.applyForces())
    }

    private fun Position.applyVelocity(velocity: Velocity): Position {
        return first + velocity.first to second + velocity.second
    }

    private fun Velocity.applyForces(): Velocity {
        val x = if (first < 0) {
            first + 1
        } else if (first > 0) {
            first - 1
        } else {
            first
        }

        return x to second - 1
    }
}
