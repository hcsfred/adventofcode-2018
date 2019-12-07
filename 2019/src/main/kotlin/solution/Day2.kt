package solution

import intcode.Computer

fun main() {
    val line = ClassLoader.getSystemResourceAsStream("day2.txt").bufferedReader().readLine()
    val program = line.split(",").map(String::toInt)
    println(Day2.part1(ArrayList(program)))
    println(Day2.part2(ArrayList(program)))
}

const val PART_TWO_TARGET = 19690720

object Day2 {

    fun part1(program: ArrayList<Int>): Int {
        val computer = Computer(program)
        return computer.solve(12, 2)
    }

    private fun Computer.solve(noun: Int, verb: Int): Int {
        initMemory(noun, verb)
        runProgram()
        return program.first()
    }

    fun part2(program: ArrayList<Int>): Int {
        val (noun, verb) = bruteforce(program)
        return 100 * noun + verb
    }

    private fun bruteforce(program: ArrayList<Int>): Pair<Int, Int> {
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