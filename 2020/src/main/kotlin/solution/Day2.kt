package solution

fun main() {
    val entries = ClassLoader.getSystemResourceAsStream("day2.txt").bufferedReader().readLines()
    println("part1 = ${Day2.part1(entries)}")
    println("part2 = ${Day2.part2(entries)}")
}


object Day2 {

    private val REGEX = Regex("(\\d+)-(\\d+) (\\w): (\\w+)")

    fun part1(entries: List<String>) = countValidPasswords(entries, policy = PreviousPasswordPolicy)

    fun part2(entries: List<String>) = countValidPasswords(entries, policy = CurrentPasswordPolicy)

    private fun countValidPasswords(entries: List<String>, policy: PasswordPolicy): Int {
        return entries.filter { entry -> isValidPassword(entry, policy) }.count()
    }

    private fun isValidPassword(entry: String, policy: PasswordPolicy): Boolean {
        val match = REGEX.find(entry) ?: return false
        val (firstNumber, secondNumber, char, password) = match.destructured
        return policy.accepts(password, char.single(), firstNumber.toInt(), secondNumber.toInt())
    }
}

interface PasswordPolicy {
    fun accepts(password: String, char: Char, firstNumber: Int, secondNumber: Int): Boolean
}

private object PreviousPasswordPolicy : PasswordPolicy {

    override fun accepts(password: String, char: Char, firstNumber: Int, secondNumber: Int): Boolean {
        val occurrences = password.count { it == char }
        return occurrences in firstNumber..secondNumber
    }
}

private object CurrentPasswordPolicy : PasswordPolicy {

    override fun accepts(password: String, char: Char, firstNumber: Int, secondNumber: Int): Boolean {
        val firstPosition = firstNumber - 1
        val secondPosition = secondNumber - 1
        return password.hasRequiredCharExactlyOnce(firstPosition, secondPosition, requiredChar = char)
    }

    private fun String.hasRequiredCharExactlyOnce(firstPosition: Int, secondPosition: Int, requiredChar: Char): Boolean {
        val firstChar = get(firstPosition)
        val secondChar = get(secondPosition)
        return firstChar != secondChar && (firstChar == requiredChar || secondChar == requiredChar)
    }
}