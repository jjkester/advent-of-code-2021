package nl.jjkester.adventofcode21.boilerplate

@DslMarker
annotation class AdventMarker

@AdventMarker
class DayBuilder(val day: Int) {
    val parts = mutableListOf<PartBuilder>()
    val inputs = mutableMapOf<String, Input>()

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
fun DayBuilder.input(filename: () -> String): Input =
    inputs.computeIfAbsent(filename.invoke()) { Input("/day%02d/%s".format(day, it)) }

@AdventMarker
fun PartBuilder.answer(description: String, body: () -> Int) = Answer(description, body)
    .apply(answers::add)
