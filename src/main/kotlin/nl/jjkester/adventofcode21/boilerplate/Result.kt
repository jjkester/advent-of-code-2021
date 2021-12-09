package nl.jjkester.adventofcode21.boilerplate

import java.time.Duration

sealed interface Result {
    val duration: Duration
}

data class DayResult(
    val day: Int,
    val parts: List<PartResult>,
    val inputs: List<InputResult<*>>
) : Result {
    init {
        require(day in 1..31) { "Day must be a valid day in December" }
    }

    val answerDuration = parts.duration()
    val inputDuration = inputs.duration()
    override val duration: Duration = answerDuration.plus(inputDuration)
}

data class PartResult(
    val part: Int,
    val answers: List<AnswerResult<*>>
) : Result {
    init {
        require(part > 0) { "Part must be positive" }
    }

    override val duration: Duration
        get() = answers.map { it.duration }.reduce { first, second -> first.plus(second) }
}

data class InputResult<T : Any>(val contents: T, override val duration: Duration) : Result

data class AnswerResult<T : Number>(
    val description: String,
    val answer: T,
    override val duration: Duration
) : Result

private fun List<Result>.duration() = map { it.duration }.reduce { first, second -> first.plus(second) }
