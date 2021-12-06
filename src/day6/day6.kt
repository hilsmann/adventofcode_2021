package day6

import java.math.BigInteger

fun calculateLanternFishPopulation(currentPopulationOfLanternFish: MutableList<Int>, days: Int): BigInteger {
    var numberOfLanternFish = BigInteger.ZERO
    var currentPopulation = mutableMapOf<Int, BigInteger>()

    for (pop in currentPopulationOfLanternFish) {
        if (!currentPopulation.containsKey(pop)) {
            currentPopulation[pop] = BigInteger.ONE
        } else {
            currentPopulation[pop] = currentPopulation[pop]!!.plus(BigInteger.ONE)
        }
    }

    for (i in 0 until days) {
        val nextPopulation = mutableMapOf<Int, BigInteger>()
        var newSixes = BigInteger.ZERO

        for (lanternFishNumberOfDaysToReproduce in currentPopulation) {
            if (lanternFishNumberOfDaysToReproduce.key == 0) {
                newSixes += lanternFishNumberOfDaysToReproduce.value
                nextPopulation[8] = lanternFishNumberOfDaysToReproduce.value
            } else {
                if (lanternFishNumberOfDaysToReproduce.key - 1 == 6) {
                    newSixes += lanternFishNumberOfDaysToReproduce.value
                } else {
                    nextPopulation[lanternFishNumberOfDaysToReproduce.key - 1] =
                        lanternFishNumberOfDaysToReproduce.value
                }
            }
        }
        nextPopulation[6] = newSixes

        currentPopulation = nextPopulation
    }

    for (numberOfLanternFishPerStage in currentPopulation) {
        numberOfLanternFish += numberOfLanternFishPerStage.value
    }

    return numberOfLanternFish
}

fun arraySolution(currentPopulationOfLanternFish: MutableList<Int>, days: Int): Int {
    for (i in 0 until days) {
        val nextLanternFish = mutableListOf<Int>()
        for (j in currentPopulationOfLanternFish.indices) {
            if (currentPopulationOfLanternFish[j] == 0) {
                nextLanternFish.add(8)
                currentPopulationOfLanternFish[j] = 6
            } else {
                currentPopulationOfLanternFish[j] = currentPopulationOfLanternFish[j].minus(1)
            }
        }
        currentPopulationOfLanternFish.addAll(nextLanternFish)
    }

    return currentPopulationOfLanternFish.size
}

fun main(args: Array<String>) {
    val linesFromFileFirstStar = file.readFileForIntCommaSeperated("src/day6/day6_first_star.txt")

    val currentPopulationOfLanternFishFirstStar =
        calculateLanternFishPopulation(linesFromFileFirstStar.toMutableList(), 80)

    println("Result for first star $currentPopulationOfLanternFishFirstStar") // 358214

    val linesFromFileSecondStar = file.readFileForIntCommaSeperated("src/day6/day6_second_star.txt")

    val currentPopulationOfLanternFishSecondStar =
        calculateLanternFishPopulation(linesFromFileSecondStar.toMutableList(), 256)

    println("Result for second star $currentPopulationOfLanternFishSecondStar") // 1622533344325
}
