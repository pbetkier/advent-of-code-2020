@file:Suppress("UnstableApiUsage")

import com.google.common.collect.Sets

fun day1a(input: List<Int>) = day1(input, 2)

fun day1b(input: List<Int>) = day1(input, 3)

private fun day1(input: List<Int>, size: Int) = Sets.combinations(input.toSet(), size)
            .firstOrNull { it.sum() == 2020 }
            ?.reduce { a, x -> a * x } ?: -1

fun main(args: Array<String>) {
    println(day1a(loadInts("day1")))
    println(day1b(loadInts("day1")))
}
