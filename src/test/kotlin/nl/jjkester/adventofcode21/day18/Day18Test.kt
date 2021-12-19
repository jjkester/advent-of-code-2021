package nl.jjkester.adventofcode21.day18

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day18Test {
    @Test
    fun computeFinalSum() {
        val numbers1 = listOf("[1,1]", "[2,2]", "[3,3]", "[4,4]")
        val numbers2 = listOf("[1,1]", "[2,2]", "[3,3]", "[4,4]", "[5,5]")
        val numbers3 = listOf("[1,1]", "[2,2]", "[3,3]", "[4,4]", "[5,5]", "[6,6]")
        val numbers4 = listOf(
            "[[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]]",
            "[7,[[[3,7],[4,3]],[[6,3],[8,8]]]]",
            "[[2,[[0,8],[3,4]]],[[[6,7],1],[7,[1,6]]]]",
            "[[[[2,4],7],[6,[0,5]]],[[[6,8],[2,8]],[[2,1],[4,5]]]]",
            "[7,[5,[[3,8],[1,4]]]]",
            "[[2,[2,2]],[8,[8,1]]]",
            "[2,9]",
            "[1,[[[9,3],9],[[9,0],[0,7]]]]",
            "[[[5,[7,4]],7],1]",
            "[[[[4,2],2],6],[8,7]]",
        )
        assertThat(Day18.computeFinalSum(numbers1.parse()))
            .isEqualTo(SnailfishNumber.parse("[[[[1,1],[2,2]],[3,3]],[4,4]]"))
        assertThat(Day18.computeFinalSum(numbers2.parse()))
            .isEqualTo(SnailfishNumber.parse("[[[[3,0],[5,3]],[4,4]],[5,5]]"))
        assertThat(Day18.computeFinalSum(numbers3.parse()))
            .isEqualTo(SnailfishNumber.parse("[[[[5,0],[7,4]],[5,5]],[6,6]]"))
        assertThat(Day18.computeFinalSum(numbers4.parse()))
            .isEqualTo(SnailfishNumber.parse("[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]"))
    }

    @Test
    fun magnitudeOfFinalSum() {
        val numbers = listOf(
            "[[[0,[5,8]],[[1,7],[9,6]]],[[4,[1,2]],[[1,4],2]]]",
            "[[[5,[2,8]],4],[5,[[9,9],0]]]",
            "[6,[[[6,2],[5,6]],[[7,6],[4,7]]]]",
            "[[[6,[0,7]],[0,9]],[4,[9,[9,0]]]]",
            "[[[7,[6,4]],[3,[1,3]]],[[[5,5],1],9]]",
            "[[6,[[7,3],[3,2]]],[[[3,8],[5,7]],4]]",
            "[[[[5,4],[7,7]],8],[[8,3],8]]",
            "[[9,3],[[9,9],[6,[4,9]]]]",
            "[[2,[[7,7],7]],[[5,8],[[9,3],[0,2]]]]",
            "[[[[5,2],5],[8,[3,7]]],[[5,[7,5]],[4,4]]]",
        )
        assertThat(Day18.magnitudeOfFinalSum(numbers.parse()))
            .isEqualTo(4140)
    }

    @Test
    fun largestMagnitudeAddingTwo() {
        val numbers = listOf(
            "[[[0,[5,8]],[[1,7],[9,6]]],[[4,[1,2]],[[1,4],2]]]",
            "[[[5,[2,8]],4],[5,[[9,9],0]]]",
            "[6,[[[6,2],[5,6]],[[7,6],[4,7]]]]",
            "[[[6,[0,7]],[0,9]],[4,[9,[9,0]]]]",
            "[[[7,[6,4]],[3,[1,3]]],[[[5,5],1],9]]",
            "[[6,[[7,3],[3,2]]],[[[3,8],[5,7]],4]]",
            "[[[[5,4],[7,7]],8],[[8,3],8]]",
            "[[9,3],[[9,9],[6,[4,9]]]]",
            "[[2,[[7,7],7]],[[5,8],[[9,3],[0,2]]]]",
            "[[[[5,2],5],[8,[3,7]]],[[5,[7,5]],[4,4]]]",
        )
        assertThat(Day18.largestMagnitudeAddingTwo(numbers.parse()))
            .isEqualTo(3993)
    }

    private fun List<String>.parse() = map { SnailfishNumber.parse(it) }
}
