package solution

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

object Day10Test {

    private val SAMPLE_INPUT = listOf("16", "10", "15", "5", "1", "11", "7", "19", "6", "12", "4")

    private val SAMPLE_INPUT_2 = listOf(
        "28", "33", "18", "42", "31", "14", "46", "20",
        "48", "47", "24", "23", "49", "45", "19", "38",
        "39", "11", "1", "32", "25", "35", "8", "17",
        "7", "9", "4", "2", "34", "10", "3"
    )

    @Test
    fun `test part 1`() {
        Assertions.assertEquals("35", Day10.part1(SAMPLE_INPUT))
        Assertions.assertEquals("220", Day10.part1(SAMPLE_INPUT_2))
        Assertions.assertEquals("2812", Day10.part1(Day10.input))
    }

    @Test
    fun `test part 2`() {
        Assertions.assertEquals("8", Day10.part2(SAMPLE_INPUT))
        Assertions.assertEquals("19208", Day10.part2(SAMPLE_INPUT_2))
        Assertions.assertEquals("386869246296064", Day10.part2(Day10.input))
    }
}