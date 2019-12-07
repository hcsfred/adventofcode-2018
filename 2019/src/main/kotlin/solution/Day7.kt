package solution

import com.marcinmoskala.math.permutations
import intcode.Computer
import kotlin.math.max

fun main() {
    val line = ClassLoader.getSystemResourceAsStream("day7.txt").bufferedReader().readLine()
    val program = line.split(",").map(String::toInt)
    println(Day7.part1(program))
    println(Day7.part2(program))
}

object Day7 {

    fun part1(program: List<Int>) = findHighestSignal(program, settings = 0..4)

    fun part2(program: List<Int>) = findHighestSignal(program, settings = 5..9)

    private fun findHighestSignal(program: List<Int>, settings: IntRange): Int {
        val permutations = settings.toSet().permutations()
        var highestSignal = 0

        for (sequence in permutations) {
            val amplifiers = ('A'..'E').zip(sequence).map { Amplifier(it.first, program, it.second) }
            var signal = 0

            loop@ while (true) {
                for (amplifier in amplifiers) {
                    signal = amplifier.computer.runTest(signal)

                    if (amplifier.id == 'E' && amplifier.computer.halted) {
                        break@loop
                    }
                }
            }
            highestSignal = max(signal, highestSignal)
        }
        return highestSignal
    }
}

class Amplifier(val id: Char, program: List<Int>, setting: Int) {
    val computer = Computer(ArrayList(program), setting)
}