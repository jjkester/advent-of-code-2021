package nl.jjkester.adventofcode21.day10

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day10Test {
    private val code = listOf(
        "[({(<(())[]>[[{[]{<()<>>",
        "[(()[<>])]({[<{<<[]>>(",
        "{([(<{}[<>[]}>{[]{[(<()>",
        "(((({<>}<{<{<>}{[]{[]{}",
        "[[<[([]))<([[{}[[()]]]",
        "[{[{({}]{}}([{[{{{}}([]",
        "{<[[]]>}<{[{[{[]{()[[[]",
        "[<(<(<(<{}))><([]([]()",
        "<{([([[(<>()){}]>(<<{{",
        "<{([{{}}[<[[[<>{}]]]>[]]",
    )

    @Test
    fun syntaxErrorScore() {
        assertThat(Day10.syntaxErrorScore(code))
            .isEqualTo(26397L)
    }

    @Test
    fun middleScore() {
        assertThat(Day10.middleScore(code))
            .isEqualTo(288957)
    }
}
