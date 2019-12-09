package solution

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

object Day1Test {

    @Test
    fun `test fuel required`() {
        Assertions.assertEquals(Day1.fuelRequired(12), 2)
        Assertions.assertEquals(Day1.fuelRequired(14), 2)
        Assertions.assertEquals(Day1.fuelRequired(1969), 654)
        Assertions.assertEquals(Day1.fuelRequired(100756), 33583)
    }

    @Test
    fun `test total fuel required`() {
        Assertions.assertEquals(Day1.totalFuelRequired(14), 2)
        Assertions.assertEquals(Day1.totalFuelRequired(1969), 966)
        Assertions.assertEquals(Day1.totalFuelRequired(100756), 50346)
    }
}