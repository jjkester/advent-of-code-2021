package nl.jjkester.adventofcode21.boilerplate

class Day(override val number: Int, override val collection: Iterable<Part>) : Section<Day, Part>() {
    val day = number
    val parts = collection
}
