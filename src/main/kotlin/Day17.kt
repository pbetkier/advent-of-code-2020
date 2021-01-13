@file:Suppress("UnstableApiUsage")

import com.google.common.collect.Lists
import com.google.common.io.Resources

fun day17a(inputFile: String): Int {
    return day17(inputFile, 3)
}

fun day17b(inputFile: String): Int {
    return day17(inputFile, 4)
}

fun day17(inputFile: String, dims: Int): Int {
    val actives = loadDay17(inputFile, dims)

    repeat(6) {
        val toInactivate = mutableSetOf<Coord>()
        val toActivate = mutableSetOf<Coord>()
        val inactivesToConsider = mutableSetOf<Coord>()

        actives.forEach {
            val (activeNeighbours, inactiveNeighbours) = neighbours(it, actives)
            if (!(activeNeighbours.size == 2 || activeNeighbours.size == 3)) {
                toInactivate.add(it)
            }
            inactivesToConsider.addAll(inactiveNeighbours)
        }

        inactivesToConsider.forEach {
            if (neighbours(it, actives).first.size == 3) {
                toActivate.add(it)
            }
        }

        actives.removeAll(toInactivate)
        actives.addAll(toActivate)
    }

    return actives.size
}

private fun neighbours(from: Coord, actives: Set<Coord>): Pair<Collection<Coord>, Collection<Coord>> {
    val active = mutableListOf<Coord>()
    val inactive = mutableListOf<Coord>()

    val rangesThroughDimensions = from.values.map { (it - 1..it + 1).toList() }
    Lists.cartesianProduct(rangesThroughDimensions)
            .forEach {
                val coord = Coord(it)
                if (coord == from) {
                    return@forEach
                }
                if (coord in actives) {
                    active.add(coord)
                } else {
                    inactive.add(coord)
                }
            }

    return Pair(active, inactive)
}

private data class Coord(val values: List<Int>)

private fun loadDay17(inputFile: String, dims: Int = 3): MutableSet<Coord> {
    val lines = Resources.readLines(Resources.getResource(inputFile), Charsets.UTF_8)
    val actives = mutableSetOf<Coord>()
    lines.forEachIndexed { y, row ->
        row.forEachIndexed { x, state ->
            if (state == '#') {
                val values = mutableListOf(x, y)
                repeat(dims - 2) { values.add(0) }
                actives.add(Coord(values))
            }
        }
    }
    return actives
}

fun main() {
    println(day17a("day17"))
    println(day17b("day17"))
}
