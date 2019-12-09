package intcode

import solution.toList
import java.util.*
import kotlin.collections.ArrayList

class Computer(val memory: ArrayList<Long>, private val setting: Long = -1) { // TODO: heavy refactor required

    init {
        val extension = ArrayList<Long>(Collections.nCopies(memory.size * 10, 0L)) // Increase available memory
        memory.addAll(extension)
    }

    var halted = false
    private var pointer = 0
    private var relativeBase = 0
    private var diagnostic = -1L
    private var phaseSet = false

    fun initMemory(noun: Int, verb: Int) {
        memory[1] = noun.toLong()
        memory[2] = verb.toLong()
    }

    fun runProgram(): Long {
        while (!halted) {
            runTest()
        }
        return diagnostic
    }

    fun runTest(signal: Long = setting): Long {
        while (!halted && pointer < memory.size) {
            val instruction = memory[pointer]
            val (opcode, modes) = readInstruction(instruction.toInt())
            val indices = getIndices(opcode, modes, pointer)
            pointer += opcode.pointerSize

            when (opcode) {
                Opcode.ADD -> memory[indices[2]] = memory[indices[0]] + memory[indices[1]]

                Opcode.MULTIPLY -> memory[indices[2]] = memory[indices[0]] * memory[indices[1]]

                Opcode.INPUT -> {
                    memory[indices[0]] = if (!phaseSet) setting else signal
                    phaseSet = true
                }

                Opcode.OUTPUT -> {
                    diagnostic = memory[indices[0]]
                    return diagnostic
                }

                Opcode.JUMP_IF_TRUE -> if (memory[indices[0]] != 0L) pointer = memory[indices[1]].toInt()

                Opcode.JUMP_IF_FALSE -> if (memory[indices[0]] == 0L) pointer = memory[indices[1]].toInt()

                Opcode.LESS_THAN -> {
                    memory[indices[2]] = if (memory[indices[0]] < memory[indices[1]]) 1 else 0
                }

                Opcode.EQUALS -> {
                    memory[indices[2]] = if (memory[indices[0]] == memory[indices[1]]) 1 else 0
                }

                Opcode.RELATIVE -> relativeBase += memory[indices[0]].toInt()

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
                ParameterMode.POSITION -> memory[pointer + i].toInt()
                ParameterMode.IMMEDIATE -> pointer + i
                ParameterMode.RELATIVE -> memory[pointer + i].toInt() + relativeBase
            }
            indices.add(index)
        }
        return indices
    }
}