@file:Suppress("UnstableApiUsage")

import com.google.common.io.Resources

fun day5a(inputFile: String): Int {
    return loadDay5(inputFile)
            .map { Pair(coord(it.bf, 128), coord(it.lr, 8)) }
            .map { it.first * 8 + it.second }
            .max()!!
}

private fun coord(coords: String, high: Int): Int {
    var regionStart = 0
    var regionEnd = high
    coords.forEach {
        val div = regionStart + (regionEnd - regionStart) / 2
        when (it) {
            'F', 'L' -> {
                regionEnd = div
            }
            'B', 'R' -> {
                regionStart = div
            }
        }
    }

    return regionStart
}

fun day5b(inputFile: String): Int {
    val ids = loadDay5(inputFile)
            .map { Pair(coord(it.bf, 128), coord(it.lr, 8)) }
            .map { it.first * 8 + it.second }
            .toSet()

    for (r in 0..128) {
        for (c in 0..8) {
            val potentialSeat = r * 8 + c
            if (potentialSeat !in ids &&
                    potentialSeat - 1 in ids &&
                    potentialSeat + 1 in ids) {
                return potentialSeat
            }
        }
    }

    return -1
}

private fun loadDay5(fileName: String) =
        Resources.readLines(Resources.getResource(fileName), Charsets.UTF_8)
                .map { Pass(it.substring(0, 7), it.substring(7)) }

private class Pass(val bf: String, val lr: String)

fun main() {
    println(day5a("day5"))
    println(day5b("day5"))
}
