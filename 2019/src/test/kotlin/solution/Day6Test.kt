package solution

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

object Day6Test {

    private val map = arrayListOf("COM)B", "B)C", "C)D", "D)E", "E)F", "B)G", "G)H", "D)I", "E)J", "J)K", "K)L")

    @Test
    fun `test total number of orbits`() {
        Assertions.assertEquals(Day6.part1(map), 42)
    }

    @Test
    fun `test minimum number of orbital transfers`() {
        val extended = ArrayList(map)
        extended.add("K)YOU")
        extended.add("I)SAN")
        Assertions.assertEquals(Day6.part2(extended), 4)
    }
}