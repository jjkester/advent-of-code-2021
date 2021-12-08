package nl.jjkester.adventofcode21.boilerplate

fun <T> List<T>.toPair(): Pair<T, T> {
    require(this.size == 2)
    return first() to last()
}
