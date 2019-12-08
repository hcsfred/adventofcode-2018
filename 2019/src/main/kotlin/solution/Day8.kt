package solution

fun main() {
    val line = ClassLoader.getSystemResourceAsStream("day8.txt").bufferedReader().readLine()
    println(Day8.part1(line))
    println(Day8.part2(line))
}

object Day8 {

    private val DIMENSIONS = 25 to 6

    fun part1(line: String): Int {
        val layers = line.chunked(DIMENSIONS.first).chunked(DIMENSIONS.second)
        val fewestZeros = layers.minBy { layer -> layer.joinToString().count { it == '0' } } ?: error("No minimum")

        val joined = fewestZeros.joinToString()
        return joined.count { it == '1' } * joined.count { it == '2' }
    }

    fun part2(line: String): String {
        val layers = line.chunked(DIMENSIONS.first).chunked(DIMENSIONS.second)
        val image = Array(DIMENSIONS.second) { Array(DIMENSIONS.first) { Colour.TRANSPARENT } }

        layers.reversed().forEach { layer ->
            for ((j, row) in layer.withIndex()) {
                for ((i, pixel) in row.withIndex()) {
                    val colour = Colour[pixel]
                    if (colour != Colour.TRANSPARENT) {
                        image[j][i] = colour
                    }
                }
            }
        }
        return prettyPrint(image)
    }

    private fun prettyPrint(image: Array<Array<Colour>>): String {
        val builder = StringBuilder()
        for (row in image) {
            builder.append(row.joinToString(separator = " ", transform = Colour::output))
            builder.append("\n")
        }
        return builder.toString()
    }
}

enum class Colour(private val value: Char, val output: String = " ") {
    BLACK('0'),
    WHITE('1', output = "#"),
    TRANSPARENT('2');

    companion object {
        private val map = values().associateBy(Colour::value)
        operator fun get(value: Char) = map[value] ?: error("Invalid colour")
    }
}