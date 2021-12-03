package nl.jjkester.adventofcode21.boilerplate

class Input(private val resourcePath: String) {
    val contents by lazy {
        {}.javaClass.getResource(resourcePath)?.readText()
    }
    val available get() = contents != null

    fun text() = contents ?: ""

    fun lines() = contents?.split(System.lineSeparator()) ?: emptyList()

    fun notEmptyLines() = lines().filter(String::isNotEmpty)

    fun ints() = notEmptyLines().map(String::toInt)
}
