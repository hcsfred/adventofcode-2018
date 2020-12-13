package solution

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

object Day13Test {

    private val SAMPLE_INPUT = listOf("939", "7,13,x,x,59,x,31,19")

    @Test
    fun `test part 1`() {
        Assertions.assertEquals("295", Day13.part1(SAMPLE_INPUT))
        Assertions.assertEquals("2845", Day13.part1(Day13.input))
    }

    @Test
    fun `test part 2`() {
        Assertions.assertEquals("1068781", Day13.part2(SAMPLE_INPUT))
        Assertions.assertEquals("487905974205117", Day13.part2(Day13.input))
    }
}