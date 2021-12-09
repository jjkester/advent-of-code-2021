package nl.jjkester.adventofcode21.boilerplate

import java.time.Duration

fun DayResult.format() = listOf("Day $day [${duration.format()}]")
    .plusElement("Input (${inputs.size}) [${inputDuration.format()}]".prependIndent())
    .plus(parts.map { it.format().prependIndent() })
    .joinToString(System.lineSeparator())

fun PartResult.format() = listOf("Part $part [${duration.format()}]")
    .plus(answers.map { it.format().prependIndent() })
    .joinToString(System.lineSeparator())

fun AnswerResult<*>.format() = "$description = $answer"

private fun Duration.format() = "${toMillis()} ms"
