import solution.*

fun main() {
    val days = listOf(Day1, Day2, Day3, Day4, Day5, Day6, Day7, Day8, Day9, Day10)
    days.forEach { day ->
        println("Day ${day.number}:")
        println("\tpart1 = ${day.part1(day.input)}")
        println("\tpart2 = ${day.part2(day.input)}")
    }
}