fun main() {
    val itemPriorities = (('a'..'z') + ('A'..'Z')).mapIndexed { i, e -> e to i + 1 }.toMap()

    fun part1(input: List<String>): Int =
        input.sumOf { rucksack ->
            itemPriorities.getValue(
                rucksack.chunked(rucksack.length / 2).let { compartments ->
                    compartments[0].first { it in compartments[1] }
                }
            )
        }

    fun part2(input: List<String>): Int =
        input.chunked(3).sumOf { groupRucksacks ->
            itemPriorities.getValue(
                groupRucksacks[0].first { it in groupRucksacks[1] && it in groupRucksacks[2] }
            )
        }

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}