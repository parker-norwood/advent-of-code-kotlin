val BagPossibilities = mapOf(
    "red" to 12,
    "green" to 13,
    "blue" to 14
)

fun main() {
    print("Part 1: ")
    readInput("Day02")
        .map { line ->
            val (idString, game) = line.split(':')

            val id = idString.split(" ").last().toInt()

            game
                .split(';')
                .flatMap { it.split(',') }
                .fold(mutableMapOf<String, Int>()) { acc, s ->
                    val (countString, color) = s.trim().split(' ')
                    val count = countString.toInt()

                    acc.merge(color, count) { old, new -> if (old > new) old else new }

                    acc
                }.entries
                .forEach { (color, count) ->
                    if (BagPossibilities[color]!! < count) {
                        return@map 0
                    }
                }

            return@map id
        }.sum()
        .println()

    print("Part 2: ")
    readInput("Day02").sumOf { line ->
        line.split(':')
            .last()
            .split(';')
            .flatMap { it.split(',') }
            .fold(mutableMapOf<String, Int>()) { acc, s ->
                val (countString, color) = s.trim().split(' ')
                val count = countString.toInt()

                acc.merge(color, count) { old, new -> if (old > new) old else new }

                acc
            }.values
            .reduce { acc, c -> acc * c }
    }.println()
}