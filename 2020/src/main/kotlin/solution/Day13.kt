package solution

import Day

object Day13 : Day(13) {

    override fun part1(input: List<String>): String {
        val earliestDeparture = input.first().toInt()

        val ids = input.last().split(",").filter(::inService).map(String::toInt)

        val earliestBus = ids.minByOrNull { it - earliestDeparture.rem(it) } ?: throw IllegalArgumentException()

        val waitTime = earliestBus - earliestDeparture.rem(earliestBus)

        return (earliestBus * waitTime).toString()
    }

    override fun part2(input: List<String>): String {
        val offsets = input.last()
                           .split(",")
                           .mapIndexed { offset, id -> id to offset }
                           .filter { inService(it.first) }
                           .toMap()

        val maxOffset = offsets.values.maxOrNull() ?: throw IllegalArgumentException()

        val remainders = offsets.values.map { maxOffset - it.toLong() }

        val longs = offsets.keys.map(String::toLong)

        val earliestTimestamp = applyChineseRemainderTheorem(longs, remainders) - maxOffset

        return earliestTimestamp.toString()
    }

    private fun inService(id: String) = id != "x"

    // Source: https://rosettacode.org/wiki/Chinese_remainder_theorem#Kotlin
    private fun applyChineseRemainderTheorem(longs: List<Long>, remainders: List<Long>): Long {
        val prod = longs.fold(1L) { acc, i -> acc * i }
        var sum = 0L
        for (i in longs.indices) {
            val p = prod / longs[i]
            sum += remainders[i] * mulInv(p, longs[i]) * p
        }
        return sum % prod
    }

    private fun mulInv(a: Long, b: Long): Long {
        if (b == 1L) return 1
        var aa = a
        var bb = b
        var x0 = 0L
        var x1 = 1L
        while (aa > 1) {
            val q = aa / bb
            var t = bb
            bb = aa % bb
            aa = t
            t = x0
            x0 = x1 - q * x0
            x1 = t
        }
        if (x1 < 0) x1 += b
        return x1
    }
}