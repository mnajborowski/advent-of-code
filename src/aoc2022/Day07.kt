package aoc2022

import readInput

fun main() {
    fun prepareTree(input: List<String>): Map<String, Int> {
        var cwd = ""
        return buildMap {
            (input + "\$ cd ..").forEach { command ->
                when {
                    command.any { it.isDigit() } ->
                        putAll(mapOf(cwd to getOrElse(cwd) { 0 } + command.filter { it.isDigit() }.toInt()))

                    command.matches("\\$ cd [a-z]+".toRegex()) -> cwd += "/" + command.split(" ").last()
                    command.matches("\\$ cd ..".toRegex()) -> {
                        val pwd = cwd.substring(0, cwd.indexOfLast { it == '/' })
                        putAll(mapOf(pwd to getOrElse(pwd) { 0 } + getOrElse(cwd) { 0 }))
                        cwd = pwd
                    }
                }
            }
            while (cwd != "") {
                val pwd = cwd.substring(0, cwd.indexOfLast { it == '/' })
                putAll(mapOf(pwd to getOrElse(pwd) { 0 } + getOrElse(cwd) { 0 }))
                cwd = pwd
            }
        }
    }

    fun part1(input: List<String>): Int =
        prepareTree(input).values.filter { it <= 100000 }.sum()

    fun part2(input: List<String>): Int {
        val tree = prepareTree(input)
        return tree.values.sorted().first { 70000000 - tree[""]!! + it >= 30000000 }
    }

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}
