@file:Suppress("UnstableApiUsage")

import com.google.common.collect.Iterators
import com.google.common.io.Resources
import java.lang.IllegalArgumentException
import java.util.*

fun day14a(inputFile: String): Long {
    val programs = loadDay14(inputFile)
    val memory = mutableMapOf<Long, Long>()

    programs.forEach {
        val andMask = it.mask.replace('X', '1').toLong(2)
        val orMask = it.mask.replace('X', '0').toLong(2)
        it.operations.forEach { (addr, value) ->
            memory[addr] = value.and(andMask).or(orMask)
        }
    }

    return memory.values.sum()
}

fun day14b(inputFile: String): Long {
    val programs = loadDay14(inputFile)
    val memory = mutableMapOf<Long, Long>()

    programs.forEach {
        it.operations.forEach { (addr, value) ->
            val floatingResult = addr.toString(2).padStart(it.mask.length, '0').toCharArray()
            it.mask.forEachIndexed { index, char ->
                if (char == '1' || char == 'X') {
                    floatingResult[index] = char
                }
            }
            expandMasks(String(floatingResult)).forEach {
                memory[it.toLong(2)] = value
            }
        }
    }

    return memory.values.sum()

}

private fun expandMasks(mask: String): MutableList<String> {
    val masks = mutableListOf<String>()
    val queue = LinkedList<String>()
    queue.offer(mask)
    while (queue.isNotEmpty()) {
        val m = queue.remove()
        if (m.contains('X')) {
            queue.offer(m.replaceFirst('X', '1'))
            queue.offer(m.replaceFirst('X', '0'))
        } else {
            masks.add(m)
        }
    }
    return masks
}

private fun loadDay14(inputFile: String): List<Program14> {
    val lines = Resources.readLines(Resources.getResource(inputFile), Charsets.UTF_8)
    val iter = Iterators.peekingIterator(lines.iterator())
    val programs = mutableListOf<Program14>()

    var mask = ""
    val operations = mutableListOf<Pair<Long, Long>>()
    while (iter.hasNext()) {
        val line = iter.next()
        when {
            line.startsWith("mask") -> mask = line.substringAfter("mask = ")
            line.startsWith("mem") -> {
                val fragments = line.split("[", "]", " = ")
                operations.add(Pair(fragments[1].toLong(), fragments[3].toLong()))
            }
            else -> throw IllegalArgumentException(line)
        }

        if (!iter.hasNext() || iter.peek().startsWith("mask")) {
            programs.add(Program14(mask, operations.toList()))
            mask = ""
            operations.clear()
        }
    }
    return programs
}

private data class Program14(val mask: String, val operations: List<Pair<Long, Long>>)

fun main() {
    println(day14a("day14"))
    println(day14b("day14"))
}
