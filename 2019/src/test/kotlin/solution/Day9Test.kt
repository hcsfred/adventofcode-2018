package solution

import intcode.Computer
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

object Day9Test {

    @Test
    fun `test produces copy of itself`() {
        val program = arrayListOf<Long>(109, 1, 204, -1, 1001, 100, 1, 100, 1008, 100, 16, 101, 1006, 101, 0, 99)
        val computer = Computer(ArrayList(program))
        for (output in program) {
            Assertions.assertEquals(computer.runTest(), output)
        }
    }

    @Test
    fun `test outputs 16 digit number`() {
        val program = "1102,34915192,34915192,7,4,7,99,0".split(",").map(String::toLong)
        Assertions.assertEquals(Day9.solve(program).toString().length, 16)
    }

    @Test
    fun `test outputs number in middle`() {
        val output = 1125899906842624
        val program = "104,$output,99".split(",").map(String::toLong)
        Assertions.assertEquals(Day9.solve(program), output)
    }
}