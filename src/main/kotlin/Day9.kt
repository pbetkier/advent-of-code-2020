@file:Suppress("UnstableApiUsage")

fun day9a(inputFile: String, preambleLength: Int = 25): Long {
    val numbers = loadLongs(inputFile)

    val sums = mutableMapOf<Long, Int>()
    for (x in 0 until preambleLength) {
        for (y in x + 1 until preambleLength) {
            sums.compute(numbers[x] + numbers[y]) { k, v -> (v ?: 0) + 1 }
        }
    }

    for (i in preambleLength until numbers.size) {
        if (sums.getOrDefault(numbers[i], 0) == 0) {
            return numbers[i]
        }

        for (j in 1 until preambleLength) {
            sums.compute(numbers[i-preambleLength] + numbers[i - preambleLength + j]) { k, v -> v!! - 1 }
            sums.compute(numbers[i] + numbers[i - preambleLength + j]) { k, v -> (v ?: 0) + 1 }
        }
    }

    return -1
}

fun day9b(inputFile: String, preambleLength: Int = 25): Long {
    val numbers = loadLongs(inputFile)
    val invalid = day9a(inputFile, preambleLength)

    for (start in numbers.indices) {
        var size = 0
        var sum = numbers[start]
        var min = numbers[start]
        var max = numbers[start]
        while (sum < invalid) {
            size += 1
            sum += numbers[start+size]
            min = kotlin.math.min(min, numbers[start + size])
            max = kotlin.math.max(max, numbers[start + size])
        }

        if (sum == invalid) {
            return min + max
        }
    }

    return -1
}

fun main() {
    println(day9a("day9"))
    println(day9b("day9"))
}
