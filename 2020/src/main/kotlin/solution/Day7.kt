package solution

import Day

private const val SHINY_GOLD_BAG = "shiny gold"
private const val EMPTY_BAG = "no other bags"

object Day7 : Day(7) {

    override fun part1(input: List<String>): String {
        val bags = getBags(input)
        return bags.values.count(Bag::containsShinyGoldBag).toString()
    }

    override fun part2(input: List<String>): String {
        val bags = getBags(input)
        return bags[SHINY_GOLD_BAG]?.countAllContainedBags().toString()
    }

    private fun getBags(input: List<String>): MutableMap<String, Bag> { // TODO: refactor using regex
        val bags = mutableMapOf<String, Bag>()

        for (rule in input) {
            val (parent, children) = rule.removeSuffix(".").split(" contain ")

            val parentColour = parent.removeSuffix(" bags")
            val parentBag = bags.getOrPut(parentColour, { Bag(parentColour) })

            for (child in children.split(", ")) {

                if (child == EMPTY_BAG) {
                    continue
                }

                val childQuantity = child.substring(0, 1).toInt()
                val childColour = child.substring(2).removeSuffix(" bag").removeSuffix(" bags")
                val childBag = bags.getOrPut(childColour, { Bag(childColour) })

                parentBag.containedBags[childBag] = childQuantity
            }
        }

        return bags
    }
}

data class Bag(private val colour: String) {

    val containedBags = mutableMapOf<Bag, Int>()

    fun containsShinyGoldBag(): Boolean {
        return containedBags.keys.any { it.colour == SHINY_GOLD_BAG || it.containsShinyGoldBag() }
    }

    fun countAllContainedBags(): Int {
        var totalBags = 0

        for ((bag, quantity) in containedBags) {

            totalBags += quantity

            repeat(quantity) {
                totalBags += bag.countAllContainedBags()
            }
        }

        return totalBags
    }
}