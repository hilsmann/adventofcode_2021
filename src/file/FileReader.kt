package file

import day4.BingoBoard
import day4.BingoSubsystem
import day5.Coordinates
import java.io.File

fun readFileForInteger(fileName: String): List<Int> {
    val lineList = mutableListOf<Int>()

    File(fileName).useLines { lines -> lines.forEach { lineList.add(it.toInt()) } }

    return lineList
}

fun readFileForString(fileName: String): List<String> {
    val lineList = mutableListOf<String>()

    File(fileName).useLines { lines -> lines.forEach { lineList.add(it) } }

    return lineList
}

fun readFileForIntCommaSeperated(fileName: String): List<Int> {
    val lineList = mutableListOf<Int>()

    File(fileName).useLines { lines -> lines.forEach { lineList.addAll(it.split(",").map { number -> number.toInt() }) } }

    return lineList
}

fun readFileForCoordinates(fileName: String): Map<Coordinates, Coordinates> {
    val lineList = mutableMapOf<Coordinates, Coordinates>()

    File(fileName).useLines { lines
        ->
        lines.forEach { line ->
            val lineWithRemovedArrow = line.trim().replace(" -> ".toRegex(), " ")
            val listWithTwoCoordinatesInAString = lineWithRemovedArrow.split(" ")
            val coordinatesFromStringList = listWithTwoCoordinatesInAString[0].split(",")
            val coordinatesFrom = Coordinates(coordinatesFromStringList[0].toInt(), coordinatesFromStringList[1].toInt())

            val coordinatesToStringList = listWithTwoCoordinatesInAString[1].split(",")
            val coordinatesTo = Coordinates(coordinatesToStringList[0].toInt(), coordinatesToStringList[1].toInt())

            lineList[coordinatesFrom] = coordinatesTo
        }
    }

    return lineList
}

fun readFileForBingoBoards(fileName: String): BingoSubsystem {
    var isFirstLine = true
    var bingoBoard = BingoBoard()
    val bingoSubsystem = BingoSubsystem()

    File(fileName).useLines { lines
        ->
        lines.forEach { line ->
            if (isFirstLine) {
                bingoSubsystem.bingoNumbers = line.split(',').map { number -> number.toInt() }
                isFirstLine = false
            } else if (line.isNotBlank()) {
                bingoBoard.board.add(
                    line.trim().replace("\\s+".toRegex(), " ").split(' ').map { number -> number.toInt() }.toMutableList())
                if (bingoBoard.board.isNotEmpty() && bingoBoard.board.size == 5) {
                    bingoSubsystem.bingoBoards.add(bingoBoard)
                    bingoBoard = BingoBoard()
                }
            }
        }
    }

    return bingoSubsystem
}

fun readFileForMapStringInt(fileName: String): List<Map<String, Int>> {
    val lineList = mutableListOf<Map<String, Int>>()

    File(fileName).useLines { lines
        ->
        lines.forEach {
            val splitLine = it.split(' ')
            val map = mapOf(splitLine[0] to splitLine[1].toInt())
            lineList.add(map)
        }
    }

    return lineList
}