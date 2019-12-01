package solution

fun main() {
    val masses = ClassLoader.getSystemResourceAsStream("day1.txt").bufferedReader().readLines().map { it.toInt() }
    println(Day1.part1(masses))
    println(Day1.part2(masses))
}

object Day1 {

    fun part1(masses: List<Int>) = masses.sumBy(::fuelRequired)

    private fun fuelRequired(mass: Int) = (mass / 3) - 2

    fun part2(masses: List<Int>) = masses.sumBy(::totalFuelRequired)

    private fun totalFuelRequired(value: Int): Int {
        val fuel = fuelRequired(value)
        return if (fuel < 0) 0 else fuel + totalFuelRequired(fuel)
    }
}