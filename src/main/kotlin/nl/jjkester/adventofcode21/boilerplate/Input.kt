package nl.jjkester.adventofcode21.boilerplate

import java.lang.Integer.max
import java.lang.Integer.min

interface Input {
    val contents: String
}

class StringInput(override val contents: String) : Input

class ResourceInput(val resourcePath: String) : Input {
    private val resource by lazy {
        requireNotNull({}.javaClass.getResource(resourcePath)) { "Resource not found: $resourcePath" }
    }

    override val contents by lazy {
        resource.readText()
    }
}

// Splitters

fun Input.sectionSeparated(): List<Input> = contents
    .splitOnSectionSeparator()
    .toInputList()

fun Input.lineSeparated(): List<Input> = contents
    .splitOnLineSeparator()
    .toInputList()

fun Input.commaSeparated(): List<Input> = contents
    .splitOnRegex(Regex(",\\s*", RegexOption.MULTILINE))
    .toInputList()

fun Input.whitespaceSeparated(): List<Input> = contents
    .splitOnRegex(Regex("\\s+", RegexOption.MULTILINE))
    .toInputList()

fun Input.arrowSeparated(): List<Input> = contents
    .splitOnRegex(Regex("\\s*->\\s*"))
    .toInputList()

// Filters

fun List<Input>.notEmpty(): List<Input> = filter { it.string().isNotEmpty() }

fun List<Input>.notBlank(): List<Input> = filter { it.string().isNotBlank() }

fun List<Input>.skip(n: Int): List<Input> = subList(min(this.size, n), this.size)

fun List<Input>.head(size: Int): List<Input> = subList(0, min(size, this.size))

fun List<Input>.tail(size: Int): List<Input> = subList(max(0, this.size - size), this.size)

// Processors

fun Input.string(): String = contents

fun Input.int(): Int = contents.trim().toInt()

fun List<Input>.strings(): List<String> = map(Input::string)

fun List<Input>.ints(): List<Int> = map(Input::int)

// Private utilities

private fun String?.splitOnLineSeparator() = this?.split(System.lineSeparator()) ?: emptyList()

private fun String?.splitOnSectionSeparator() = this?.split(System.lineSeparator().repeat(2)) ?: emptyList()

private fun String?.splitOnRegex(regex: Regex) = this?.split(regex) ?: emptyList()

private fun Iterable<String>?.toInputList() = this?.map(::StringInput) ?: emptyList()
