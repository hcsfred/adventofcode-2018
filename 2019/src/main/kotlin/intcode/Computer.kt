package intcode

import solution.toList

class Computer(val program: ArrayList<Int>, private val setting: Int = -1) { // TODO: heavy refactor required

    var halted = false
    private var diagnostic = -1
    private var pointer = 0
    private var phaseSet = false

    fun initMemory(noun: Int, verb: Int) {
        program[1] = noun
        program[2] = verb
    }

    fun runProgram(): Int {
        while (!halted) {
            runTest()
        }
        return diagnostic
    }

    fun runTest(signal: Int = setting): Int {
        while (!halted && pointer < program.size) {
            val instruction = program[pointer]
            val (opcode, modes) = readInstruction(instruction)
            val indices = getIndices(opcode, modes, pointer)
            pointer += opcode.pointerSize

            when (opcode) {
                Opcode.ADD -> program[indices[2]] = program[indices[0]] + program[indices[1]]

                Opcode.MULTIPLY -> program[indices[2]] = program[indices[0]] * program[indices[1]]

                Opcode.INPUT -> {
                    program[indices[0]] = if (!phaseSet) setting else signal
                    phaseSet = true
                }

                Opcode.OUTPUT -> {
                    diagnostic = program[indices[0]]
                    return diagnostic
                }

                Opcode.JUMP_IF_TRUE -> if (program[indices[0]] != 0) pointer = program[indices[1]]

                Opcode.JUMP_IF_FALSE -> if (program[indices[0]] == 0) pointer = program[indices[1]]

                Opcode.LESS_THAN -> {
                    program[indices[2]] = if (program[indices[0]] < program[indices[1]]) 1 else 0
                }

                Opcode.EQUALS -> {
                    program[indices[2]] = if (program[indices[0]] == program[indices[1]]) 1 else 0
                }

                Opcode.HALT -> halted = true
            }
        }
        return diagnostic
    }

    private fun readInstruction(instruction: Int): Pair<Opcode, List<Int>> {
        val list = instruction.toList()
        if (list.size == 1) {
            return Opcode[instruction] to emptyList()
        }
        val opcode = list.subList(list.size - 2, list.size).joinToString("").toInt() // Final two digits
        val modes = list.subList(0, list.size - 2).reversed() // Read right to left
        return Opcode[opcode] to modes
    }

    private fun getIndices(opcode: Opcode, modes: List<Int>, pointer: Int): ArrayList<Int> {
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