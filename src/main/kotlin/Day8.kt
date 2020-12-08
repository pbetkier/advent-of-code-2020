@file:Suppress("UnstableApiUsage")

import com.google.common.io.Resources
import java.lang.RuntimeException

fun day8a(inputFile: String): Int {
    val instructions = loadDay8(inputFile)
    return run(instructions).first
}

private fun run(instructions: List<Instruction>): Pair<Int, Boolean> {
    var cursor = 0
    var accValue = 0
    val visitedInstructions = mutableSetOf<Int>()

    while (cursor < instructions.size && cursor !in visitedInstructions) {
        visitedInstructions.add(cursor)
        when (instructions[cursor].type) {
            "nop" -> {
                cursor += 1
            }
            "acc" -> {
                accValue += instructions[cursor].argument
                cursor += 1
            }
            "jmp" -> {
                cursor += instructions[cursor].argument
            }
            else -> throw RuntimeException("what is ${instructions[cursor].type}")
        }
    }

    return Pair(accValue, cursor >= instructions.size)
}

fun day8b(inputFile: String): Int {
    val instructions = loadDay8(inputFile)
    val variants = mutableListOf(instructions)
    instructions.forEachIndexed { index, instruction ->
        val replacement = when (instruction.type) {
            "nop" -> Instruction("jmp", instruction.argument)
            "jmp" -> Instruction("nop", instruction.argument)
            else -> return@forEachIndexed
        }
        val newVariant = mutableListOf(*instructions.toTypedArray())
        newVariant[index] = replacement
        variants.add(newVariant)
    }

    variants.forEach {
        val (acc, finished) = run(it)
        if (finished) {
            return acc
        }
    }

    return -1
}

private fun loadDay8(fileName: String) =
        Resources.readLines(Resources.getResource(fileName), Charsets.UTF_8)
                .map { Instruction(it.substring(0, 3), it.substring(4).toInt()) }

data class Instruction (val type: String, val argument: Int)

fun main() {
    println(day8a("day8"))
    println(day8b("day8"))
}
