import solution.Day1
import solution.Day2
import solution.Day3

fun main() {
    val days = listOf(Day1, Day2, Day3)
    days.forEach { day ->
        println("Day ${day.number}:")
        println("\tpart1 = ${day.part1(day.input)}")
        println("\tpart2 = ${day.part2(day.input)}")
    }
}