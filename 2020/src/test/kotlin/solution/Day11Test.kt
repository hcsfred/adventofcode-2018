package solution

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

object Day11Test {

    private val SAMPLE_INPUT = listOf(
        "L.LL.LL.LL",
        "LLLLLLL.LL",
        "L.L.L..L..",
        "LLLL.LL.LL",
        "L.LL.LL.LL",
        "L.LLLLL.LL",
        "..L.L.....",
        "LLLLLLLLLL",
        "L.LLLLLL.L",
        "L.LLLLL.LL"
    )

    @Test
    fun `test part 1`() {
        Assertions.assertEquals("37", Day11.part1(SAMPLE_INPUT))
        Assertions.assertEquals("2183", Day11.part1(Day11.input))
    }

    @Test
    fun `test part 2`() {
        Assertions.assertEquals("26", Day11.part2(SAMPLE_INPUT))
        Assertions.assertEquals("1990", Day11.part2(Day11.input))
    }
}