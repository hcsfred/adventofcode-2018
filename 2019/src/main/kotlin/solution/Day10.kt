package solution

import org.joml.*
import java.lang.Math
import kotlin.math.*

fun main() {
    val rows = ClassLoader.getSystemResourceAsStream("day10.txt").bufferedReader().readLines()
    println(Day10.part1(rows))
    println(Day10.part2(rows))
}

object Day10 {

    private const val PART_TWO_TARGET = 200

    fun part1(rows: List<String>): Int {
        val asteroids = getAsteroids(rows)
        return findBestStation(asteroids).detected
    }

    private fun findBestStation(asteroids: ArrayList<Vector2i>): Station {
        val angles = HashMap<Vector2i, HashSet<Double>>()
        for (station in asteroids) {
            for (asteroid in asteroids) {
                if (station != asteroid) {
                    val angle = getAngle(station, asteroid)
                    angles.getOrPut(station) { HashSet() }.add(angle)
                }
            }
        }
        val best = angles.maxBy { it.value.size } ?: error("No stations found")
        return Station(best.key, best.value.size)
    }

    private fun getAngle(p1: Vector2i, p2: Vector2i) =
            Math.toDegrees(atan2((p1.x - p2.x).toDouble(), (p1.y - p2.y).toDouble()))

    fun part2(rows: List<String>): Int {
        val asteroids = getAsteroids(rows)
        val center = findBestStation(asteroids).location
        asteroids.remove(center)

        val angles = getAngles(center, asteroids)
        val sorted = angles.keys.sorted().map { angles[it] }
        sorted.map { it?.sortByDescending { asteroid -> center.distance(asteroid) } }

        var i = 0
        var destroyed = 0

        while (true) {
            val destroyable = sorted.getOrNull(i++ % sorted.size) ?: continue
            val closest = destroyable.removeAt(destroyable.size - 1) // Closest asteroid

            if (++destroyed == PART_TWO_TARGET) {
                return closest.x * 100 + closest.y
            }
        }
    }

    private fun getAngles(center: Vector2i, asteroids: ArrayList<Vector2i>): HashMap<Double, ArrayList<Vector2i>> {
        val angles = HashMap<Double, ArrayList<Vector2i>>()
        val max = 360
        for (asteroid in asteroids) {
            val angle = max - getAngle(center, asteroid)
            angles.getOrPut(angle % max) { ArrayList() }.add(asteroid)
        }
        return angles
    }


    private fun getAsteroids(rows: List<String>): ArrayList<Vector2i> {
        val asteroids = ArrayList<Vector2i>()
        for ((y, row) in rows.withIndex()) {
            for ((x, char) in row.withIndex()) {
                if (char == '#') {
                    asteroids.add(Vector2i(x, y))
                }
            }
        }
        return asteroids
    }
}

data class Station(val location: Vector2i, val detected: Int)