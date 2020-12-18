@file:Suppress("UnstableApiUsage")

import com.google.common.collect.Comparators
import com.google.common.io.Resources
import kotlin.math.abs
import kotlin.math.min

fun day13a(inputFile: String): Int {
    val input = loadDay13(inputFile)

    var minTime = Int.MAX_VALUE
    var minBus = -1
    input.buses.forEach {
        val waitTime = it - (input.current % it)
        if (waitTime < minTime) {
            minBus = it
        }
        minTime = min(minTime, waitTime)
    }

    return minTime * minBus
}

fun day13b(inputFile: String): Long {
    val busOffsets = loadDay13b(inputFile)

    var agg = busOffsets[0].first.toLong()
    var plus = 0L
    for (b in 1 until busOffsets.size) {
        for (i in 0 until busOffsets[b].first) {
            if ((agg * i + plus + busOffsets[b].second) % busOffsets[b].first == 0L) {
                if (b == busOffsets.size - 1) {
                    return agg * i + plus
                }
                plus += agg * i
                agg *= busOffsets[b].first
                break
            }
        }
    }
    return -1
}

private fun loadDay13(inputFile: String): Input13 {
    val lines = Resources.readLines(Resources.getResource(inputFile), Charsets.UTF_8)
    return Input13(
            lines[0].toInt(),
            lines[1].split(',').filter { it != "x" }.map { it.toInt() }
    )
}

private fun loadDay13b(inputFile: String): List<Pair<Int, Int>> {
    val lines = Resources.readLines(Resources.getResource(inputFile), Charsets.UTF_8)
    val busOffset = mutableListOf<Pair<Int, Int>>()
    lines[1].split(',').forEachIndexed { i, b ->
        if (b != "x") {
            busOffset.add(Pair(b.toInt(), i))
        }
    }
    return busOffset
}

private data class Input13(val current: Int, val buses: List<Int>)

fun main() {
    println(day13a("day13"))
    println(day13b("day13"))
}
