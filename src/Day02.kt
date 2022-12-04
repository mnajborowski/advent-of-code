fun main() {

    fun part1(input: List<String>): Int = input.fold(0) { acc, e ->
        acc + countScorePart1(e.first(), e.last())
    }

    fun part2(input: List<String>): Int = input.fold(0) { acc, e ->
        acc + countScorePart2(e.first(), e.last())
    }

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}

fun countScorePart1(enemyFigure: Char, playerFigure: Char): Int =
    when (enemyFigure to playerFigure) {
        'A' to 'X' -> 4
        'B' to 'X' -> 1
        'C' to 'X' -> 7
        'A' to 'Y' -> 8
        'B' to 'Y' -> 5
        'C' to 'Y' -> 2
        'A' to 'Z' -> 3
        'B' to 'Z' -> 9
        'C' to 'Z' -> 6
        else -> throw IllegalArgumentException("Invalid outcome")
    }

fun countScorePart2(enemyFigure: Char, playerFigure: Char): Int =
    when (enemyFigure to playerFigure) {
        'A' to 'X' -> 3
        'B' to 'X' -> 1
        'C' to 'X' -> 2
        'A' to 'Y' -> 4
        'B' to 'Y' -> 5
        'C' to 'Y' -> 6
        'A' to 'Z' -> 8
        'B' to 'Z' -> 9
        'C' to 'Z' -> 7
        else -> throw IllegalArgumentException("Invalid outcome")
    }