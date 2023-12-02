val NumbersArray = arrayOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9")

val NumbersMap = mapOf(
    "1" to "1",
    "2" to "2",
    "3" to "3",
    "4" to "4",
    "5" to "5",
    "6" to "6",
    "7" to "7",
    "8" to "8",
    "9" to "9",
    "one" to "1",
    "two" to "2",
    "three" to "3",
    "four" to "4",
    "five" to "5",
    "six" to "6",
    "seven" to "7",
    "eight" to "8",
    "nine" to "9"
)

fun main() {
    print("Part 1: ")
    readInput("Day01")
        .sumOf { line ->
            val (first) = NumbersArray
                .map { it to line.indexOf(it) }
                .minBy { (_, value) -> if (value < 0) Int.MAX_VALUE else value }

            val (last) = NumbersArray
                .map { it to line.lastIndexOf(it) }
                .maxBy { (_, value) -> if (value < 0) Int.MIN_VALUE else value }

            (first + last).toInt()
        }.println()

    print("Part 2: ")
    readInput("Day01")
        .sumOf { line ->
            val (first) = NumbersMap.keys
                .map { it to line.indexOf(it) }
                .minBy { (_, value) -> if (value < 0) Int.MAX_VALUE else value }

            val (last) = NumbersMap.keys
                .map { it to line.lastIndexOf(it) }
                .maxBy { (_, value) -> if (value < 0) Int.MIN_VALUE else value }

            (NumbersMap[first] + NumbersMap[last]).toInt()
        }.println()
}
