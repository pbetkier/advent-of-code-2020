@file:Suppress("UnstableApiUsage")

import com.google.common.io.Resources

fun day3a(inputFile: String): Int {
    val area = loadDay3(inputFile)
    return treesOnSlope(area, 3, 1)
}

fun day3b(inputFile: String): Long {
    val area = loadDay3(inputFile)
    var result = 1L
    listOf(
        treesOnSlope(area, 1, 1),
        treesOnSlope(area, 3, 1),
        treesOnSlope(area, 5, 1),
        treesOnSlope(area, 7, 1),
        treesOnSlope(area, 1, 2)
    ).forEach {
        result *= it
    }

    return result
}

private fun treesOnSlope(area: Area, stepX: Int, stepY: Int): Int {
    var treesEncountered = 0
    repeat(area.height / stepY) {
        if (hasTree(area, it, stepX, stepY)) {
            treesEncountered += 1
        }
    }

    return treesEncountered
}

private fun hasTree(area: Area, step: Int, stepX: Int, stepY: Int) =
        area.trees.contains(Pair((step * stepX) % area.width, step * stepY))

private fun loadDay3(fileName: String): Area {
    val lines = Resources.readLines(Resources.getResource(fileName), Charsets.UTF_8)
    val trees = HashSet<Pair<Int, Int>>()
    lines.forEachIndexed { y, line ->
        line.forEachIndexed { x, char ->
            if (char == '#') {
                trees.add(Pair(x, y))
            }
        }
    }

    return Area(lines[0].length, lines.size, trees)
}

private data class Area(
        val width: Int,
        val height: Int,
        val trees: Set<Pair<Int, Int>>
)

fun main() {
    println(day3a("day3"))
    println(day3b("day3"))
}
