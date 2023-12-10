import kotlin.time.measureTime

fun main() {
    val input = readInput("Day05")

    getSeeds(input).minOfOrNull { seed ->
        var thing = seed
        getMaps(input).map { map ->
            thing = mapFromTo(input, thing, map)
        }
        thing
    }.println()


    // TODO: brute force, takes ~1 week to run.
    var min = Long.MAX_VALUE
    getSeedsByRange(input).forEachIndexed { _, longRange ->
        longRange.forEach { i ->
            var thing = i
            getMaps(input).map { map ->
                thing = mapFromTo(input, thing, map)
            }

            if (thing < min) {
                min = thing
            }
        }
    }
    println(min)
}

fun getSeeds(input: List<String>): List<Long> = input[0].split(":")[1].trim().split(" ").map { it.toLong() }

fun getSeedsByRange(input: List<String>): List<LongRange> =
    getSeeds(input).windowed(2, 2).map { (seedStart, range) -> seedStart until seedStart + range }

fun getMaps(input: List<String>): List<String> = input.filter { it.contains("map:") }

fun mapFromTo(input: List<String>, seed: Long, map: String): Long {
    val start = input.indexOf(map)
    val end = (start..input.lastIndex).find { input[it] == "" } ?: input.lastIndex

    input.slice(start + 1 until end).map {
        val (first, second, third) = it.split(" ").map { s -> s.toLong() }
        Triple(first, second, third)
    }.forEach { (toIndex, fromIndex, range) ->
        if (seed in fromIndex until fromIndex + range) {
            return toIndex + (seed - fromIndex)
        }
    }

    return seed
}