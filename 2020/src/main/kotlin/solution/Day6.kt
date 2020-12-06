package solution

import Day

object Day6 : Day(6) {

    override fun part1(input: List<String>) = sumAnswerCounts(input, Set<Char>::union).toString()

    override fun part2(input: List<String>) = sumAnswerCounts(input, Set<Char>::intersect).toString()

    private fun sumAnswerCounts(input: List<String>, setOperation: (Set<Char>, Set<Char>) -> Set<Char>): Int {
        val groups = generateGroups(input)
        return groups.sumBy { it.reduce { acc, set -> setOperation(acc, set) }.size }
    }

    private fun generateGroups(input: List<String>) = sequence {
        val group = mutableListOf<Set<Char>>()

        for (line in input) {
            if (line.isNotEmpty()) {
                val answerSet = line.toCharArray().toSet()
                group.add(answerSet)
            } else {
                yield(group)
                group.clear()
            }
        }

        yield(group)
    }
}