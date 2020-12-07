package solution

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

object Day7Test {

    private val SAMPLE_INPUT = listOf(
        "light red bags contain 1 bright white bag, 2 muted yellow bags.",
        "dark orange bags contain 3 bright white bags, 4 muted yellow bags.",
        "bright white bags contain 1 shiny gold bag.",
        "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.",
        "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.",
        "dark olive bags contain 3 faded blue bags, 4 dotted black bags.",
        "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.",
        "faded blue bags contain no other bags.",
        "dotted black bags contain no other bags."
    )

    @Test
    fun `test part 1`() {
        Assertions.assertEquals("4", Day7.part1(SAMPLE_INPUT))
        Assertions.assertEquals("289", Day7.part1(Day7.input))
    }

    @Test
    fun `test part 2`() {
        Assertions.assertEquals("32", Day7.part2(SAMPLE_INPUT))
        Assertions.assertEquals("30055", Day7.part2(Day7.input))
    }
}