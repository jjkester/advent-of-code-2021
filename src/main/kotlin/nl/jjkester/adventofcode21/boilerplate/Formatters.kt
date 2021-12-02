package nl.jjkester.adventofcode21.boilerplate

fun Day.format() = listOf(toString())
    .plus(this.map { it.format().prependIndent() })
    .joinToString(System.lineSeparator())

fun Part.format() = listOf(toString())
    .plus(this.map { it.format().prependIndent() })
    .joinToString(System.lineSeparator())

fun Answer.format() = toString()
