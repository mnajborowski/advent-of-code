fun main() {
    fun part1(input: List<String>): Int =
        input
            .map { pairRangesString ->
                pairRangesString.split(',', '-')
                    .let { it[0].toInt()..it[1].toInt() to it[2].toInt()..it[3].toInt() }
            }
            .fold(0) { acc, (firstRange, secondRange) ->
                if (firstRange.all { it in secondRange } || secondRange.all { it in firstRange })
                    acc + 1
                else
                    acc
            }

    fun part2(input: List<String>): Int =
        input
            .map { pairRangesString ->
                pairRangesString.split(',', '-')
                    .let { it[0].toInt()..it[1].toInt() to it[2].toInt()..it[3].toInt() }
            }
            .fold(0) { acc, (firstRange, secondRange) ->
                if ((firstRange intersect secondRange).isNotEmpty())
                    acc + 1
                else
                    acc
            }

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}