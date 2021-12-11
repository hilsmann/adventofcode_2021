package day11

fun calculateAmountOfFlashesFirstStar(dumboOctopusesEnergy: List<MutableList<Int>>, steps: Int): Int {

    var amountOfFlashes = 0

    println(dumboOctopusesEnergy)

    for (step in 0 until steps) {

        incrementAllOctopusEnergyByOne(dumboOctopusesEnergy)

        while (hasNumbersGreaterThanNine(dumboOctopusesEnergy)) {
            for (i in dumboOctopusesEnergy.indices) {
                for (j in dumboOctopusesEnergy[0].indices) {
                    if (dumboOctopusesEnergy[i][j] == 0) {
                        continue
                    } else if (dumboOctopusesEnergy[i][j] > 9) {
                        amountOfFlashes++
                        dumboOctopusesEnergy[i][j] = 0
                        incrementAdjacentAfterFlash(dumboOctopusesEnergy, i, j)
                    }
                }
            }
        }
    }

    println(dumboOctopusesEnergy)

    return amountOfFlashes
}

fun calculateStepWhenAllDumboOctopusesFlashesAtSameTimeSecondStar(dumboOctopusesEnergy: List<MutableList<Int>>): Int {

    var amountOfFlashes = 0
    var currentStep = 1
    var stepWhenAllDumboOctopusesAreFlashingAtSameTime = 0

    println(dumboOctopusesEnergy)

    while (stepWhenAllDumboOctopusesAreFlashingAtSameTime == 0) {

        incrementAllOctopusEnergyByOne(dumboOctopusesEnergy)

        while (hasNumbersGreaterThanNine(dumboOctopusesEnergy)) {
            for (i in dumboOctopusesEnergy.indices) {
                for (j in dumboOctopusesEnergy[0].indices) {
                    if (dumboOctopusesEnergy[i][j] == 0) {
                        continue
                    } else if (dumboOctopusesEnergy[i][j] > 9) {
                        amountOfFlashes++
                        dumboOctopusesEnergy[i][j] = 0
                        incrementAdjacentAfterFlash(dumboOctopusesEnergy, i, j)
                    }
                }
            }
        }

        if (allNumbersAreNull(dumboOctopusesEnergy)) {
            stepWhenAllDumboOctopusesAreFlashingAtSameTime = currentStep
        } else {
            currentStep++
        }

    }

    println(dumboOctopusesEnergy)

    return stepWhenAllDumboOctopusesAreFlashingAtSameTime
}

private fun allNumbersAreNull(dumboOctopusesEnergy: List<MutableList<Int>>): Boolean {
    var result = true

    for (line in dumboOctopusesEnergy) {
        result = result && line.stream().allMatch { it == 0 }
    }

    return result
}

private fun hasNumbersGreaterThanNine(dumboOctopusesEnergy: List<MutableList<Int>>): Boolean {
    var result = false

    for (line in dumboOctopusesEnergy) {
        if (!result) {
            result = line.stream().anyMatch { it > 9 }
        }
    }

    return result
}

private fun incrementAdjacentAfterFlash(dumboOctopusesEnergy: List<MutableList<Int>>, row: Int, column: Int) {

    // Row Above
    if (row - 1 >= 0) {
        if (column - 1 >= 0) {
            dumboOctopusesEnergy[row - 1][column - 1] =
                incrementByOneIfNotNull(dumboOctopusesEnergy[row - 1][column - 1])
        }

        dumboOctopusesEnergy[row - 1][column] = incrementByOneIfNotNull(dumboOctopusesEnergy[row - 1][column])

        if (column + 1 < dumboOctopusesEnergy[column].size) {
            dumboOctopusesEnergy[row - 1][column + 1] =
                incrementByOneIfNotNull(dumboOctopusesEnergy[row - 1][column + 1])
        }
    }

    // Same Row
    if (column - 1 >= 0) {
        dumboOctopusesEnergy[row][column - 1] = incrementByOneIfNotNull(dumboOctopusesEnergy[row][column - 1])
    }

    if (column + 1 < dumboOctopusesEnergy[column].size) {
        dumboOctopusesEnergy[row][column + 1] = incrementByOneIfNotNull(dumboOctopusesEnergy[row][column + 1])
    }

    // Row Beneath
    if (row + 1 < dumboOctopusesEnergy[row].size) {
        if (column - 1 >= 0) {
            dumboOctopusesEnergy[row + 1][column - 1] =
                incrementByOneIfNotNull(dumboOctopusesEnergy[row + 1][column - 1])
        }

        dumboOctopusesEnergy[row + 1][column] = incrementByOneIfNotNull(dumboOctopusesEnergy[row + 1][column])

        if (column + 1 < dumboOctopusesEnergy[column].size) {
            dumboOctopusesEnergy[row + 1][column + 1] =
                incrementByOneIfNotNull(dumboOctopusesEnergy[row + 1][column + 1])
        }
    }
}

private fun incrementByOneIfNotNull(number: Int): Int {
    return if (number == 0) 0 else number + 1
}

private fun incrementAllOctopusEnergyByOne(dumboOctopusesEnergy: List<MutableList<Int>>) {
    for (i in dumboOctopusesEnergy.indices) {
        for (j in dumboOctopusesEnergy[0].indices) {
            dumboOctopusesEnergy[i][j] += 1
        }
    }
}

fun main(args: Array<String>) {
    val linesFromFileFirstStar = file.readFileForIntegerInLine("src/day11/day11_first_star.txt")

    val amountOfFlashesFirstStar = calculateAmountOfFlashesFirstStar(linesFromFileFirstStar.toMutableList(), 100)

    println("Result for first star $amountOfFlashesFirstStar") // 1642

    val linesFromFileSecondStar = file.readFileForIntegerInLine("src/day11/day11_second_star.txt")

    val stepWhenAllDumboOctopusesFlashesAtSameTimeSecondStar =
        calculateStepWhenAllDumboOctopusesFlashesAtSameTimeSecondStar(linesFromFileSecondStar.toMutableList())

    println("Result for second star $stepWhenAllDumboOctopusesFlashesAtSameTimeSecondStar") // 320 TooLow
}
