package solution

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

object Day3Test {

    private val INPUT = listOf(
        "..##.......",
        "#...#...#..",
        ".#....#..#.",
        "..#.#...#.#",
        ".#...##..#.",
        "..#.##.....",
        ".#.#.#....#",
        ".#........#",
        "#.##...#...",
        "#...##....#",
        ".#..#...#.#",
    )

    @Test
    fun `test part 1`() {
        Assertions.assertEquals("7", Day3.part1(INPUT))
        Assertions.assertEquals("234", Day3.part1(Day3.input))
    }

    @Test
    fun `test part 2`() {
        Assertions.assertEquals("336", Day3.part2(INPUT))
        Assertions.assertEquals("5813773056", Day3.part2(Day3.input))
    }
}