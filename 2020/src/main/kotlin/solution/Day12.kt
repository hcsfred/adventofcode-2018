package solution

import Day
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

object Day12 : Day(12) {

    private const val FORWARD = 'F'
    private const val RIGHT = 'R'
    private const val LEFT = 'L'
    private val ORIGIN = Coordinate(0, 0)

    override fun part1(input: List<String>): String {
        var position = ORIGIN
        var direction = Direction.EAST

        getNavigationInstructions(input).forEach { (action, value) ->
            when (action) {
                FORWARD -> position = direction.move(position, value)
                RIGHT -> direction = direction.turn(value, clockwise = true)
                LEFT -> direction = direction.turn(value, clockwise = false)
                else -> position = Direction[action].move(position, value)
            }
        }

        return calculateManhattanDistance(position).toString()
    }

    override fun part2(input: List<String>): String {
        var position = ORIGIN
        var waypoint = Coordinate(10, 1)

        getNavigationInstructions(input).forEach { (action, value) ->
            when (action) {
                FORWARD -> {
                    repeat(value) {
                        val dx = waypoint.x - position.x
                        val dy = waypoint.y - position.y
                        position = position.add(dx, dy)
                        waypoint = waypoint.add(dx, dy)
                    }
                }
                RIGHT -> waypoint = waypoint.rotate(position, -value)
                LEFT -> waypoint = waypoint.rotate(position, value)
                else -> waypoint = Direction[action].move(waypoint, value)
            }
        }

        return calculateManhattanDistance(position).toString()
    }

    private fun getNavigationInstructions(input: List<String>): Sequence<Pair<Char, Int>> {
        return input.asSequence().map { it.first() to it.drop(1).toInt() }
    }

    private fun calculateManhattanDistance(position: Coordinate): Int {
        return abs(position.x - ORIGIN.x) + abs(position.y - ORIGIN.y)
    }
}

data class Coordinate(val x: Int, val y: Int) {

    fun add(dx: Int = 0, dy: Int = 0) = Coordinate(x + dx, y + dy)

    fun rotate(center: Coordinate, angle: Int): Coordinate {
        val theta = Math.toRadians(angle.toDouble())
        val x1 = x - center.x
        val y1 = y - center.y

        val x2 = x1 * cos(theta) - y1 * sin(theta)
        val y2 = x1 * sin(theta) + y1 * cos(theta)

        val newX = (center.x + x2).roundToInt()
        val newY = (center.y + y2).roundToInt()

        return Coordinate(newX, newY)
    }
}

enum class Direction(val move: (Coordinate, Int) -> Coordinate, val right: Char, val left: Char) {
    NORTH(move = { coord, value -> coord.add(dy = value) }, right = 'E', left = 'W'),
    SOUTH(move = { coord, value -> coord.add(dy = -value) }, right = 'W', left = 'E'),
    WEST(move = { coord, value -> coord.add(dx = -value) }, right = 'N', left = 'S'),
    EAST(move = { coord, value -> coord.add(dx = value) }, right = 'S', left = 'N');

    fun turn(angle: Int, clockwise: Boolean): Direction {
        val turns = angle / 90
        var newDirection = this

        repeat(turns) {
            val key = if (clockwise) newDirection.right else newDirection.left
            newDirection = Direction[key]
        }

        return newDirection
    }

    companion object {
        private val KEYS = values().associateBy { it.name.first() }
        operator fun get(key: Char) = KEYS[key] ?: throw IllegalArgumentException()
    }
}