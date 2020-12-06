@file:Suppress("UnstableApiUsage")

import com.google.common.io.Resources

fun day6a(inputFile: String): Int {
    return loadDay6(inputFile, "")
            .map { it.toSet() }
            .map { it.size }
            .sum()
}

fun day6b(inputFile: String): Int {
    return loadDay6(inputFile, " ")
            .map { it.split(' ') }
            .map { it.map { it.toSet() } }
            .map { it.reduce { acc, set -> acc.intersect(set) } }
            .map { it.size }
            .sum()

}

private fun loadDay6(fileName: String, join: String) =
    Resources.getResource(fileName).readText(Charsets.UTF_8)
            .split("\n\n")
            .map { it.replace("\n", join) }

fun main() {
    println(day6a("day6"))
    println(day6b("day6"))
}
