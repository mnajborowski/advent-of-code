package aoc2022

import readInput

fun main() {
    fun part1(input: String): Int {
        input.substring(4, input.length).foldIndexed(ArrayDeque(input.take(4).toList())) { i, acc, e ->
            if (acc.toSet().size == 4)
                return i + 4
            else {
                acc.removeFirst()
                acc.addLast(e)
                acc
            }
        }
        throw Exception("Marker not found")
    }

    fun part2(input: String): Int {
        input.substring(14, input.length).foldIndexed(ArrayDeque(input.take(14).toList())) { i, acc, e ->
            if (acc.toSet().size == 14)
                return i + 14
            else {
                acc.removeFirst()
                acc.addLast(e)
                acc
            }
        }
        throw Exception("Marker not found")
    }

    val input = readInput("Day06")
    println(part1(input.first()))
    println(part2(input.first()))
}
