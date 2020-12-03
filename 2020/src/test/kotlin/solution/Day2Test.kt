package solution

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

object Day2Test {

    private val INPUT = listOf("1-3 a: abcde", "1-3 b: cdefg", "2-9 c: ccccccccc")

    @Test
    fun `test part 1`() {
        Assertions.assertEquals("2", Day2.part1(INPUT))
        Assertions.assertEquals("500", Day2.part1(Day2.input))
    }

    @Test
    fun `test part 2`() {
        Assertions.assertEquals("1", Day2.part2(INPUT))
        Assertions.assertEquals("313", Day2.part2(Day2.input))
    }
}