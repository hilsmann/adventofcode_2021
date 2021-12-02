package day1

import java.io.File

fun readFile(fileName: String): List<String> {
    val lineList = mutableListOf<String>()

    File(fileName).useLines { lines -> lines.forEach { lineList.add(it) } }

    return lineList
}

fun calculateIncreases(linesFromFile: List<String>): Int {
    var countOfIncreases = 0

    for (i in linesFromFile.indices) {
        if (i > 0 && linesFromFile[i - 1].toInt() < linesFromFile[i].toInt()) {
            countOfIncreases++
        }
    }
    return countOfIncreases
}

fun calculateSlidingWindowIncreases(linesFromFile: List<String>): Int {
    var countOfIncreases = 0

    var sumFromFirstSlidingWindow = 0
    var sumFromSecondSlidingWindow = 0

    for (i in linesFromFile.indices) {
        if (i < linesFromFile.size - 3) {
            sumFromFirstSlidingWindow =
                linesFromFile[i].toInt() + linesFromFile[i + 1].toInt() + linesFromFile[i + 2].toInt()
            sumFromSecondSlidingWindow =
                linesFromFile[i + 1].toInt() + linesFromFile[i + 2].toInt() + linesFromFile[i + 3].toInt()

            if (sumFromFirstSlidingWindow < sumFromSecondSlidingWindow) {
                countOfIncreases++
            }
        }
    }
    return countOfIncreases
}

fun main(args: Array<String>) {
    val linesFromFileFirstStar = readFile("src/day1/day1_first_star.txt")

    val countOfIncreasesFirstStar = calculateIncreases(linesFromFileFirstStar)

    println("Result for first star $countOfIncreasesFirstStar")

    val linesFromFileSecondStar = readFile("src/day1/day1_second_star.txt")

    val countOfIncreasesSecondStar = calculateSlidingWindowIncreases(linesFromFileSecondStar)

    println("Result for second star $countOfIncreasesSecondStar")
}
