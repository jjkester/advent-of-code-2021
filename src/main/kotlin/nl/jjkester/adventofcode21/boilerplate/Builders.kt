package nl.jjkester.adventofcode21.boilerplate

import java.time.Duration
import kotlin.system.measureTimeMillis

@DslMarker
annotation class AdventMarker

sealed interface Builder<T : Result> {
    operator fun invoke(): T
}

@AdventMarker
class DayBuilder(val day: Int) : Builder<DayResult> {
    private val parts = mutableListOf<PartBuilder>()
    private val inputs = mutableListOf<InputBuilder<*>>()

    override operator fun invoke(): DayResult {
        val loadedInputs = inputs.map { it() }
        val computedParts = parts.map { it() }
        return DayResult(day, computedParts, loadedInputs)
    }

    @AdventMarker
    fun <T : Any> input(filename: String, transformation: Input.() -> T): Lazy<T> =
        InputBuilder(ProcessedInput(ResourceInput("/day%02d/%s".format(day, filename)), transformation))
            .apply(inputs::add)
            .let { lazy { requireNotNull(it.result) { "Input not ready" }.contents } }

    @AdventMarker
    fun part(body: PartBuilder.() -> Unit) = PartBuilder(this.parts.size + 1)
        .apply(body)
        .apply(parts::add)
}

@AdventMarker
class PartBuilder(private val part: Int) : Builder<PartResult> {
    private val answers = mutableListOf<AnswerBuilder<*>>()

    override operator fun invoke() = PartResult(part, answers.map { it() })

    @AdventMarker
    fun answer(description: String, body: () -> Any) {
        answers.add(AnswerBuilder(description, body))
    }
}

@AdventMarker
class InputBuilder<T : Any>(private val input: ProcessedInput<T>) : Builder<InputResult<T>> {
    var result: InputResult<T>? = null
        private set

    override operator fun invoke() = input().also { result = it }
}

@AdventMarker
class AnswerBuilder<T : Any>(private val description: String, private val solution: () -> T) : Builder<AnswerResult<T>> {
    override operator fun invoke(): AnswerResult<T> {
        val answer: T
        val timeInMs = measureTimeMillis { answer = solution() }
        return AnswerResult(description, answer, Duration.ofMillis(timeInMs))
    }
}

@AdventMarker
fun day(number: Int, body: DayBuilder.() -> Unit) = DayBuilder(number)
    .apply(body)
