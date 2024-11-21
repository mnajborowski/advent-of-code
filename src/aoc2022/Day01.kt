package aoc2022

import readInput

fun main() {
    fun part1(input: List<String>): Int {
        var mostCalories = 0
        input.fold(0) { acc, item ->
            if (item.isBlank()) {
                if (acc > mostCalories)
                    mostCalories = acc
                0
            } else
                acc + item.toInt()
        }
        return mostCalories
    }

    fun part2(input: List<String>): Int {
        val totalCaloriesPerElf = mutableListOf<Int>()
        input.fold(0) { acc, item ->
            if (item.isBlank()) {
                totalCaloriesPerElf.add(acc)
                0
            } else
                acc + item.toInt()
        }
        return totalCaloriesPerElf.sortedDescending().take(3).sum()
    }

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
