package nl.jjkester.adventofcode21.day18

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SnailfishNumberKtTest {
    @Test
    fun parse() {
        assertThat(SnailfishNumber.parse("[[[[[4,3],4],4],[7,[[8,4],9]]],[1,1]]"))
            .isEqualTo(
                SnailfishNumber.Pair(
                    SnailfishNumber.Pair(
                        SnailfishNumber.Pair(
                            SnailfishNumber.Pair(
                                SnailfishNumber.Pair(
                                    SnailfishNumber.Leaf(4),
                                    SnailfishNumber.Leaf(3)
                                ),
                                SnailfishNumber.Leaf(4)
                            ),
                            SnailfishNumber.Leaf(4)
                        ),
                        SnailfishNumber.Pair(
                            SnailfishNumber.Leaf(7),
                            SnailfishNumber.Pair(
                                SnailfishNumber.Pair(
                                    SnailfishNumber.Leaf(8),
                                    SnailfishNumber.Leaf(4)
                                ),
                                SnailfishNumber.Leaf(9)
                            ),
                        ),
                    ),
                    SnailfishNumber.Pair(
                        SnailfishNumber.Leaf(1),
                        SnailfishNumber.Leaf(1)
                    )
                )
            )
    }

    @Test
    fun add() {
        assertThat(SnailfishNumber.parse("[[[[4,3],4],4],[7,[[8,4],9]]]").add(SnailfishNumber.parse("[1,1]")))
            .isEqualTo(SnailfishNumber.parse("[[[[[4,3],4],4],[7,[[8,4],9]]],[1,1]]"))
    }

    @Test
    fun reduce() {
        assertThat(SnailfishNumber.parse("[[[[[4,3],4],4],[7,[[8,4],9]]],[1,1]]").reduce())
            .isEqualTo(SnailfishNumber.parse("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]"))
    }

    @Test
    fun explode() {
        assertThat(SnailfishNumber.parse("[[[[[9,8],1],2],3],4]").explode())
            .isEqualTo(SnailfishNumber.parse("[[[[0,9],2],3],4]"))
        assertThat(SnailfishNumber.parse("[7,[6,[5,[4,[3,2]]]]]").explode())
            .isEqualTo(SnailfishNumber.parse("[7,[6,[5,[7,0]]]]"))
        assertThat(SnailfishNumber.parse("[[6,[5,[4,[3,2]]]],1]").explode())
            .isEqualTo(SnailfishNumber.parse("[[6,[5,[7,0]]],3]"))
        assertThat(SnailfishNumber.parse("[[3,[2,[1,[7,3]]]],[6,[5,[4,[3,2]]]]]").explode())
            .isEqualTo(SnailfishNumber.parse("[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]"))
        assertThat(SnailfishNumber.parse("[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]").explode())
            .isEqualTo(SnailfishNumber.parse("[[3,[2,[8,0]]],[9,[5,[7,0]]]]"))
    }

    @Test
    fun split() {
        assertThat(SnailfishNumber.parse("[10,11]").split())
            .isEqualTo(SnailfishNumber.parse("[[5,5],11]"))
        assertThat(SnailfishNumber.parse("[10,11]").split().split())
            .isEqualTo(SnailfishNumber.parse("[[5,5],[5,6]]"))
    }

    @Test
    fun plus() {
        assertThat(SnailfishNumber.parse("[[[[4,3],4],4],[7,[[8,4],9]]]") + SnailfishNumber.parse("[1,1]"))
            .isEqualTo(SnailfishNumber.parse("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]"))
        assertThat(SnailfishNumber.parse("[[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]]") + SnailfishNumber.parse("[7,[[[3,7],[4,3]],[[6,3],[8,8]]]]"))
            .isEqualTo(SnailfishNumber.parse("[[[[4,0],[5,4]],[[7,7],[6,0]]],[[8,[7,7]],[[7,9],[5,0]]]]"))
    }

    @Test
    fun magnitude() {
        assertThat(SnailfishNumber.parse("[9,1]").magnitude)
            .isEqualTo(29)
        assertThat(SnailfishNumber.parse("[[9,1],[1,9]]").magnitude)
            .isEqualTo(129)
        assertThat(SnailfishNumber.parse("[[1,2],[[3,4],5]]").magnitude)
            .isEqualTo(143)
        assertThat(SnailfishNumber.parse("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]").magnitude)
            .isEqualTo(1384)
        assertThat(SnailfishNumber.parse("[[[[1,1],[2,2]],[3,3]],[4,4]]").magnitude)
            .isEqualTo(445)
        assertThat(SnailfishNumber.parse("[[[[3,0],[5,3]],[4,4]],[5,5]]").magnitude)
            .isEqualTo(791)
        assertThat(SnailfishNumber.parse("[[[[5,0],[7,4]],[5,5]],[6,6]]").magnitude)
            .isEqualTo(1137)
        assertThat(SnailfishNumber.parse("[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]").magnitude)
            .isEqualTo(3488)
    }
}
