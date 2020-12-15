package solution

import Day

object Day15 : Day(15) {

    override fun part1(input: List<String>) = playGame(input, turns = 2020).toString()

    override fun part2(input: List<String>) = playGame(input, turns = 30000000).toString()

    private fun playGame(input: List<String>, turns: Int): Int {
        val startingNumbers = getStartingNumbers(input)
        var previousNumber = startingNumbers.last()

        val memory = IntArray(turns)
        startingNumbers.forEachIndexed { turnIndex, number -> memory[number] = turnIndex + 1 }

        for (lastTurn in startingNumbers.size until turns) {
            val spokenNumber = memory[previousNumber]
            memory[previousNumber] = lastTurn
            previousNumber = if (spokenNumber != 0) lastTurn - spokenNumber else 0
        }

        return previousNumber
    }

    private fun getStartingNumbers(input: List<String>) = input.first().split(",").map(String::toInt)
}