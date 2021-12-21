package nl.jjkester.adventofcode21.day19

class Solver(val minimumOverlap: Int) {

    fun alignScanners(scanners: List<Scanner>): Map<Scanner, Coordinate> {
        // Assume the first scanner to be at 0, 0, 0
        val visited = mutableMapOf(scanners.first() to Coordinate.zero)
        val queued = scanners.drop(1).toMutableList()

        while (queued.isNotEmpty()) {
            // Store size to break if we cannot find any (more) matches to break endless loop
            val sizeBeforeIteration = queued.size

            queued.removeIf { newScanner ->
                visited.forEach { (knownScanner, location) ->
                    val equivalences = findEquivalentMeasurements(knownScanner, newScanner)

                    // 12 equal beacons is sufficient to state that the scanners are equal
                    if (equivalences.size >= minimumOverlap) {
                        val (vector, translation) = findTranslation(equivalences)
                        val translatedScanner = newScanner.copy(measurements = newScanner.measurements.map(translation))

                        visited[translatedScanner] = location + vector
                        return@removeIf true
                    }
                }
                false
            }

            assert(queued.size != sizeBeforeIteration) { "Scanners do not scan a contiguous area" }
        }

        return visited
    }

    private fun findEquivalentMeasurements(
        knownScanner: Scanner,
        newScanner: Scanner
    ): List<Pair<Coordinate, Coordinate>> {
        return knownScanner.distances
            .mapNotNull { (knownMeasurement, knownMeasurementDistances) ->
                newScanner.distances.entries.asSequence()
                    .filter { (_, newMeasurementDistances) ->
                        // When any already fixed point has 12 distances of the same length to a point with unknown
                        // location, it is assumed to be the same point
                        // (If the assumption holds for scanners, it probably does for beacons.)
                        knownMeasurementDistances.intersect(newMeasurementDistances).size >= minimumOverlap
                    }
                    .map { knownMeasurement to it.key }
                    .singleOrNull()
            }
    }

    private fun findTranslation(equivalences: List<Pair<Coordinate, Coordinate>>): Pair<Vector, Translation> {
        return Coordinate.translations.asSequence()
            .mapNotNull { translation ->
                // Apply the translation to all equivalent pairs, keep distinct vectors.
                // Translation is valid if all equivalent pairs have the same distance.
                equivalences.map { (first, second) -> first - translation(second) }
                    .toSet()
                    .singleOrNull()
                    ?.let { it to translation }
            }
            .first()
    }
}
