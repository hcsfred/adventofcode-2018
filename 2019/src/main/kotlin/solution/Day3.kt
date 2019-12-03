package solution

import kotlin.math.abs
import kotlin.math.min

fun main() {
    val (first, second) = ClassLoader.getSystemResourceAsStream("day3.txt").bufferedReader().readLines()
    println(Day3.part1(Wire(first), Wire(second)))
    println(Day3.part2(Wire(first), Wire(second)))
}

val PATTERN = Regex("(\\w)(\\d+)")

object Day3 {

    private fun findIntersections(first: Wire, second: Wire): Set<Coordinate> {
        first.parse()
        second.parse()
        return first.points.intersect(second.points)
    }

    fun part1(first: Wire, second: Wire): Int {
        val intersections = findIntersections(first, second)
        var minimum = Int.MAX_VALUE

        intersections.forEach {
            minimum = min(minimum, abs(it.x) + abs(it.y)) // Manhattan distance with center (0,0)
        }
        return minimum
    }

    fun part2(first: Wire, second: Wire): Int {
        val intersections = findIntersections(first, second)
        var minimum = Int.MAX_VALUE

        intersections.forEach {
            minimum = min(minimum, first.stepsTo(it) + second.stepsTo(it))
        }
        return minimum
    }
}

class Wire(input: String) {

    private val path = input.split(",")
    var position = Coordinate()
    val points = ArrayList<Coordinate>()
    
    fun parse() {
        for (action in path) {
            val match = PATTERN.find(action) ?: continue
            val (direction, steps) = match.destructured

            Direction[direction]?.let { dir ->
                repeat(steps.toInt()) {
                    position.x += dir.dx
                    position.y += dir.dy
                    points.add(position.copy())
                }
            }
        }
    }

    fun stepsTo(intersection: Coordinate): Int {
        val point = points.first { it == intersection }
        return points.indexOf(point) + 1
    }
}

enum class Direction(val id: String, val dx: Int, val dy: Int) {
    RIGHT("R", 1, 0),
    UP("U", 0, 1),
    LEFT("L", -1, 0),
    DOWN("D", 0, -1);

    companion object {
        private val map = values().associateBy(Direction::id)
        operator fun get(id: String) = map[id]
    }
}

data class Coordinate(var x: Int = 0, var y: Int = 0)
