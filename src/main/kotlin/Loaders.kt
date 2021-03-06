@file:Suppress("UnstableApiUsage")

import com.google.common.io.Resources

fun loadInts(fileName: String): List<Int> =
    Resources.readLines(Resources.getResource(fileName), Charsets.UTF_8).map { x -> x.toInt() }

fun loadLongs(fileName: String): List<Long> =
        Resources.readLines(Resources.getResource(fileName), Charsets.UTF_8).map { x -> x.toLong() }
