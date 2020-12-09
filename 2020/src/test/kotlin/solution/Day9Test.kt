package solution

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

object Day9Test {

    private val SAMPLE_INPUT = listOf(
        "35",
        "20",
        "15",
        "25",
        "47",
        "40",
        "62",
        "55",
        "65",
        "95",
        "102",
        "117",
        "150",
        "182",
        "127",
        "219",
        "299",
        "277",
        "309",
        "576"
    )

    @Test
    fun `test part 1`() {
        Assertions.assertEquals("127", Day9.part1(SAMPLE_INPUT))
        Assertions.assertEquals("41682220", Day9.part1(Day9.input))
    }

    @Test
    fun `test part 2`() {
        Assertions.assertEquals("62", Day9.part2(SAMPLE_INPUT))
        Assertions.assertEquals("5388976", Day9.part2(Day9.input))
    }
}