package solution

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

object Day5Test {

    private val SAMPLE_INPUT = listOf("FBFBBFFRLR", "BFFFBBFRRR", "FFFBBBFRRR", "BBFFBBFRLL")

    @Test
    fun `test part 1`() {
        Assertions.assertEquals("820", Day5.part1(SAMPLE_INPUT))
        Assertions.assertEquals("938", Day5.part1(Day5.input))
    }

    @Test
    fun `test part 2`() {
        Assertions.assertEquals("696", Day5.part2(Day5.input))
    }
}