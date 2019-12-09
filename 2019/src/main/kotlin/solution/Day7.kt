package solution

import com.marcinmoskala.math.permutations
import intcode.Computer
import kotlin.math.max

fun main() {
    val line = ClassLoader.getSystemResourceAsStream("day7.txt").bufferedReader().readLine()
    val program = line.split(",").map(String::toLong)
    println(Day7.part1(program))
    println(Day7.part2(program))
}

object Day7 {

    fun part1(program: List<Long>) = findHighestSignal(program, settings = 0..4L)

    fun part2(program: List<Long>) = findHighestSignal(program, settings = 5..9L)

    private fun findHighestSignal(program: List<Long>, settings: LongRange): Long {
        val permutations = settings.toSet().permutations()
        var highestSignal = 0L

        for (sequence in permutations) {
            val amplifiers = ('A'..'E').zip(sequence).map { Amplifier(it.first, program, it.second) }
            var signal = 0L

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

class Amplifier(val id: Char, program: List<Long>, setting: Long) {
    val computer = Computer(ArrayList(program), setting)
}