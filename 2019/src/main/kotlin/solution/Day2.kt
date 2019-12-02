package solution

fun main() {
    val line = ClassLoader.getSystemResourceAsStream("day2.txt").bufferedReader().readLine()
    val program = line.split(",").map { it.toInt() }
    println(Day2.part1(ArrayList(program)))
    println(Day2.part2(ArrayList(program)))
}

const val INSTRUCTION_LENGTH = 4
const val PART_TWO_TARGET = 19690720

object Day2 {

    fun part1(program: ArrayList<Int>): Int {
        program.initMemory(12, 2)
        return program.parse()
    }

    private fun ArrayList<Int>.initMemory(noun: Int, verb: Int) {
        set(1, noun)
        set(2, verb)
    }

    private fun ArrayList<Int>.parse(): Int {
        for (i in indices step INSTRUCTION_LENGTH) {
            when (Opcode[get(i)]) {
                Opcode.HALT -> return first()
                Opcode.ADD -> executeInstruction(i, Int::plus)
                Opcode.MULTIPLY -> executeInstruction(i, Int::times)
            }
        }
        return -1
    }

    private fun ArrayList<Int>.executeInstruction(index: Int, operation: (Int, Int) -> Int) {
        set(get(index + 3), operation(getForOffset(index + 1), getForOffset(index + 2)))
    }
    private fun ArrayList<Int>.getForOffset(offset: Int) = get(get(offset))

    fun part2(program: ArrayList<Int>): Int {
        val (noun, verb) = bruteforce(program)
        return 100 * noun + verb
    }

    private fun bruteforce(program: ArrayList<Int>): Pair<Int, Int> {
        val range = 0..99
        for (noun in range) {
            for (verb in range) {
                val copy = ArrayList(program)
                copy.initMemory(noun, verb)
                if (copy.parse() == PART_TWO_TARGET) {
                    return noun to verb
                }
            }
        }
        return -1 to -1
    }
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