package nl.jjkester.adventofcode21.boilerplate

abstract class Section<T : Section<T, C>, C : Any> : Comparable<T>, Iterable<C> {
    protected abstract val number: Int
    protected abstract val collection: Iterable<C>

    override fun toString() = "${javaClass.simpleName} $number"

    override fun compareTo(other: T) = number.compareTo(other.number)

    override fun iterator(): Iterator<C> = collection.iterator()
}
