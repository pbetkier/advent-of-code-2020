@file:Suppress("UnstableApiUsage")

const val MISSING = -1

fun day15a(input: String): Int {
    return day15(input, 2020)
}

fun day15b(input: String): Int {
    return day15(input, 30000000)
}

private fun day15(input: String, stopAt: Int): Int {
    val whenSpoken = mutableMapOf<Int, Pair<Int, Int>>()
    val numbers = input.split(',').map { it.toInt() }
    numbers.forEachIndexed { index, item ->
        whenSpoken[item] = Pair(MISSING, index + 1)
    }

    var turn = numbers.size
    var lastSpoken = numbers.last()
    while (true) {
        turn += 1
        if (whenSpoken[lastSpoken]!!.first == MISSING) {
            lastSpoken = 0
        } else {
            lastSpoken = whenSpoken[lastSpoken]!!.run {
                this.second - this.first
            }
        }

        if (turn == stopAt) {
            return lastSpoken
        }

        if (lastSpoken !in whenSpoken) {
            whenSpoken[lastSpoken] = Pair(MISSING, turn)
        } else {
            whenSpoken.compute(lastSpoken) { k, v -> Pair(v!!.second, turn)}
        }
    }
}

fun main() {
    println(day15a("5,2,8,16,18,0,1"))
    println(day15b("5,2,8,16,18,0,1"))
}
