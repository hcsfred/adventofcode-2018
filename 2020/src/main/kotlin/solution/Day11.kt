package solution

import Day

object Day11 : Day(11) {

    override fun part1(input: List<String>): String {
        return countTotalOccupiedSeats(input, strategy = AdjacentSeatingStrategy).toString()
    }

    override fun part2(input: List<String>): String {
        return countTotalOccupiedSeats(input, strategy = LineOfSightSeatingStrategy).toString()
    }

    private fun countTotalOccupiedSeats(input: List<String>, strategy: SeatingStrategy): Int {
        val seed = getSeatLayout(input)

        val steadyState = findSteadyState(seed, strategy)

        return steadyState.values.count { it == SeatState.OCCUPIED }
    }

    private fun getSeatLayout(input: List<String>): Map<Coord, SeatState> {
        val layout = mutableMapOf<Coord, SeatState>()

        input.forEachIndexed { i, row ->
            row.forEachIndexed { j, key ->
                layout[Coord(i, j)] = SeatState[key]
            }
        }

        return layout
    }

    private fun findSteadyState(seed: Map<Coord, SeatState>, strategy: SeatingStrategy): Map<Coord, SeatState> {
        var previousState = seed

        while (true) {
            val newState = tick(previousState, strategy)

            if (previousState == newState) {
                break
            }

            previousState = newState
        }

        return previousState
    }

    private fun tick(layout: Map<Coord, SeatState>, strategy: SeatingStrategy): Map<Coord, SeatState> {
        return layout.map { it.key to strategy.getNextSeatState(layout, it.key, it.value) }.toMap()
    }
}

data class Coord(val x: Int, val y: Int)

enum class SeatState(private val key: Char) {
    EMPTY('#'),
    OCCUPIED('L'),
    NA('.');

    companion object {
        private val LOOKUP = values().associateBy(SeatState::key)
        operator fun get(key: Char) = LOOKUP[key] ?: throw IllegalArgumentException("Unrecognised seat state")
    }
}

interface SeatingStrategy {

    val requiredOccupied: Int

    fun countOccupiedSeats(layout: Map<Coord, SeatState>, center: Coord): Int

    fun getNextSeatState(layout: Map<Coord, SeatState>, coord: Coord, seatState: SeatState): SeatState {
        val occupied = countOccupiedSeats(layout, coord)

        if (seatState == SeatState.EMPTY && occupied == 0) {
            return SeatState.OCCUPIED
        }

        if (seatState == SeatState.OCCUPIED && occupied >= requiredOccupied) {
            return SeatState.EMPTY
        }

        return seatState
    }
}

object AdjacentSeatingStrategy : SeatingStrategy {

    override val requiredOccupied = 4

    override fun countOccupiedSeats(layout: Map<Coord, SeatState>, center: Coord): Int {
        var occupied = 0

        getRange().forEach { (dx, dy) ->
            val adjacent = layout[Coord(center.x + dx, center.y + dy)]
            if (adjacent == SeatState.OCCUPIED) {
                occupied++
            }
        }

        return occupied
    }
}

object LineOfSightSeatingStrategy : SeatingStrategy {

    override val requiredOccupied = 5

    override fun countOccupiedSeats(layout: Map<Coord, SeatState>, center: Coord): Int {
        var occupied = 0

        getRange().forEach { (dx, dy) ->
            var x = center.x
            var y = center.y
            var los = SeatState.NA

            while (los == SeatState.NA) {
                x += dx
                y += dy
                los = layout[Coord(x, y)] ?: break
            }

            if (los == SeatState.OCCUPIED) {
                occupied++
            }
        }

        return occupied
    }
}

private fun getRange() = sequence {
    for (dx in -1..1) {
        for (dy in -1..1) {
            if (!(dx == 0 && dy == 0)) {
                yield(dx to dy)
            }
        }
    }
}