package intcode

enum class ParameterMode(val id: Int) {
    POSITION(0),
    IMMEDIATE(1),
    RELATIVE(2);

    companion object {
        private val map = values().associateBy(ParameterMode::id)
        operator fun get(id: Int) = map[id] ?: error("Invalid parameter mode")
    }
}