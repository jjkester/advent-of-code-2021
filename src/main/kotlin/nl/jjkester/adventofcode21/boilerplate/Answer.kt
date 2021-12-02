package nl.jjkester.adventofcode21.boilerplate

class Answer(val description: String, solution: () -> Any) {
    val value by lazy(solution)

    override fun toString(): String {
        return "$description = $value"
    }
}
