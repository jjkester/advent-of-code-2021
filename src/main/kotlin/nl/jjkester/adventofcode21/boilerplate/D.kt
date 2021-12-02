package nl.jjkester.adventofcode21.boilerplate

interface D {
    val day: DayBuilder
}

fun D.main() = println(day().format())
