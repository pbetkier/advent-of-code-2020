@file:Suppress("UnstableApiUsage")

fun day10a(inputFile: String): Int {
    val adapters = loadInts(inputFile).sorted()
    val diffs = mutableMapOf<Int, Int>()
    for (i in 1 until adapters.size) {
        diffs.compute(adapters[i] - adapters[i - 1]) { k, v -> (v ?: 0) + 1 }
    }
    return (diffs.getOrDefault(1, 0) + 1) *
            (diffs.getOrDefault(3, 0) + 1)
}

fun day10b(inputFile: String): Long {
    val adapters = loadInts(inputFile).sorted()
    return rng(adapters, 0, listOf(0))
}

val memo = mutableMapOf<Pair<Int, List<Int>>, Long>()

fun rng(input: List<Int>, pos: Int, prev: List<Int>): Long {
    if (memo.contains(Pair(pos, prev))) {
        return memo[Pair(pos, prev)]!!
    }
    if (pos == input.size - 1) {
        return 1
    }

    val advancedPrev = (prev + input[pos]).takeLast(3)
    if (valid(prev + input[pos + 1])) {
        val res = rng(input, pos + 1, prev) + rng(input, pos + 1, advancedPrev)
        memo[Pair(pos, prev)] = res
        return res
    }

    val res = rng(input, pos + 1, advancedPrev)
    memo[Pair(pos, prev)] = res
    return res
}

fun valid(input: List<Int>): Boolean {
    for (i in 1 until input.size) {
        if (input[i] - input[i - 1] > 3) {
            return false
        }
    }
    return true
}

fun main() {
    println(day10a("day10"))
    println(day10b("day10"))
}
