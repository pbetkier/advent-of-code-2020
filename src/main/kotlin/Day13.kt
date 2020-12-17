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
    println(busOffsets)

    // find max bus
    // sorted map descending (bus, offset) without max bus
    // long ts = max bus - bus.offset
    // while (true)
    //   found = true
    //   sorted map.each
    //     (ts + offset) % bus != 0 { found=false; break }
    //   if found
    //     return ts
    //   ts += max bus

    val maxBus = busOffsets.keys.max()!!
    val busOffsetsSorted = busOffsets.toSortedMap(reverseOrder())
    busOffsetsSorted.remove(maxBus)

    var ts = (maxBus - busOffsets[maxBus]!!).toLong()
    while (true) {
        var found = true
        busOffsetsSorted.forEach { bus, offset ->
            if ((ts + offset) % bus != 0L) {
                found = false
                return@forEach
            }
        }
        if (found) {
            return ts
        }
        ts += maxBus

        if (ts % 100000000 == 0L) {
            println(ts / 100000000000000.toDouble())
        }
    }
}

// gcd b1, b2 gdzie szukamy nie do 0 a do offsetu?

private fun loadDay13(inputFile: String): Input13 {
    val lines = Resources.readLines(Resources.getResource(inputFile), Charsets.UTF_8)
    return Input13(
            lines[0].toInt(),
            lines[1].split(',').filter { it != "x" }.map { it.toInt() }
    )
}

private fun loadDay13b(inputFile: String): Map<Int, Int> {
    val lines = Resources.readLines(Resources.getResource(inputFile), Charsets.UTF_8)
    val busOffset = mutableMapOf<Int, Int>()
    lines[1].split(',').forEachIndexed { i, b ->
        if (b != "x") {
            busOffset[b.toInt()] = i
        }
    }
    return busOffset
}

private data class Input13(val current: Int, val buses: List<Int>)

fun main() {
    println(day13a("day13"))
    println(day13b("day13"))
}
