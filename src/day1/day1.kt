package day1

fun calculateIncreases(linesFromFile: List<Int>): Int {
    var countOfIncreases = 0

    for (i in linesFromFile.indices) {
        if (i > 0 && linesFromFile[i - 1] < linesFromFile[i]) {
            countOfIncreases++
        }
    }
    return countOfIncreases
}

fun calculateSlidingWindowIncreases(linesFromFile: List<Int>): Int {
    var countOfIncreases = 0

    var sumFromFirstSlidingWindow = 0
    var sumFromSecondSlidingWindow = 0

    for (i in linesFromFile.indices) {
        if (i < linesFromFile.size - 3) {
            sumFromFirstSlidingWindow =
                linesFromFile[i] + linesFromFile[i + 1] + linesFromFile[i + 2]
            sumFromSecondSlidingWindow =
                linesFromFile[i + 1] + linesFromFile[i + 2] + linesFromFile[i + 3]

            if (sumFromFirstSlidingWindow < sumFromSecondSlidingWindow) {
                countOfIncreases++
            }
        }
    }
    return countOfIncreases
}

fun main() {
    val linesFromFileFirstStar = file.readFileForInteger("src/day1/day1_first_star.txt")

    val countOfIncreasesFirstStar = calculateIncreases(linesFromFileFirstStar)

    println("Result for first star $countOfIncreasesFirstStar")

    val linesFromFileSecondStar = file.readFileForInteger("src/day1/day1_second_star.txt")

    val countOfIncreasesSecondStar = calculateSlidingWindowIncreases(linesFromFileSecondStar)

    println("Result for second star $countOfIncreasesSecondStar")
}
