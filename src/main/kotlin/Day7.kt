@file:Suppress("UnstableApiUsage")

import com.google.common.graph.Graph
import com.google.common.graph.GraphBuilder
import com.google.common.graph.Traverser
import com.google.common.io.Resources

val contentRegex = Regex("\\d+ ([a-z ]+) bag")
val contentRegexB = Regex("(\\d+) ([a-z ]+) bag")

fun day7a(inputFile: String): Int {
    val entryPoints = mutableListOf<String>()
    val graph = GraphBuilder.directed().build<String>()
    loadDay7(inputFile)
            .asSequence()
            .map { Pair(it.substringBefore(" bags "), extractContent(it.substringAfter(" bags contain "))) }
            .forEach {
                entryPoints.add(it.first)
                it.second.forEach { next -> graph.putEdge(it.first, next) }
            }

    return entryPoints
            .filter { it != "shiny gold" }
            .filter {
                Traverser.forGraph(graph).breadthFirst(it).contains("shiny gold")
            }
            .count()
}

fun day7b(inputFile: String): Int {
    val counts = mutableMapOf<Pair<String, String>, Int>()
    val graph = GraphBuilder.directed().build<String>()
    loadDay7(inputFile)
            .asSequence()
            .map { Pair(it.substringBefore(" bags "), extractContentB(it.substringAfter(" bags contain "))) }
            .forEach {
                it.second.forEach { next ->
                    counts[Pair(it.first, next.second)] = next.first
                    graph.putEdge(it.first, next.second)
                }
            }

    return bagsInside("shiny gold", graph, counts)
}

fun bagsInside(from: String, graph: Graph<String>, counts: Map<Pair<String, String>, Int>): Int {
    return graph.successors(from)
            .map { counts[Pair(from, it)]!! + counts[Pair(from, it)]!! * bagsInside(it, graph, counts)}
            .sum()
}

fun extractContent(value: String): Collection<String> {
    return contentRegex.findAll(value)
            .map { it.groups[1]!!.value }
            .toList()
}

fun extractContentB(value: String): Collection<Pair<Int, String>> {
    return contentRegexB.findAll(value)
            .map { Pair(it.groups[1]!!.value.toInt(), it.groups[2]!!.value) }
            .toList()
}

private fun loadDay7(fileName: String) =
        Resources.readLines(Resources.getResource(fileName), Charsets.UTF_8)

fun main() {
    println(day7a("day7"))
    println(day7b("day7"))
}
