package solution

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

object Day12Test {

    private val SAMPLE_INPUT = listOf("F10", "N3", "F7", "R90", "F11")

    @Test
    fun `test part 1`() {
        Assertions.assertEquals("25", Day12.part1(SAMPLE_INPUT))
        Assertions.assertEquals("1186", Day12.part1(Day12.input))
    }

    @Test
    fun `test part 2`() {
        Assertions.assertEquals("286", Day12.part2(SAMPLE_INPUT))
        Assertions.assertEquals("47806", Day12.part2(Day12.input))
    }
}