package aoc2022

import readInput

fun main() {
    fun part1(input: List<String>): Int =
        input
            .map { pairRangesString ->
                pairRangesString.split(',', '-')
                    .let { it[0].toInt()..it[1].toInt() to it[2].toInt()..it[3].toInt() }
            }
            .count { (firstRange, secondRange) ->
                firstRange.all { it in secondRange } || secondRange.all { it in firstRange }
            }

    fun part2(input: List<String>): Int =
        input
            .map { pairRangesString ->
                pairRangesString.split(',', '-')
                    .let { it[0].toInt()..it[1].toInt() to it[2].toInt()..it[3].toInt() }
            }
            .count { (firstRange, secondRange) ->
                (firstRange intersect secondRange).isNotEmpty()
            }

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
