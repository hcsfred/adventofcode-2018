package solution

import intcode.Computer

fun main() {
    val line = ClassLoader.getSystemResourceAsStream("day5.txt").bufferedReader().readLine()
    val program = line.split(",").map(String::toLong)
    println(Day5.solve(program, System.AIR_CONDITIONER))
    println(Day5.solve(program, System.THERMAL_RADIATOR))
}

object Day5 {
    fun solve(program: List<Long>, system: System): Long {
        val computer = Computer(ArrayList(program), system.id)
        return computer.runProgram()
    }
}

enum class System(val id: Long) {
    AIR_CONDITIONER(1),
    THERMAL_RADIATOR(5)
}