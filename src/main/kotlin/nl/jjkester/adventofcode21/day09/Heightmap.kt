package nl.jjkester.adventofcode21.day09

data class Heightmap(private val heights: List<List<Int>>) : Iterable<Pair<Int, Int>> {
    init {
        require(heights.distinctBy { it.size }.size == 1) { "Not all rows are equal length" }
    }

    val sizeX = heights.firstOrNull()?.size ?: 0
    val sizeY = heights.size

    val coordinates by lazy {
        (0 until sizeX).flatMap { x ->
            (0 until sizeY).map { y ->
                x to y
            }
        }
    }

    operator fun get(index: Pair<Int, Int>) = heights.getOrNull(index.second)?.getOrNull(index.first)

    override fun iterator() = coordinates.iterator()

    val rows: List<List<Int>>
        get() = heights

    val columns: List<List<Int>>
        get() = (0 until sizeX).map { col -> heights.map { it[col] } }
}
