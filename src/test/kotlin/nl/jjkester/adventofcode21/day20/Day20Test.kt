package nl.jjkester.adventofcode21.day20

import nl.jjkester.adventofcode21.day20.Day20.asAlgorithm
import nl.jjkester.adventofcode21.day20.Day20.asImage
import nl.jjkester.adventofcode21.day20.Day20.display
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day20Test {
    val image = listOf("#..#.", "#....", "##..#", "..#..", "..###").asImage()
    val algorithm = ("..#.#..#####.#.#.#.###.##.....###.##.#..###.####..#####..#....#..#..##..##" +
            "#..######.###...####..#..#####..##..#.#####...##.#.#..#.##..#.#......#.###" +
            ".######.###.####...#.##.##..#..#..#####.....#.#....###..#.##......#.....#." +
            ".#..#..##..#...##.######.####.####.#.#...#.......#..#.#.#...####.##.#....." +
            ".#..#...##.#.##..#...##.#.##..###.#......#.#.......#.#.#.####.###.##...#.." +
            "...####.#..#..#.##.#....##..#.####....##...##..#...#......#.#.......#....." +
            "..##..####..#...#.#.#...##..#.#..###..#####........#..####......#..#").asAlgorithm()

    @Test
    fun enhance() {
        assertThat(Day20.enhance(image, algorithm))
            .isEqualTo(listOf(".##.##.", "#..#.#.", "##.#..#", "####..#", ".#..##.", "..##..#", "...#.#.").asImage(-1 to -1))
    }

    @Test
    fun reallyEnhance() {
        val result = Day20.reallyEnhance(image, algorithm)
        assertThat(result)
            .hasSize(35)
        assertThat(result.display)
            .isEqualTo("""
                .......#.
                .#..#.#..
                #.#...###
                #...##.#.
                #.....#.#
                .#.#####.
                ..#.#####
                ...##.##.
                ....###..
            """.trimIndent())
        assertThat(Day20.reallyEnhance(emptySet(), List(512) { true }))
            .isEqualTo(listOf(
                "#########",
                "#########",
                "#########",
                "#########",
                "#########",
                "#########",
                "#########",
                "#########",
                "#########",
            ).asImage(-4 to -4))
    }

    @Test
    fun reallyReallyEnhance() {
        assertThat(Day20.reallyReallyEnhance(image, algorithm))
            .hasSize(3351)
    }
}
