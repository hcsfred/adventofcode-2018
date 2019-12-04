package solution

fun main() {
    val input = 138241..674034
    println(Day4.part1(input))
    println(Day4.part2(input))
}

object Day4 {

    fun part1(range: IntRange) = range.filter { it.toList().isValid() }.size

    fun part2(range: IntRange) = range.filter { it.toList().isValid(part2 = true) }.size

    private fun Int.toList() = toString().map(Char::toInt)

    private fun List<Int>.isValid(part2: Boolean = false): Boolean {
        var hasDoubleDigit = false
        loop@ for (i in 0 until size - 1) {
            val first = get(i)
            val second = get(i + 1)

            when {
                first > second -> return false      // Check ascending
                first != second -> continue@loop    // Check for equal adjacent digits
                !part2 -> hasDoubleDigit = true
                count { it == first } == 2 -> hasDoubleDigit = true // Check adjacent pair not part of larger group
            }
        }
        return hasDoubleDigit
    }
}