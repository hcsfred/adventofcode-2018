package solution

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

object Day14Test {

    private val SAMPLE_INPUT = listOf(
        "mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X",
        "mem[8] = 11",
        "mem[7] = 101",
        "mem[8] = 0"
    )

    private val SAMPLE_INPUT_2 = listOf(
        "mask = 000000000000000000000000000000X1001X",
        "mem[42] = 100",
        "mask = 00000000000000000000000000000000X0XX",
        "mem[26] = 1"
    )

    @Test
    fun `test part 1`() {
        Assertions.assertEquals("165", Day14.part1(SAMPLE_INPUT))
        Assertions.assertEquals("2346881602152", Day14.part1(Day14.input))
    }

    @Test
    fun `test part 2`() {
        Assertions.assertEquals("208", Day14.part2(SAMPLE_INPUT_2))
        Assertions.assertEquals("3885232834169", Day14.part2(Day14.input))
    }
}