package nl.jjkester.adventofcode21.boilerplate

interface Input {
    val contents: String?
    val available get() = contents != null
}

class StringInput(override val contents: String) : Input

class ResourceInput(private val resourcePath: String) : Input {
    override val contents by lazy {
        {}.javaClass.getResource(resourcePath)?.readText()
    }
}

fun Input.sections() = contents?.split(System.lineSeparator() + System.lineSeparator())
    ?.map { StringInput(it) } ?: emptyList()

fun Input.text() = contents ?: ""

fun Input.lines() = contents?.split(System.lineSeparator()) ?: emptyList()

fun Input.notEmptyLines() = lines().filter(String::isNotEmpty)

fun Input.ints() = notEmptyLines().map(String::toInt)

fun Input.commaSeparated() = lines().map { it.split(Regex(", *")) }
    .flatten()
    .joinToString(System.lineSeparator())
    .let { StringInput(it) }

fun Input.spaceSeparated() = lines().map { it.split(Regex(" *")) }
    .joinToString(System.lineSeparator())
    .let { StringInput(it) }
