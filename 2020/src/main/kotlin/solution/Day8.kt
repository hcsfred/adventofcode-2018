package solution

import Day
import java.lang.IllegalArgumentException

object Day8 : Day(8) {

    override fun part1(input: List<String>) = Program(input).apply { executeOnce() }.accumulator.toString()

    override fun part2(input: List<String>) = Program(input).apply { fixAndExecute() }.accumulator.toString()
}

class Program(private val bootCode: List<String>) {

    var accumulator = 0
    private var terminated = false

    fun executeOnce(bootCode: List<String> = this.bootCode) {
        var index = 0
        val traversed = mutableSetOf<Int>()

        while (index < bootCode.size) {

            if (!traversed.add(index)) {
                return
            }

            val instruction = bootCode[index]
            val (operation, argument) = instruction.parseInstruction()

            when (operation) {
                Operation.ACC -> accumulator += argument
                Operation.JMP -> index += argument - 1
            }

            index++
        }

        terminated = true
    }

    fun fixAndExecute() {
        val indicesToChange = bootCode.asSequence().withIndex().filter { it.value.isNotAccInstruction() }.map { it.index }

        for (index in indicesToChange) {

            val bootCodeCopy = bootCode.toMutableList()

            bootCodeCopy[index] = bootCodeCopy[index].changeInstruction()

            executeOnce(bootCodeCopy)

            if (terminated) return else reset()
        }
    }

    private fun String.parseInstruction(): Pair<Operation, Int> {
        val parts = split(" ")
        val operation = Operation[parts[0]]
        val argument = parts[1].toInt()
        return operation to argument
    }

    private fun String.isNotAccInstruction() = !contains("acc")

    private fun String.changeInstruction() = if (contains("nop")) replace("nop", "jmp") else replace("jmp", "nop")

    private fun reset() {
        accumulator = 0
        terminated = false
    }
}

enum class Operation {
    ACC,
    JMP,
    NOP;

    companion object {
        private val LOOKUP = values().associateBy { it.name.toLowerCase() }
        operator fun get(name: String) = LOOKUP[name] ?: throw IllegalArgumentException("Invalid operation")
    }
}