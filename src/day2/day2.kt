package day2

fun calculateDepthAndHorizontalPositionForFirstStar(linesFromFile: List<Map<String, Int>>): Int {
    var depth = 0
    var horizontalPosition = 0

    for (line in linesFromFile) {
        val command = line.keys.first()
        val value = line.values.first()

        when (command) {
            "forward" -> horizontalPosition += value
            "down" -> depth += value
            "up" -> depth -= value
        }
    }

    return depth * horizontalPosition
}

fun calculateDepthAndHorizontalPositionForSecondStar(linesFromFile: List<Map<String, Int>>): Int {
    var aim = 0
    var depth = 0
    var horizontalPosition = 0

    for (line in linesFromFile) {
        val command = line.keys.first()
        val value = line.values.first()

        when (command) {
            "forward" -> {horizontalPosition += value; depth += aim * value}
            "down" -> aim += value
            "up" -> aim -= value
        }
    }

    return depth * horizontalPosition
}

fun main(args: Array<String>) {
    val linesFromFileFirstStar = file.readFileForMapStringInt("src/day2/day2_first_star.txt")

    val depthMultipyHorizontalPosition = calculateDepthAndHorizontalPositionForFirstStar(linesFromFileFirstStar)

    println("Result for first star $depthMultipyHorizontalPosition")

    val linesFromFileSecondStar = file.readFileForMapStringInt("src/day2/day2_second_star.txt")

    val depthMultipyHorizontalPositionSecondStar = calculateDepthAndHorizontalPositionForSecondStar(linesFromFileSecondStar)

    println("Result for second star $depthMultipyHorizontalPositionSecondStar")
}
