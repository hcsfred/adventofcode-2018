package solution

import Day
import kotlin.math.abs

object Day9 : Day(9) {

    override fun part1(input: List<String>): String {
        val data = input.map(String::toLong)
        return data.findFirstInvalidNumber().toString()
    }

    override fun part2(input: List<String>): String {
        val data = input.map(String::toLong)

        val contiguousSet = findContiguousSet(data)

        return calculateWeakness(contiguousSet).toString()
    }

    private fun List<Long>.findFirstInvalidNumber(): Long {
        val preambleSize = if (size == input.size) 25 else 5
        val preamble = subList(0, preambleSize)
        val previousNumbers = ArrayDeque(preamble)

        for (index in preambleSize until size) {

            val number = get(index)

            if (!number.isValid(previousNumbers)) {
                return number
            }

            previousNumbers.removeFirst()
            previousNumbers.add(number)
        }

        return -1
    }

    private fun Long.isValid(previousNumbers: List<Long>) = previousNumbers.any { abs(this - it) in previousNumbers }

    private fun findContiguousSet(data: List<Long>): List<Long> {

        val invalidNumber = data.findFirstInvalidNumber()

        for (index in data.indices) {

            val contiguousSet = mutableListOf(data[index])

            for (adjacentIndex in index + 1 until data.size) {

                contiguousSet.add(data[adjacentIndex])

                val sum = contiguousSet.sum()

                if (sum == invalidNumber) {
                    return contiguousSet
                } else if (sum > invalidNumber) {
                    break
                }
            }
        }

        return emptyList()
    }

    private fun calculateWeakness(contiguousSet: List<Long>): Long {
        val min = contiguousSet.minOrNull() ?: return -1
        val max = contiguousSet.maxOrNull() ?: return -1
        return min + max
    }
}