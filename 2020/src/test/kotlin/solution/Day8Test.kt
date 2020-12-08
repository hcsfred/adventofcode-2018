package solution

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

object Day8Test {

    private val SAMPLE_INPUT = listOf(
        "nop +0",
        "acc +1",
        "jmp +4",
        "acc +3",
        "jmp -3",
        "acc -99",
        "acc +1",
        "jmp -4",
        "acc +6"
    )

    @Test
    fun `test part 1`() {
        Assertions.assertEquals("5", Day8.part1(SAMPLE_INPUT))
        Assertions.assertEquals("1782", Day8.part1(Day8.input))
    }

    @Test
    fun `test part 2`() {
        Assertions.assertEquals("8", Day8.part2(SAMPLE_INPUT))
        Assertions.assertEquals("797", Day8.part2(Day8.input))
    }
}