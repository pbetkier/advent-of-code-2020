@file:Suppress("UnstableApiUsage")

import com.google.common.io.Resources
import java.util.stream.Collectors

fun day16a(inputFile: String): Long {
    val input = loadDay16(inputFile)
    val ranges = input.fields.values
            .flatMap { it.toList() }
            .map { it.toSet() }
            .reduce { acc, x -> acc.union(x) }

    return input.nearby
            .flatten()
            .filter { it !in ranges }
            .map { it.toLong() }
            .sum()
}

@ExperimentalStdlibApi
fun day16b(inputFile: String): Long {
    val input = loadDay16(inputFile)
    val ranges = input.fields.values
            .flatMap { it.toList() }
            .map { it.toSet() }
            .reduce { acc, x -> acc.union(x) }

    val matchingFieldsOnPositions = mutableListOf<Set<String>>()
    val validTickets = input.nearby.filter { it.all { it in ranges } }
    for (pos in validTickets[0].indices) {
        matchingFieldsOnPositions.add(
                input.fields.filter { (_, r) ->
                    validTickets.all { it[pos] in r.first || it[pos] in r.second }
                }.map { it.key }.toSet()
        )
    }

    val positionPtrs = matchingFieldsOnPositions.indices.toList().sortedBy { matchingFieldsOnPositions[it].size }
    val work = ArrayDeque<Work>()
    work.addFirst(Work(listOf()))
    while (!work.isEmpty()) {
        val w = work.removeFirst()
        if (w.matchedFields.size == positionPtrs.size) {
            return w.matchedFields
                    .mapIndexed { index, field -> Pair(positionPtrs[index], field)}
                    .filter { it.second.startsWith("departure") }
                    .map { input.my[it.first].toLong() }
                    .reduce { acc, item -> acc * item}
        }

        matchingFieldsOnPositions[positionPtrs[w.matchedFields.size]].subtract(w.matchedFields).forEach {
            work.addFirst(Work(w.matchedFields + it))
        }
    }

    return -1
}

private data class Work(val matchedFields: List<String>)

private val fieldRegex = Regex("([a-z ]+): (\\d+)-(\\d+) or (\\d+)-(\\d+)")

private fun loadDay16(inputFile: String): Input16 {
    val lines = Resources.readLines(Resources.getResource(inputFile), Charsets.UTF_8)
    val yourTicketPos = lines.indexOf("your ticket:")

    val fields: Map<String, Pair<IntRange, IntRange>> = lines.subList(0, yourTicketPos - 1)
            .stream()
            .map { fieldRegex.find(it)!!.groups.map { it!!.value } }
            .collect(Collectors.toMap({ x -> x[1] }, { x -> Pair(x[2].toInt()..x[3].toInt(), x[4].toInt()..x[5].toInt()) }))

    val my = lines[yourTicketPos + 1]
            .split(',')
            .map { it.toInt() }

    val nearby = lines.subList(yourTicketPos + 4, lines.size)
            .map { it.split(',').map { it.toInt() } }

    return Input16(fields, my, nearby)
}

private data class Input16(val fields: Map<String, Pair<IntRange, IntRange>>, val my: List<Int>, val nearby: List<List<Int>>)

@ExperimentalStdlibApi
fun main() {
    println(day16a("day16"))
    println(day16b("day16"))
}
