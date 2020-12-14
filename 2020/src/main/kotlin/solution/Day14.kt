package solution

import Day
import kotlin.math.pow

object Day14 : Day(14) { // TODO: refactor

    private const val INT_SIZE = 36
    private const val FLOATING_BIT = 'X'

    override fun part1(input: List<String>): String {
        val memory = initMemory()

        runProgram(input, onWrite = { address, value, mask ->
            memory[address] = maskValue(value, mask)
        })

        return memory.sumMemory()
    }

    override fun part2(input: List<String>): String {
        val memory = initMemory()

        runProgram(input, onWrite = { address, value, mask ->
            val addresses = getMaskedAddresses(address, mask)
            addresses.forEach { memory[it] = value }
        })

        return memory.sumMemory()
    }

    private fun initMemory() = mutableMapOf<Long, Long>()

    private fun Map<Long, Long>.sumMemory() = values.sum().toString()

    private fun runProgram(program: List<String>, onWrite: (Long, Long, String) -> Unit) {

        var mask = ""

        for (instruction in program) {
            val (action, _, value) = instruction.split(" ")

            if (action == "mask") {
                mask = value
            } else {
                val address = action.removeSurrounding("mem[", "]")
                onWrite(address.toLong(), value.toLong(), mask)
            }
        }
    }

    private fun maskValue(value: Long, mask: String): Long {

        val binaryString = value.toBinaryString()

        val maskedString = binaryString.toCharArray()

        binaryString.forEachIndexed { index, bit ->
            maskedString[index] = when (mask[index]) {
                '0' -> '0'
                '1' -> '1'
                else -> bit
            }
        }

        return maskedString.toLong()
    }

    private fun getMaskedAddresses(address: Long, mask: String): List<Long> {

        val addressBinaryString = address.toBinaryString()

        val maskedAddress = address.toBinaryString().toCharArray()

        addressBinaryString.forEachIndexed { index, bit ->
            val maskValue = mask[index]
            maskedAddress[index] = when (maskValue) {
                '0' -> bit
                '1' -> '1'
                else -> maskValue
            }
        }

        val nFlippedBits = maskedAddress.count { it == FLOATING_BIT }
        val combinations = 2.0.pow(nFlippedBits).toInt()
        val addresses = mutableSetOf<CharArray>()

        for (i in 0 until combinations) {
            var combination = Integer.toBinaryString(i).padStart(nFlippedBits, '0')
            val newAddress = maskedAddress.copyOf()

            maskedAddress.forEachIndexed { index, bit ->
                if (bit == FLOATING_BIT) {
                    newAddress[index] = combination.first()
                    combination = combination.substring(1)
                }
            }

            addresses.add(newAddress)
        }

        return addresses.map { it.toLong() }
    }

    private fun Long.toBinaryString() = toString(radix = 2).padStart(INT_SIZE, '0')

    private fun CharArray.toLong() = java.lang.Long.parseLong(String(this), 2)
}