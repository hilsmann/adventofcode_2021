package day10

import java.math.BigInteger
import java.util.*

fun calculateSyntaxErrorScoreFirstStar(syntaxLines: List<String>): Int {
    val syntaxErrors = mutableListOf<Char>()
    val stack: Stack<Char> = Stack()

    for (syntaxLine in syntaxLines) {
        for (chunkSymbol in syntaxLine) {
            if (isOpenChunkSymbol(chunkSymbol)) {
                stack.add(mapOpenChunkSymbolToCloseChunkSymbol(chunkSymbol))
            } else if (stack.pop() != chunkSymbol) {
                syntaxErrors.add(chunkSymbol)
                break
            }
        }
    }

    return calculateSyntaxErrorScore(syntaxErrors)
}

fun calculateAutocompletionScoreSecondStar(syntaxLines: List<String>): BigInteger {
    val autoCompletion = mutableListOf<MutableList<Char>>()

    loop@
    for (syntaxLine in syntaxLines) {
        val autoCompletionSymbols: MutableList<Char> = mutableListOf()
        for (chunkSymbol in syntaxLine) {
            if (isOpenChunkSymbol(chunkSymbol)) {
                autoCompletionSymbols.add(mapOpenChunkSymbolToCloseChunkSymbol(chunkSymbol))
            } else if (autoCompletionSymbols.removeLast() != chunkSymbol) {
                continue@loop
            }
        }
        autoCompletion.add(autoCompletionSymbols)
    }

    return calculateMiddleAutoCompletionScore(autoCompletion)
}

private fun calculateSyntaxErrorScore(syntaxErrors: List<Char>): Int {
    var syntaxErrorScore = 0

    for (symbol in syntaxErrors) {
        syntaxErrorScore += mapSymbolToPointsForSyntaxErrors(symbol)
    }

    return syntaxErrorScore
}


private fun calculateMiddleAutoCompletionScore(autoCompletions: MutableList<MutableList<Char>>): BigInteger {
    val allAutocompletionScores = mutableListOf<BigInteger>()

    for (autoCompletion in autoCompletions) {
        var autocompletionScore = BigInteger.ZERO
        for (symbol in autoCompletion.asReversed()) {
            autocompletionScore *= BigInteger.valueOf(5)
            autocompletionScore += mapSymbolToPointsForAutocompletion(symbol).toBigInteger()
        }
        allAutocompletionScores.add(autocompletionScore)
    }

    allAutocompletionScores.sort()
    return allAutocompletionScores[allAutocompletionScores.size / 2]
}

private fun mapSymbolToPointsForAutocompletion(symbol: Char): Int {
    return when (symbol) {
        '>' -> 4
        ')' -> 1
        '}' -> 3
        ']' -> 2
        else -> throw Exception("Unsupported Symbol")
    }
}

private fun mapSymbolToPointsForSyntaxErrors(symbol: Char): Int {
    return when (symbol) {
        '>' -> 25137
        ')' -> 3
        '}' -> 1197
        ']' -> 57
        else -> throw Exception("Unsupported Symbol")
    }
}

private fun isOpenChunkSymbol(symbol: Char): Boolean {
    return when (symbol) {
        '<' -> true
        '(' -> true
        '{' -> true
        '[' -> true
        else -> false
    }
}

private fun mapOpenChunkSymbolToCloseChunkSymbol(symbol: Char): Char {
    return when (symbol) {
        '<' -> '>'
        '(' -> ')'
        '{' -> '}'
        '[' -> ']'
        else -> throw Exception("Unsupported Symbol")
    }
}

fun main(args: Array<String>) {
    val linesFromFileFirstStar = file.readFileForString("src/day10/day10_first_star.txt")

    val syntaxErrorScoreFirstStar = calculateSyntaxErrorScoreFirstStar(linesFromFileFirstStar)

    println("Result for first star $syntaxErrorScoreFirstStar") // 339477

    val linesFromFileSecondStar = file.readFileForString("src/day10/day10_second_star.txt")

    val autocompletionScoreSecondStar = calculateAutocompletionScoreSecondStar(linesFromFileSecondStar)

    println("Result for second star $autocompletionScoreSecondStar") // 3049320156 TooLow
}
