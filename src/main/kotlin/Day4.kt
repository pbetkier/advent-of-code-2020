@file:Suppress("UnstableApiUsage")

import com.google.common.io.Resources

private val hclRegex = Regex("^#[a-f0-9][a-f0-9][a-f0-9][a-f0-9][a-f0-9][a-f0-9]$")
private val validEcls = setOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
private val pidRegex = Regex("^[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]$")

fun day4a(inputFile: String): Int {
    return loadDay4(inputFile)
            .filter { it.fields.size == 8 || "cid" !in it.fields && it.fields.size == 7 }
            .count()

}

fun day4b(inputFile: String): Int {
    return loadDay4(inputFile)
            .asSequence()
            .filter { it.fields.size == 8 || "cid" !in it.fields && it.fields.size == 7 }
            .filter { it.fields["byr"]!!.toIntOrNull() ?: -1 in 1920..2002 }
            .filter { it.fields["iyr"]!!.toIntOrNull() ?: -1 in 2010..2020 }
            .filter { it.fields["eyr"]!!.toIntOrNull() ?: -1 in 2020..2030 }
            .filter {
                val raw = it.fields["hgt"]!!
                if (raw.endsWith("cm")) {
                    return@filter raw.substringBefore("cm").toIntOrNull() ?: -1 in 150..193
                } else if (raw.endsWith("in")) {
                    return@filter raw.substringBefore("in").toIntOrNull() ?: -1 in 59..76
                }
                false
            }
            .filter { it.fields["hcl"]!!.matches(hclRegex) }
            .filter { it.fields["ecl"]!! in validEcls }
            .filter { it.fields["pid"]!!.matches(pidRegex) }
            .count()
}

private fun loadDay4(fileName: String): List<Doc> {
    val content = Resources.getResource(fileName).readText(Charsets.UTF_8)
    val rawDocs = content.split("\n\n").map { it.replace('\n', ' ') }

    return rawDocs.map { line ->
        val fields = HashMap<String, String>()
        line.split(' ').forEach {
            fields[it.substringBefore(':')] = it.substringAfter(':')
        }
        Doc(fields)
    }
}

private data class Doc(val fields: Map<String, String>)

fun main() {
    println(day4a("day4"))
    println(day4b("day4"))
}
