enum class DIRECTION(val row: Int, val col: Int) {
    UP(-1, 0),
    UP_RIGHT(-1, 1),
    RIGHT(0, 1),
    DOWN_RIGHT(1, 1),
    DOWN(1, 0),
    DOWN_LEFT(1, -1),
    LEFT(0, -1),
    UP_LEFT(-1, -1)
}

val SYMBOLS = Regex("[^\\w\\s.]")

fun main() {
    val input = readInput("Day03")

    val partNumbers = mutableListOf<Int>()

    input.forEachIndexed { row, line ->
        Regex("(\\d)+")
            .findAll(line)
            .forEach { match ->
                if (hasAdjacentSymbol(input, match.range, row)) {
                    partNumbers += match.value.toInt()
                }
            }
    }

    print("Part 1: ")
    partNumbers.sum().println()
}

fun hasAdjacentSymbol(matrix: List<CharSequence>, range: IntRange, row: Int): Boolean {
    range.forEach { col ->
        DIRECTION.entries.forEach { d ->
            if (col + d.col in matrix.indices && row + d.row in matrix.indices) {
                if (matrix[row + d.row][col + d.col].toString().matches(SYMBOLS)) {
                    return true
                }
            }
        }
    }
    return false
}