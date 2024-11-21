fun main() {
    fun List<String>.getMappingFor(mappingHeader: String): List<Triple<Long, Long, Long>> =
        subList(indexOf(mappingHeader) + 1, size).takeWhile { it.isNotEmpty() }.map { line ->
            line.split(' ').let { Triple(it[0].toLong(), it[1].toLong(), it[2].toLong()) }
        }

    fun List<Triple<Long, Long, Long>>.getDestination(source: Long): Long =
        find { it.second <= source && source <= it.second + it.third }?.let {
            it.first + source - it.second
        } ?: source

    fun List<Triple<Long, Long, Long>>.getDestination(sourceRange: Pair<Long, Long>): Pair<Long, Long> =
        Pair(
            first = find { it.second <= sourceRange.first && sourceRange.first <= it.second + it.third }?.let {
                it.first + sourceRange.first - it.second
            } ?: sourceRange.first,
            second = find { it.second <= sourceRange.second && sourceRange.second <= it.second + it.third }?.let {
                it.first + sourceRange.second - it.second
            } ?: sourceRange.second
        )

    fun part1(input: List<String>): Int {
        val seedToSoil = input.getMappingFor("seed-to-soil map:")
        val soilToFertilizer = input.getMappingFor("soil-to-fertilizer map:")
        val fertilizerToWater = input.getMappingFor("fertilizer-to-water map:")
        val waterToLight = input.getMappingFor("water-to-light map:")
        val lightToTemperature = input.getMappingFor("light-to-temperature map:")
        val temperatureToHumidity = input.getMappingFor("temperature-to-humidity map:")
        val humidityToLocation = input.getMappingFor("humidity-to-location map:")
        return input.first().substringAfter(": ").split(' ').minOfOrNull {
            humidityToLocation.getDestination(
                temperatureToHumidity.getDestination(
                    lightToTemperature.getDestination(
                        waterToLight.getDestination(
                            fertilizerToWater.getDestination(
                                soilToFertilizer.getDestination(
                                    seedToSoil.getDestination(
                                        it.toLong()
                                    )
                                )
                            )
                        )
                    )
                )
            )
        }?.toInt() ?: throw NullPointerException()
    }

    fun part2(input: List<String>): Int {
        val seedToSoil = input.getMappingFor("seed-to-soil map:")
        val soilToFertilizer = input.getMappingFor("soil-to-fertilizer map:")
        val fertilizerToWater = input.getMappingFor("fertilizer-to-water map:")
        val waterToLight = input.getMappingFor("water-to-light map:")
        val lightToTemperature = input.getMappingFor("light-to-temperature map:")
        val temperatureToHumidity = input.getMappingFor("temperature-to-humidity map:")
        val humidityToLocation = input.getMappingFor("humidity-to-location map:")
        return input.first().substringAfter(": ").split(' ').chunked(2).flatMap {
            (0L until it.last().toLong()).map { rangeElement ->
                it.first().toLong() + rangeElement
            }
        }.minOfOrNull {
            humidityToLocation.getDestination(
                temperatureToHumidity.getDestination(
                    lightToTemperature.getDestination(
                        waterToLight.getDestination(
                            fertilizerToWater.getDestination(
                                soilToFertilizer.getDestination(
                                    seedToSoil.getDestination(
                                        it
                                    )
                                )
                            )
                        )
                    )
                )
            )
        }?.toInt() ?: throw NullPointerException()
    }

    val testInput = readInput("input_test")
    check(part1(testInput) == 35)
    check(part2(testInput) == 46)

    val input = readInput("input")
//    part1(input).println()
    part2(input).println()
}
