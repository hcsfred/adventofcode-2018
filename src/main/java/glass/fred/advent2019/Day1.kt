package glass.fred.advent2019

import com.google.common.io.Resources
import java.io.File
import kotlin.math.floor

fun main() {
    val input = File(Resources.getResource("2019/day1.txt").toURI())
    val masses = input.readLines().map { it.toInt() }
    println(Day1.part1(masses))
    println(Day1.part2(masses))
}

object Day1 {

    fun part1(masses: List<Int>) = masses.sumBy { fuelRequired(it) }

    private fun fuelRequired(mass: Int) = floor(mass / 3.0).toInt() - 2

    fun part2(masses: List<Int>) = masses.sumBy { recursive(fuelRequired(it)) }

    private fun recursive(value: Int): Int {
        val fuel = fuelRequired(value)
        return if (fuel <= 0) value else value + recursive(fuel)
    }
}