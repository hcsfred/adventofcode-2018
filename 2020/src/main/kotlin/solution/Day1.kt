package solution

import Day

object Day1 : Day(1) {

    private const val TARGET_YEAR = 2020

    override fun part1(input: List<String>): String {
        val entries = input.map(String::toInt)
        var product = -1

        entries.loop2D { i, j ->
            if (equalsTargetYear(entries[i], entries[j])) {
                product = calculateProduct(entries[i], entries[j])
            }
        }

        return product.toString()
    }

    override fun part2(input: List<String>): String {
        val entries = input.map(String::toInt)
        var product = -1

        entries.loop2D { i, j ->

            for (k in i + 2 until entries.size) {

                if (equalsTargetYear(entries[i], entries[j], entries[k])) {
                    product = calculateProduct(entries[i], entries[j], entries[k])
                }
            }
        }

        return product.toString()
    }

    private fun List<Int>.loop2D(onLoop: (Int, Int) -> Unit) {
        for (i in indices) {
            for (j in i + 1 until size) {
                onLoop(i, j)
            }
        }
    }

    private fun equalsTargetYear(vararg entries: Int) = entries.sum() == TARGET_YEAR

    private fun calculateProduct(vararg entries: Int) = entries.reduce(Int::times)
}