package solution

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

object Day3Test {

    @Test
    fun `test manhattan distance of closest intersection`() {
        Assertions.assertEquals(
            Day3.part1(
                Wire("R75,D30,R83,U83,L12,D49,R71,U7,L72"),
                Wire("U62,R66,U55,R34,D71,R55,D58,R83")
            ), 159)
        Assertions.assertEquals(
            Day3.part1(
                Wire("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51"),
                Wire("U98,R91,D20,R16,D67,R40,U7,R15,U6,R7")
            ), 135)
    }

    @Test
    fun `test fewest combined steps for intersection`() {
        Assertions.assertEquals(
            Day3.part2(
                Wire("R75,D30,R83,U83,L12,D49,R71,U7,L72"),
                Wire("U62,R66,U55,R34,D71,R55,D58,R83")
            ), 610)
        Assertions.assertEquals(
            Day3.part2(
                Wire("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51"),
                Wire("U98,R91,D20,R16,D67,R40,U7,R15,U6,R7")
            ), 410)
    }
}