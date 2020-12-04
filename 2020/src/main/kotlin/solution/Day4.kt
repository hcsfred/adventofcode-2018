package solution

import Day
import java.lang.IllegalArgumentException

private val HEX_COLOUR_REGEX = Regex("#[0-9a-f]{6}")
private val EYE_COLOURS = setOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")

object Day4 : Day(4) {

    override fun part1(input: List<String>): String {
        return countValidPassports(input) { it.hasRequiredFields() }.toString()
    }

    override fun part2(input: List<String>): String {
        return countValidPassports(input) { it.hasRequiredFields() && it.hasValidFields() }.toString()
    }

    private fun countValidPassports(input: List<String>, predicate: (Passport) -> Boolean): Int {
        val passports = getPassportSequence(input)

        val validPassports = passports.filter(predicate)

        return validPassports.count()
    }

    private fun getPassportSequence(input: List<String>) = sequence {
        val fields = mutableMapOf<String, String>()

        for (line in input) {
            if (line.isNotEmpty()) {
                fields.putAll(line.extractFields())
            } else {
                yield(Passport(fields))
                fields.clear()
            }
        }

        yield(Passport(fields))
    }

    private fun String.extractFields() = split(" ").map {
        val (key, value) = it.split(":")
        key to value
    }
}

data class Passport(private val fields: Map<String, String>) {

    fun hasRequiredFields() = fields.keys.containsAll(Field.REQUIRED)

    fun hasValidFields() = fields.all { Field[it.key].isValid(it.value) }
}

enum class Field(private val key: String, val isValid: (String) -> Boolean, private val required: Boolean = true) {
    BYR("byr", isValid = { it.toInt() in 1920..2002 }),
    IYR("iyr", isValid = { it.toInt() in 2010..2020 }),
    EYR("eyr", isValid = { it.toInt() in 2020..2030 }),
    HGT("hgt", isValid = { isValidHeight(it) }),
    HCL("hcl", isValid = { HEX_COLOUR_REGEX.matches(it) }),
    ECL("ecl", isValid = { it in EYE_COLOURS }),
    PID("pid", isValid = { it.length == 9 && it.toIntOrNull() != null }),
    CID("cid", isValid = { true }, required = false);

    companion object {
        val REQUIRED = values().filter(Field::required).map(Field::key)
        private val KEY_LOOKUP = values().associateBy(Field::key)
        operator fun get(key: String) = KEY_LOOKUP[key] ?: throw IllegalArgumentException("Unrecognised field $key")
    }
}

private fun isValidHeight(it: String): Boolean {
    return if (it.endsWith("cm")) {
        it.removeSuffix("cm").toInt() in 150..193
    } else {
        it.removeSuffix("in").toInt() in 59..76
    }
}