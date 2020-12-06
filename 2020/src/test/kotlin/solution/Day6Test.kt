package solution

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

object Day6Test {

    private val SAMPLE_INPUT = listOf(
        "abc",
        "",
        "a",
        "b",
        "c",
        "",
        "ab",
        "ac",
        "",
        "a",
        "a",
        "a",
        "a",
        "",
        "b"
    )

    @Test
    fun `test part 1`() {
        Assertions.assertEquals("11", Day6.part1(SAMPLE_INPUT))
        Assertions.assertEquals("6799", Day6.part1(Day6.input))
    }

    @Test
    fun `test part 2`() {
        Assertions.assertEquals("6", Day6.part2(SAMPLE_INPUT))
        Assertions.assertEquals("3354", Day6.part2(Day6.input))
    }
}