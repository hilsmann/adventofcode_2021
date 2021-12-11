package day7

import kotlin.math.abs

fun calculateBestCrabPositionFirstStar(currentCrabPosition: List<Int>): Int {

    val maxCrabPosition = currentCrabPosition.maxOrNull()
    val fuelUsed = mutableListOf<Int>()

    for (i in 0 until maxCrabPosition!!) {
        var fuelUsePerPosition = 0
        for (element in currentCrabPosition) {
            fuelUsePerPosition += abs(element - i)
        }
        fuelUsed.add(fuelUsePerPosition)
    }

    return fuelUsed.minOrNull()!!
}

fun calculateBestCrabPositionSecondStar(currentCrabPosition: List<Int>): Int {

    val maxCrabPosition = currentCrabPosition.maxOrNull()
    val fuelUsed = mutableListOf<Int>()

    for (i in 0 until maxCrabPosition!!) {
        var fuelUsePerPosition = 0
        for (element in currentCrabPosition) {
            fuelUsePerPosition += gaussianEmpiricalFormula(abs(element - i))
        }
        fuelUsed.add(fuelUsePerPosition)
    }

    return fuelUsed.minOrNull()!!
}

private fun gaussianEmpiricalFormula(number: Int): Int {
    return (((number * number) + number) / 2)
}

fun main() {
    val linesFromFileFirstStar = file.readFileForIntCommaSeperated("src/day7/day7_first_star.txt")

    val fuelUseOfBestPositionFirstStar = calculateBestCrabPositionFirstStar(linesFromFileFirstStar)

    println("Result for first star $fuelUseOfBestPositionFirstStar") // 356179

    val linesFromFileSecondStar = file.readFileForIntCommaSeperated("src/day7/day7_second_star.txt")

    val fuelUseOfBestPositionSecondStar = calculateBestCrabPositionSecondStar(linesFromFileSecondStar)

    println("Result for second star $fuelUseOfBestPositionSecondStar") // 1622533344325
}
