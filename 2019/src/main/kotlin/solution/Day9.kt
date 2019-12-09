package solution

import intcode.Computer

fun main() {
    val line = ClassLoader.getSystemResourceAsStream("day9.txt").bufferedReader().readLine()
    val program = line.split(",").map(String::toLong)
    println(Day9.solve(program, setting = 1))
    println(Day9.solve(program, setting = 2))
}

object Day9 {
    fun solve(program: List<Long>, setting: Long = -1): Long {
        val computer = Computer(ArrayList(program), setting)
        return computer.runProgram()
    }
}