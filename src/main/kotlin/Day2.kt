@file:Suppress("UnstableApiUsage")

import com.google.common.io.Resources
import java.lang.IllegalArgumentException
import java.util.regex.Pattern

private val inputPattern = Pattern.compile("(\\d+)-(\\d+) ([a-z]): ([a-z]+)")

fun day2a(inputFile: String) = loadDay2(inputFile)
        .filter { occurrences(it.char, it.password) in it.min..it.max }
        .count()

fun day2b(inputFile: String) = loadDay2(inputFile)
        .filter { (it.password[it.min - 1] == it.char).xor(it.password[it.max - 1] == it.char) }
        .count()

private fun occurrences(char: Char, string: String) = string.count { it == char }

private fun loadDay2(fileName: String): List<Input> =
        Resources.readLines(Resources.getResource(fileName), Charsets.UTF_8)
                .map {
                    val matcher = inputPattern.matcher(it)
                    if (!matcher.matches()) {
                        throw IllegalArgumentException("'$it' not matched")
                    }
                    Input(
                            min = matcher.group(1).toInt(),
                            max = matcher.group(2).toInt(),
                            char = matcher.group(3)[0],
                            password = matcher.group(4)
                    )
                }

data class Input(val min: Int, val max: Int, val char: Char, val password: String)

fun main() {
    println(day2a("day2"))
    println(day2b("day2"))
}
