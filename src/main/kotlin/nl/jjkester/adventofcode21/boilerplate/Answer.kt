package nl.jjkester.adventofcode21.boilerplate

import kotlin.system.measureTimeMillis

class Answer(val description: String, private val solution: () -> Any) {
    private val result by lazy {
        var answer: Any
        val timeInMs = measureTimeMillis {
            answer = solution()
        }
        answer to timeInMs
    }

    val value by lazy {
        result.first
    }

    val timeInMs by lazy {
        result.second
    }

    override fun toString(): String {
        return "$description = $value (took $timeInMs ms)"
    }
}
