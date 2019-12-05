package solution

fun main() {
    val line = ClassLoader.getSystemResourceAsStream("day2.txt").bufferedReader().readLine()
    val program = line.split(",").map(String::toInt)
    println(Day2.part1(ArrayList(program)))
    println(Day2.part2(ArrayList(program)))
}

const val INSTRUCTION_LENGTH = 4
const val PART_TWO_TARGET = 19690720

object Day2 {

    fun part1(program: ArrayList<Int>): Int {
        val computer = Computer(program)
        computer.initMemory(12, 2)
        return computer.parseProgram()
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
                computer.initMemory(noun, verb)
                if (computer.parseProgram() == PART_TWO_TARGET) {
                    return noun to verb
                }
            }
        }
        return -1 to -1
    }
}

class Computer(private val program: ArrayList<Int>) {

    fun initMemory(noun: Int, verb: Int) {
        program[1] = noun
        program[2] = verb
    }

    fun parseProgram(): Int {
        for (i in program.indices step INSTRUCTION_LENGTH) {
            when (Opcode[program[i]]) {
                Opcode.ADD -> executeInstruction(i, Int::plus)
                Opcode.MULTIPLY -> executeInstruction(i, Int::times)
                else -> return program.first()
            }
        }
        return -1
    }

    private fun executeInstruction(index: Int, operation: (Int, Int) -> Int) {
        program[program[index + 3]] = operation(program.getForOffset(index + 1), program.getForOffset(index + 2))
    }

    private fun ArrayList<Int>.getForOffset(offset: Int) = get(get(offset))
}

enum class Opcode(val value: Int) {
    ADD(1),
    MULTIPLY(2),
    HALT(99);

    companion object {
        private val map = values().associateBy(Opcode::value)
        operator fun get(opcode: Int) = map[opcode]
    }
}