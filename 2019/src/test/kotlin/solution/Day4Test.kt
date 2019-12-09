package solution

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import solution.Day4.isValid

object Day4Test {

    @Test
    fun `test double digits and ascending`() {
        Assertions.assertTrue(111111.toList().isValid())
        Assertions.assertFalse(223450.toList().isValid())
        Assertions.assertFalse(123789.toList().isValid())
    }

    @Test
    fun `test double digits not part of larger group`() {
        Assertions.assertTrue(112233.toList().isValid(part2 = true))
        Assertions.assertTrue(111122.toList().isValid(part2 = true))
        Assertions.assertFalse(123444.toList().isValid(part2 = true))
    }
}