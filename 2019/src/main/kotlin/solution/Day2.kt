package solution

import intcode.Computer

fun main() {
    val line = ClassLoader.getSystemResourceAsStream("day2.txt").bufferedReader().readLine()
    val program = line.split(",").map(String::toLong)
    println(Day2.part1(ArrayList(program)))
    println(Day2.part2(ArrayList(program)))
}


object Day2 {

    private const val PART_TWO_TARGET = 19690720L

    fun part1(program: ArrayList<Long>): Long {
        val computer = Computer(program)
        return computer.solve(12, 2)
    }

    private fun Computer.solve(noun: Int, verb: Int): Long {
        initMemory(noun, verb)
        runProgram()
        return memory.first()
    }

    fun part2(program: ArrayList<Long>): Int {
        val (noun, verb) = bruteforce(program)
        return 100 * noun + verb
    }

    private fun bruteforce(program: ArrayList<Long>): Pair<Int, Int> {
        val range = 0..99
        for (noun in range) {
            for (verb in range) {
                val computer = Computer(ArrayList(program))
                if (computer.solve(noun, verb) == PART_TWO_TARGET) {
                    return noun to verb
                }
            }
        }
        error("Failed to bruteforce")
    }
}