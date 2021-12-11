package day3

fun calculatePowerConsumption(linesFromFile: List<String>): Int {
    var gamma = ""
    var epsilon = ""

    for (i in 0 until linesFromFile[0].length) {
        var numberOfOnes = 0
        var numberOfZeros = 0

        for (line in linesFromFile) {
            if (line[i].digitToInt() == 1) {
                numberOfOnes++
            } else if (line[i].digitToInt() == 0) {
                numberOfZeros++
            }
        }

        if (numberOfOnes > numberOfZeros) {
            gamma = gamma.plus(1)
            epsilon = epsilon.plus(0)
        } else {
            gamma = gamma.plus(0)
            epsilon = epsilon.plus(1)
        }
    }

    return Integer.parseInt(gamma, 2) * Integer.parseInt(epsilon, 2)
}

fun calculateLifeSupportRating(linesFromFile: List<String>): Int {
    var co2ScrubberRating = ""
    var oxygenGeneratorRating = ""
    var linesFromFileMutableListCO2 = linesFromFile.toMutableList()
    var linesFromFileMutableListOxygen = linesFromFile.toMutableList()

    for (i in 0 until linesFromFileMutableListOxygen[0].length) {
        var numberOfOnes = 0
        var numberOfZeros = 0

        for (line in linesFromFileMutableListOxygen) {
            if (line[i].digitToInt() == 1) {
                numberOfOnes++
            } else if (line[i].digitToInt() == 0) {
                numberOfZeros++
            }
        }

        if (numberOfOnes > numberOfZeros) {
            linesFromFileMutableListOxygen.removeIf { it[i].digitToInt() == 0 }
        } else if (numberOfOnes < numberOfZeros) {
            linesFromFileMutableListOxygen.removeIf { it[i].digitToInt() == 1 }
        } else {
            linesFromFileMutableListOxygen.removeIf { it[i].digitToInt() == 0 }
        }

        if (linesFromFileMutableListOxygen.size == 1) {
            oxygenGeneratorRating = linesFromFileMutableListOxygen[0]
        }
    }

    for (i in 0 until linesFromFileMutableListCO2[0].length) {
        var numberOfOnes = 0
        var numberOfZeros = 0

        for (line in linesFromFileMutableListCO2) {
            if (line[i].digitToInt() == 1) {
                numberOfOnes++
            } else if (line[i].digitToInt() == 0) {
                numberOfZeros++
            }
        }

        if (numberOfOnes < numberOfZeros) {
            linesFromFileMutableListCO2.removeIf { it[i].digitToInt() == 0 }
        } else if (numberOfOnes > numberOfZeros) {
            linesFromFileMutableListCO2.removeIf { it[i].digitToInt() == 1 }
        } else {
            linesFromFileMutableListCO2.removeIf { it[i].digitToInt() == 1 }
        }

        if (linesFromFileMutableListCO2.size == 1) {
            co2ScrubberRating = linesFromFileMutableListCO2[0]
        }
    }

    return Integer.parseInt(co2ScrubberRating, 2) * Integer.parseInt(oxygenGeneratorRating, 2)
}

fun main() {
    val linesFromFileFirstStar = file.readFileForString("src/day3/day3_first_star.txt")

    val powerConsumption = calculatePowerConsumption(linesFromFileFirstStar)

    println("Result for first star $powerConsumption")

    val linesFromFileSecondStar = file.readFileForString("src/day3/day3_second_star.txt")

    val depthMultipyHorizontalPositionSecondStar = calculateLifeSupportRating(linesFromFileSecondStar)

    println("Result for second star $depthMultipyHorizontalPositionSecondStar")
}
