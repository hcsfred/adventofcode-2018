abstract class Day(val number: Int) {

    val input = ClassLoader.getSystemResourceAsStream("day${number}.txt").bufferedReader().readLines()

    abstract fun part1(input: List<String>): String

    abstract fun part2(input: List<String>): String
}