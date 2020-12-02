package solution

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

object Day1Test {

    private val ENTRIES = listOf(1721, 979, 366, 299, 675, 1456)

    @Test
    fun `test part 1`() {
        Assertions.assertEquals(514579, Day1.part1(ENTRIES))
    }

    @Test
    fun `test part 2`() {
        Assertions.assertEquals(241861950, Day1.part2(ENTRIES))
    }
}