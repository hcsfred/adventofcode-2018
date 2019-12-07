package solution

import kotlin.collections.HashMap

fun main() {
    val map = ClassLoader.getSystemResourceAsStream("day6.txt").bufferedReader().readLines()
    println(Day6.part1(map))
    println(Day6.part2(map))
}

object Day6 {

    fun part1(map: List<String>) = map.parse().values.sumBy(SpaceObject::countOrbits)

    fun part2(map: List<String>): Int {
        val objects = map.parse()
        val source = objects.getValue("YOU").getOrbits()
        val destination = objects.getValue("SAN").getOrbits()
        val intersect = source.intersect(destination).first()
        return source.indexOf(intersect) + destination.indexOf(intersect)
    }

    private fun List<String>.parse(): Map<String, SpaceObject> {
        val objects = HashMap<String, SpaceObject>()
        forEach {
            val (parent, orbiter) = it.split(")").map { id -> objects.getOrPut(id) { SpaceObject() } }
            orbiter.parent = parent
        }
        return objects
    }
}

class SpaceObject {

    var parent: SpaceObject? = null

    fun getOrbits(): List<SpaceObject> {
        var child = this
        val visited = ArrayList<SpaceObject>()

        while(child.parent != null) {
            visited.add(child.parent!!)
            child = child.parent!!
        }
        return visited
    }

    fun countOrbits() = getOrbits().count()
}