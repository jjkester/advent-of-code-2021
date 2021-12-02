package nl.jjkester.adventofcode21.boilerplate

class Part(override val number: Int, override val collection: Iterable<Answer>)  : Section<Part, Answer>() {
    val part = number
    val answers = collection
}
