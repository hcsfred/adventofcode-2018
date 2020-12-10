package solution

import Day

object Day10 : Day(10) {

    override fun part1(input: List<String>): String {
        val adapters = getAdapters(input)
        var oneJoltDifferences = 0
        var threeJoltDifferences = 1

        for (index in adapters.indices) {
            val currentAdapter = adapters[index]
            val previousAdapter = if (index >= 1) adapters[index - 1] else 0

            when (currentAdapter - previousAdapter) {
                1 -> oneJoltDifferences++
                3 -> threeJoltDifferences++
            }
        }

        return (oneJoltDifferences * threeJoltDifferences).toString()
    }

    override fun part2(input: List<String>): String {
        val adapters = getAdapters(input)
        val paths = mutableMapOf(0 to 1L)
        val joltRange = 1..3

        adapters.forEach { adapter ->
            paths[adapter] = joltRange.map { jolt -> paths.getOrDefault(adapter - jolt, 0) }.sum()
        }

        return paths[adapters.last()].toString()
    }

    private fun getAdapters(input: List<String>) = input.map(String::toInt).sorted()
}