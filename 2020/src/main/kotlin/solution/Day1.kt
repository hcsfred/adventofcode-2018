package solution

fun main() {
    val entries = ClassLoader.getSystemResourceAsStream("day1.txt").bufferedReader().readLines().map(String::toInt)
    println("part1 = ${Day1.part1(entries)}")
    println("part2 = ${Day1.part2(entries)}")
}

object Day1 {

    private const val TARGET_YEAR = 2020

    fun part1(entries: List<Int>): Int {

        var product = -1

        entries.loop2D { i, j ->
            if (equalsTargetYear(entries[i], entries[j])) {
                product = calculateProduct(entries[i], entries[j])
            }
        }

        return product
    }

    fun part2(entries: List<Int>): Int {

        var product = -1

        entries.loop2D { i, j ->

            for (k in i + 2 until entries.size) {

                if (equalsTargetYear(entries[i], entries[j], entries[k])) {
                    product = calculateProduct(entries[i], entries[j], entries[k])
                }
            }
        }

        return product
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