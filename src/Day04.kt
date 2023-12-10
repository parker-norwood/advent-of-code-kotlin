import java.util.ArrayDeque
import java.util.Stack
import kotlin.math.pow

fun main() {
    val list = readInput("Day04").toMutableList()

    print("Part 1: ")
    list.sumOf { line ->
        val (card, rest) = line.split(":")
        val (winningNumbers, numbers) = rest
            .split("|")
            .map { n ->
                n
                    .trim()
                    .split(" ")
                    .filter { it.matches(Regex("(\\d)+")) }
            }

        val count = numbers.count { winningNumbers.contains(it) }
        if (count > 0) {
            return@sumOf 2f.pow(count - 1).toInt()
        } else {
            return@sumOf 0
        }
    }.println()

    print("Part 2: ")
    var counter = 0
    val stack = ArrayDeque(list)
    while (stack.isNotEmpty()) {
        val line = stack.pop()
//        println(line)
        val (card, rest) = line.split(":")
        val (winningNumbers, numbers) = rest
            .split("|")
            .map { n ->
                n
                    .trim()
                    .split(" ")
                    .filter { it.matches(Regex("(\\d)+")) }
            }

        val cardNumber = card.split(" ").last().trim().toInt()
        val count = numbers.count { winningNumbers.contains(it) }

        if (count > 0) {
            counter++

            stack.addAll(
                list.subList(
                    cardNumber - 1,
                    if ((cardNumber - 1) + count > list.size) list.size else cardNumber - 1 + count
                )
            )
        }
    }

    println("======================================")
    println("Counter: ")
    println(counter)
}