package nl.jjkester.adventofcode21.boilerplate

interface Day {
    val builder: DayBuilder
}

fun Day.main() = println(builder().format())
