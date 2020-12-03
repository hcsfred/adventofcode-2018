package solution

import Day

object Day2 : Day(2) {

    private val REGEX = Regex("(\\d+)-(\\d+) (\\w): (\\w+)")

    override fun part1(input: List<String>) = countValidPasswords(input, policy = PreviousPasswordPolicy).toString()

    override fun part2(input: List<String>) = countValidPasswords(input, policy = CurrentPasswordPolicy).toString()

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