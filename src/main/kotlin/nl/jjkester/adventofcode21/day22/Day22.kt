package nl.jjkester.adventofcode21.day22

import nl.jjkester.adventofcode21.boilerplate.*

object Day22 : Day {
    override val builder = day(22) {
        val instructions by input("instructions.txt") {
            lineSeparated().notBlank().strings().map { line ->
                val re = Regex("(?<onOrOff>on|off) x=(?<x0>-?\\d+)\\.\\.(?<x1>-?\\d+),\\s*y=(?<y0>-?\\d+)\\.\\.(?<y1>-?\\d+),\\s*z=(?<z0>-?\\d+)\\.\\.(?<z1>-?\\d+)")
                val matches = requireNotNull(re.matchEntire(line)) { "Invalid instruction: $line" }.groups

                Region(
                    matches["onOrOff"]!!.value == "on",
                    matches["x0"]!!.value.toInt()..matches["x1"]!!.value.toInt(),
                    matches["y0"]!!.value.toInt()..matches["y1"]!!.value.toInt(),
                    matches["z0"]!!.value.toInt()..matches["z1"]!!.value.toInt()
                )
            }
        }

        part {
            answer("Number of turned on cubes within -50..50") {
                turnedOnCubesInsideRegion(instructions, Region(false, -50..50, -50..50, -50..50))
            }
        }
        part {
            answer("Number of turned on cubes") {
                turnedOnCubesInsideRegion(instructions)
            }
        }
    }

    fun turnedOnCubesInsideRegion(instructions: List<Region>, region: Region? = null): Long {
        return instructions.asSequence()
            .mapNotNull { instruction ->
                if (region != null) {
                    instruction.limitTo(region)
                } else {
                    instruction
                }
            }
            .fold(emptySet<Region>()) { set, instruction ->
                set.withoutOverlapFrom(instruction).plus(instruction)
            }
            .asSequence()
            .filter { it.isOn }
            .sumOf { it.size }
    }

    fun Set<Region>.withoutOverlapFrom(excludedRegion: Region): Set<Region> {
        return asSequence().flatMap { includedRegion ->
            if (excludedRegion.overlapsWith(includedRegion)) {
                // Slice up the included region in separate regions, excluding the region that is turned off
                sequence {
                    // Keep remainder that is not split up
                    var remainder = includedRegion

                    if (excludedRegion.x.first > remainder.x.first) {
                        // Section not overlapping from the left (x-axis)
                        yield(remainder.copy(x = remainder.x.first until excludedRegion.x.first))
                        // We have covered x up until the start of the excluded region
                        remainder = remainder.copy(x = excludedRegion.x.first..remainder.x.last)
                    }
                    if (excludedRegion.x.last < remainder.x.last) {
                        // Section not overlapping from the right (x-axis)
                        yield(remainder.copy(x = excludedRegion.x.last afterUntil remainder.x.last))
                        // We have covered x outside of the overlapping area
                        remainder = remainder.copy(x = remainder.x.first..excludedRegion.x.last)
                    }
                    if (excludedRegion.y.first > remainder.y.first) {
                        // Section not overlapping from the bottom (y-axis)
                        yield(remainder.copy(y = remainder.y.first until excludedRegion.y.first))
                        // We have covered y up until the start of the excluded region
                        remainder = remainder.copy(y = excludedRegion.y.first..remainder.y.last)
                    }
                    if (excludedRegion.y.last < remainder.y.last) {
                        // Section not overlapping from the top (y-axis)
                        yield(remainder.copy(y = excludedRegion.y.last afterUntil remainder.y.last))
                        // We have covered y outside of the overlapping area
                        remainder = remainder.copy(y = remainder.y.first..excludedRegion.y.last)
                    }
                    if (excludedRegion.z.first > remainder.z.first) {
                        // Section not overlapping from the front (z-axis)
                        yield(remainder.copy(z = remainder.z.first until excludedRegion.z.first))
                        // We have covered z up until the start of the excluded region
                        remainder = remainder.copy(z = excludedRegion.z.first..remainder.z.last)
                    }
                    if (excludedRegion.z.last < remainder.z.last) {
                        // Section not overlapping from the back (z-axis)
                        yield(remainder.copy(z = excludedRegion.z.last afterUntil remainder.z.last))
                        // We have covered z outside of the overlapping area
                        remainder = remainder.copy(z = remainder.z.first..excludedRegion.z.last)
                    }
                }
            } else {
                // Keep it as-is
                sequenceOf(includedRegion)
            }
        }.toSet()
    }

    private infix fun Int.afterUntil(other: Int) = 1 + this..other
}
