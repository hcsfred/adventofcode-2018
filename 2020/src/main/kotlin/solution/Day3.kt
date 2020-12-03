package solution

import Day

object Day3 : Day(3) {

    private val FIRST_SLOPE = 3 to 1
    private val ALL_SLOPES = listOf(1 to 1, FIRST_SLOPE, 5 to 1, 7 to 1, 1 to 2)

    override fun part1(input: List<String>): String {
        return countTrees(input, FIRST_SLOPE).toString()
    }

    override fun part2(input: List<String>): String {
        return ALL_SLOPES.fold(1L) { product, slope -> product * countTrees(input, slope) }.toString()
    }

    private fun countTrees(rows: List<String>, slope: Pair<Int, Int>): Long {
        var count = 0L
        var x = 0
        val ys = rows.indices

        for (y in ys step slope.second) {
            val row = rows[y]
            val square = row[x % row.length]

            if (square.isTree()) {
                count++
            }

            x += slope.first
        }

        return count
    }

    private fun Char.isTree() = equals('#')
}