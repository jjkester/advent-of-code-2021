package nl.jjkester.adventofcode21.day19

import nl.jjkester.adventofcode21.boilerplate.*

object Day19 : Day {
    override val builder = day(19) {
        val scanners by input("scanners.txt") { parseScanners() }

        part {
            answer("Number of beacons") {
                countBeacons(scanners)
            }
        }
        part {
            answer("Largest manhattan distance") {
                largestDistance(scanners)
            }
        }
    }

    fun countBeacons(scanners: List<Scanner>): Int {
        return Solver(12).alignScanners(scanners)
            .flatMap { (scanner, location) ->
                scanner.measurements.map { it + location }
            }
            .distinct().size
    }

    fun largestDistance(scanners: List<Scanner>): Int {
        val scannerLocations = Solver(12).alignScanners(scanners)
            .map { it.value }

        return scannerLocations.flatMap { first ->
            scannerLocations.map { second ->
                (first - second).manhattan
            }
        }.maxOrNull()!!
    }

    fun Input.parseScanners() = sectionSeparated()
        .map { section ->
            val lines = section.lineSeparated()
                .notBlank()
            val name = lines.first().string().trim(' ', '-')
            lines.skip(1).map { line ->
                line.commaSeparated()
                    .ints()
                    .let { ints ->
                        require(ints.size == 3)
                        val (x, y, z) = ints
                        Coordinate(x, y, z)
                    }
            }
                .let { Scanner(name, it) }

        }
}
