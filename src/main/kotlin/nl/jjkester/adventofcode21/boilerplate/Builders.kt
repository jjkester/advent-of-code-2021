package nl.jjkester.adventofcode21.boilerplate

@DslMarker
annotation class AdventMarker

@AdventMarker
class DayBuilder(val day: Int) {
    val parts = mutableListOf<PartBuilder>()
    val inputs = mutableMapOf<String, ResourceInput>()

    operator fun invoke() = Day(day, parts.map { it() })
}

@AdventMarker
class PartBuilder(private val part: Int) {
    val answers = mutableListOf<Answer>()

    operator fun invoke() = Part(part, answers)
}

@AdventMarker
fun day(number: Int, body: DayBuilder.() -> Unit) = DayBuilder(number)
    .apply(body)

@AdventMarker
fun DayBuilder.part(body: PartBuilder.() -> Unit) = PartBuilder(this.parts.size + 1)
    .apply(body)
    .apply(parts::add)

@AdventMarker
fun <T> DayBuilder.input(filename: String, transformation: Input.() -> T) =
    inputs.computeIfAbsent(filename) { ResourceInput("/day%02d/%s".format(day, it)) }
        .let { lazy { transformation(it) } }

@AdventMarker
fun PartBuilder.answer(description: String, body: () -> Number) = Answer(description, body)
    .apply(answers::add)
