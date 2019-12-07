package intcode

enum class Opcode(val value: Int, val pointerSize: Int) {
    ADD(1, 4),
    MULTIPLY(2, 4),
    INPUT(3, 2),
    OUTPUT(4, 2),
    JUMP_IF_TRUE(5, 3),
    JUMP_IF_FALSE(6, 3),
    LESS_THAN(7, 4),
    EQUALS(8, 4),
    HALT(99, 1);

    companion object {
        private val map = values().associateBy(Opcode::value)
        operator fun get(opcode: Int) = map[opcode] ?: error("Invalid opcode")
    }
}