package solution

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

object Day15Test {

    private val SAMPLE_INPUT = listOf("0,3,6")

    @Test
    fun `test part 1`() {
        Assertions.assertEquals("436", Day15.part1(SAMPLE_INPUT))
        Assertions.assertEquals("758", Day15.part1(Day15.input))
    }

    @Test
    fun `test part 2`() {
        Assertions.assertEquals("175594", Day15.part2(SAMPLE_INPUT))
        Assertions.assertEquals("814", Day15.part2(Day15.input))
    }
}