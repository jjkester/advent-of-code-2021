package nl.jjkester.adventofcode21.day22

import nl.jjkester.adventofcode21.boilerplate.*

object Day22 : Day {
    override val builder = day(22) {
        val instructions by input("instructions.txt") {
            lineSeparated().notBlank().strings().map { line ->
                val re = Regex("(?<onOrOff>on|off) x=(?<x0>-?\\d+)\\.\\.(?<x1>-?\\d+),\\s*y=(?<y0>-?\\d+)\\.\\.(?<y1>-?\\d+),\\s*z=(?<z0>-?\\d+)\\.\\.(?<z1>-?\\d+)")
                val matches = requireNotNull(re.matchEntire(line)) { "Invalid instruction: $line" }.groups

                Instruction(
                    matches["onOrOff"]!!.value == "on",
                    Region(
                        matches["x0"]!!.value.toInt()..matches["x1"]!!.value.toInt(),
                        matches["y0"]!!.value.toInt()..matches["y1"]!!.value.toInt(),
                        matches["z0"]!!.value.toInt()..matches["z1"]!!.value.toInt()
                    )
                )
            }
        }

        part {
            answer("Number of turned on cubes within -50..50") {
                turnedOnCubesInsideRegion(instructions, Region(-50..50, -50..50, -50..50))
            }
        }
        part {
            answer("Number of turned on cubes") {
                turnedOnCubesInsideRegion(instructions)
            }
        }
    }

    fun turnedOnCubesInsideRegion(instructions: List<Instruction>, region: Region? = null): Int {
        return instructions.asSequence()
            .mapNotNull { instruction ->
                if (region != null) {
                    instruction.limitTo(region)
                } else {
                    instruction
                }
            }
            .fold(emptySet<Cube>()) { set, instruction ->
                if (instruction.isOn) {
                    set + instruction.region
                } else {
                    set - instruction.region
                }
            }
            .size
    }
}
