@file:Suppress("UnstableApiUsage")

import com.google.common.io.Resources
import kotlin.math.abs

fun day12a(inputFile: String): Int {
    val instructions = loadDay12(inputFile)
    val directions = listOf('E', 'S', 'W', 'N')
    var currentDirection = 0

    val distances = mutableMapOf(Pair('N', 0), Pair('E', 0), Pair('S', 0), Pair('W', 0))

    instructions.forEach {
        when (it.first) {
            'N', 'S', 'E', 'W' -> distances.compute(it.first) { k, v -> v!! + it.second }
            'L' -> currentDirection = (directions.size + currentDirection - (it.second / 90)) % directions.size
            'R' -> currentDirection = (directions.size + currentDirection + (it.second / 90)) % directions.size
            'F' -> distances.compute(directions[currentDirection]) { k, v -> v!! + it.second }
        }
    }

    return abs(distances['N']!! - distances['S']!!) + abs(distances['E']!! - distances['W']!!)
}

fun day12b(inputFile: String): Int {
    val instructions = loadDay12(inputFile)
    val directions = listOf('E', 'S', 'W', 'N')
    var currentDirection = 0

    val weights = mapOf(Pair('N', 1), Pair('E', 1), Pair('S', -1), Pair('W', -1))
    var waypointNorth = 1
    var waypointEast = 10

    var shipNorth = 0
    var shipEast = 0

    instructions.forEach {
        when (it.first) {
            'N', 'S' -> waypointNorth += weights[it.first]!! * it.second
            'E', 'W' -> waypointEast += weights[it.first]!! * it.second
            'L', 'R' -> {
                repeat (normalizeToStepsRight(it.first, it.second)) {
                    val prevN = waypointNorth
                    waypointNorth = -waypointEast
                    waypointEast = prevN
                }
            }
            'F' -> {
                shipNorth += waypointNorth * it.second
                shipEast += waypointEast * it.second
            }
        }
    }

    return abs(shipNorth) + abs(shipEast)
}

fun normalizeToStepsRight(direction: Char, degrees: Int): Int {
    if (direction == 'R') {
        return degrees / 90
    }

    return (360 - degrees) / 90
}

private fun loadDay12(inputFile: String): List<Pair<Char, Int>> {
    return Resources.readLines(Resources.getResource(inputFile), Charsets.UTF_8)
            .map { Pair(it[0], it.substring(1).toInt()) }
}

fun main() {
    println(day12a("day12"))
    println(day12b("day12"))
}
