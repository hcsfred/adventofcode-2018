package solution

fun main() {
    val line = ClassLoader.getSystemResourceAsStream("day5.txt").bufferedReader().readLine()
    val program = line.split(",").map(String::toInt)
    println(Day5.solve(program, System.AIR_CONDITIONER))
    println(Day5.solve(program, System.THERMAL_RADIATOR))
}

object Day5 {
    fun solve(program: List<Int>, system: System): Int {
        val computer = AdvancedComputer(ArrayList(program))
        return computer.parseProgram(system.id)
    }
}

class AdvancedComputer(private val program: ArrayList<Int>) { // TODO: refactor

    fun parseProgram(id: Int): Int {
        var pointer = 0
        var diagnostic = -1

        while (pointer < program.size) {
            val instruction = program[pointer]
            val (opcode, modes) = readInstruction(instruction)
            val indices = getIndices(opcode, modes, pointer)
            pointer += opcode.pointerSize

            when (opcode) {
                AdvancedOpcode.ADD -> program[indices[2]] = program[indices[0]] + program[indices[1]]

                AdvancedOpcode.MULTIPLY -> program[indices[2]] = program[indices[0]] * program[indices[1]]

                AdvancedOpcode.INPUT -> program[indices[0]] = id

                AdvancedOpcode.OUTPUT -> diagnostic = program[indices[0]]

                AdvancedOpcode.JUMP_IF_TRUE -> if (program[indices[0]] != 0) pointer = program[indices[1]]

                AdvancedOpcode.JUMP_IF_FALSE -> if (program[indices[0]] == 0) pointer = program[indices[1]]

                AdvancedOpcode.LESS_THAN -> {
                    program[indices[2]] = if (program[indices[0]] < program[indices[1]]) 1 else 0
                }

                AdvancedOpcode.EQUALS -> {
                    program[indices[2]] = if (program[indices[0]] == program[indices[1]]) 1 else 0
                }

                AdvancedOpcode.HALT -> return diagnostic
            }
        }
        return -1
    }

    private fun readInstruction(instruction: Int): Pair<AdvancedOpcode, List<Int>> {
        val list = instruction.toList()
        if (list.size == 1) {
            return AdvancedOpcode[instruction] to emptyList()
        }
        val opcode = list.subList(list.size - 2, list.size).joinToString("").toInt() // Final two digits
        val modes = list.subList(0, list.size - 2).reversed() // Read right to left
        return AdvancedOpcode[opcode] to modes
    }

    private fun getIndices(opcode: AdvancedOpcode, modes: List<Int>, pointer: Int): ArrayList<Int> {
        val indices = ArrayList<Int>()
        for (i in 1 until opcode.pointerSize) {
            val mode = ParameterMode[modes.getOrElse(i - 1) { 0 }]
            val index = when(mode) {
                ParameterMode.POSITION -> program[pointer + i]
                ParameterMode.IMMEDIATE -> pointer + i
            }
            indices.add(index)
        }
        return indices
    }
}

enum class AdvancedOpcode(val value: Int, val pointerSize: Int) {
    ADD(1, 4),
    MULTIPLY(2, 4),
    INPUT(3, 2),
    OUTPUT(4, 2),
    JUMP_IF_TRUE(5, 3),
    JUMP_IF_FALSE(6, 3),
    LESS_THAN(7, 4),
    EQUALS(8, 4),
    HALT(99, 1);

    companion object {
        private val map = values().associateBy(AdvancedOpcode::value)
        operator fun get(opcode: Int) = map[opcode] ?: error("Invalid opcode")
    }
}

enum class ParameterMode(val id: Int) {
    POSITION(0),
    IMMEDIATE(1);

    companion object {
        private val map = values().associateBy(ParameterMode::id)
        operator fun get(id: Int) = map[id] ?: error("Invalid parameter mode")
    }
}

enum class System(val id: Int) {
    AIR_CONDITIONER(1),
    THERMAL_RADIATOR(5)
}