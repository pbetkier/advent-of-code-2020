@file:Suppress("UnstableApiUsage")

import com.google.common.io.Resources

fun day11a(inputFile: String): Int {
    val area = loadDay11(inputFile)

    while (true) {
        val changes = mutableMapOf<Pair<Int, Int>, Char>()
        area.map.entries
                .filter { it.value != '.' }
                .forEach { (k, v) ->
                    val occupied = (v == '#')
                    val neighboursOccupied = area.neighbours(k)
                    if (!occupied && neighboursOccupied == 0) {
                        changes[k] = '#'
                    } else if (occupied && neighboursOccupied >= 4) {
                        changes[k] = 'L'
                    }
                }
        if (changes.isEmpty()) {
            break
        }
        changes.entries.forEach { (k, v) ->
            area.map.replace(k, v)
        }
    }

    return area.map.values.filter { it == '#' }.count()
}

fun day11b(inputFile: String): Int {
    val area = loadDay11(inputFile)

    while (true) {
        val changes = mutableMapOf<Pair<Int, Int>, Char>()
        area.map.entries
                .filter { it.value != '.' }
                .forEach { (k, v) ->
                    val occupied = (v == '#')
                    val neighboursOccupied = area.neighboursB(k)
                    if (!occupied && neighboursOccupied == 0) {
                        changes[k] = '#'
                    } else if (occupied && neighboursOccupied >= 5) {
                        changes[k] = 'L'
                    }
                }
        if (changes.isEmpty()) {
            break
        }
        changes.entries.forEach { (k, v) ->
            area.map.replace(k, v)
        }
    }

    return area.map.values.filter { it == '#' }.count()
}

private fun loadDay11(inputFile: String): SeatArea {
    val map = mutableMapOf<Pair<Int, Int>, Char>()
    Resources.readLines(Resources.getResource(inputFile), Charsets.UTF_8)
            .forEachIndexed { row, s ->
                s.forEachIndexed { column, c ->
                    map[Pair(row, column)] = c
                }
            }
    return SeatArea(map)
}

private class SeatArea(val map: MutableMap<Pair<Int, Int>, Char>) {
    fun neighbours(k: Pair<Int, Int>): Int {
        var neighbours = 0
        for (i in k.first - 1..k.first + 1) {
            for (j in k.second - 1..k.second + 1) {
                if (i == k.first && j == k.second) {
                    continue
                }
                if (map[Pair(i, j)] == '#') {
                    neighbours += 1
                }
            }
        }
        return neighbours
    }

    fun neighboursB(k: Pair<Int, Int>): Int {
        var neighbours = 0
        for (i in -1..1) {
            for (j in -1..1) {
                if (i == 0 && j == 0) {
                    continue
                }
                var d = 1
                while (true) {
                    val c = map[Pair(i * d + k.first, j * d + k.second)]
                    if (c == null || c == 'L') {
                        break
                    }
                    if (c == '#') {
                        neighbours += 1
                        break
                    }
                    d += 1
                }
            }
        }
        return neighbours
    }
}

fun main() {
    println(day11a("day11"))
    println(day11b("day11"))
}
