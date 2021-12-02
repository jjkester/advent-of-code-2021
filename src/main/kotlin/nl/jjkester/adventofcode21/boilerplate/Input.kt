package nl.jjkester.adventofcode21.boilerplate

class Input(private val resourcePath: String) {
    val contents by lazy {
        {}.javaClass.getResource(resourcePath)?.readText()
    }
    val available get() = contents != null

    fun text() = contents ?: ""

    fun lines() = contents?.split(System.lineSeparator()) ?: emptyList()

    fun ints() = lines().filter(String::isNotEmpty).map(String::toInt)
}
