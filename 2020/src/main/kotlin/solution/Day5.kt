package solution

import Day
import kotlin.math.roundToInt

object Day5 : Day(5) {

    private const val ROW_SPECIFICATION_LENGTH = 7
    private const val MAX_ROWS = 128
    private const val MAX_COLUMNS = 8
    private const val ROW_MULTIPLIER = 8

    override fun part1(input: List<String>) = findSeatIds(input).maxOrNull().toString()

    override fun part2(input: List<String>): String {
        val allIds = 1 until (MAX_ROWS * MAX_COLUMNS - 1)

        val seatIds = findSeatIds(input).toSet()

        return allIds.first { isRemainingSeat(it, seatIds) }.toString()
    }

    private fun findSeatIds(passes: List<String>) = passes.map { findSeatId(it) }

    private fun isRemainingSeat(id: Int, ids: Set<Int>): Boolean {
        return !ids.contains(id) && ids.contains(id + 1) && ids.contains(id - 1)
    }

    private fun findSeatId(pass: String): Int {
        val rowSpecification = pass.take(ROW_SPECIFICATION_LENGTH)
        val columnSpecification = pass.drop(ROW_SPECIFICATION_LENGTH)

        val row = resolveSpecification(rowSpecification, lower = 'F', upper = 'B', range = 0 until MAX_ROWS)
        val column = resolveSpecification(columnSpecification, lower = 'L', upper = 'R', range = 0 until MAX_COLUMNS)

        return calculateUniqueSeatId(row, column)
    }

    private fun resolveSpecification(specification: String, lower: Char, upper: Char, range: IntRange): Int {
        val head = specification.firstOrNull()

        return if (head == null) {
            range.first
        } else {
            val partitionedRange = if (head == lower) range.takeLowerHalf() else range.takeUpperHalf()
            resolveSpecification(specification.tail(), lower, upper, partitionedRange)
        }
    }

    private fun calculateUniqueSeatId(row: Int, column: Int) = row * ROW_MULTIPLIER + column

    private fun IntRange.takeLowerHalf() = first..getMidpoint()

    private fun IntRange.takeUpperHalf() = getMidpoint()..last

    private fun IntRange.getMidpoint() = ((last + first) / 2.0).roundToInt()

    private fun String.tail() = substring(1)
}