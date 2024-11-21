package aoc2022

import readInput

fun main() {
    val input = readInput("Day05")
    val stackLabels = input[8]

    fun prepareStacks(): List<ArrayDeque<Char>> =
        stackLabels.last { it.isDigit() }.let { finalStackLabel ->
            ('1'..finalStackLabel).map { label ->
                ArrayDeque(
                    input.take(8).map { floor ->
                        floor.getOrElse(stackLabels.indexOf(label)) { ' ' }
                    }.filter { it.isLetter() }
                )
            }
        }

    val instructions =
        input.subList(10, input.size)
            .map { instruction -> instruction.split("move ", " from ", " to ").takeLast(3).map { it.toInt() } }

    fun part1(): String {
        val stacks = prepareStacks()
        instructions.forEach { instruction ->
            repeat((0 until instruction[0]).count()) {
                stacks[instruction[2] - 1].addFirst(stacks[instruction[1] - 1].removeFirst())
            }
        }
        return stacks.map { it.first() }.joinToString("")
    }

    fun part2(): String {
        val stacks = prepareStacks()
        instructions.forEach { instruction ->
            stacks[instruction[2] - 1].addAll(0, stacks[instruction[1] - 1].take(instruction[0]))
            repeat((0 until instruction[0]).count()) { stacks[instruction[1] - 1].removeFirst() }
        }
        return stacks.map { it.firstOrNull() ?: "" }.joinToString("")
    }

    println(part1())
    println(part2())
}
