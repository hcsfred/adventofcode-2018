package solution

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

object Day1Test {

    private val INPUT = listOf("1721", "979", "366", "299", "675", "1456")

    @Test
    fun `test part 1`() {
        Assertions.assertEquals("514579", Day1.part1(INPUT))
        Assertions.assertEquals("982464", Day1.part1(Day1.input))
    }

    @Test
    fun `test part 2`() {
        Assertions.assertEquals("241861950", Day1.part2(INPUT))
        Assertions.assertEquals("162292410", Day1.part2(Day1.input))
    }
}