package day5

fun calculateOverLappingVentClouds(mapOfCoordinates: Map<Coordinates, Coordinates>): Int {

    var diagram = mutableListOf<MutableList<String>>(mutableListOf())

    var mapOfLines = mutableMapOf<Int, MutableList<Int>>()
    var numberOfOverlappingPoints = 0


    // TODO Create Diagram with Coordinates

    for (coordinates in mapOfCoordinates) {
        println(coordinates)
        if (coordinates.key.x == coordinates.value.x) {
            for (i in coordinates.key.y .. coordinates.value.y) {
                if (mapOfLines.containsKey(i)) {
                    mapOfLines.putIfAbsent(i, mutableListOf(coordinates.key.x))
                } else {
                    mapOfLines.put(i, mutableListOf(coordinates.key.x))
                }
            }
        } else if (coordinates.key.y == coordinates.value.y) {
            val line = mutableListOf<Int>() // TODO vercotr
            for (i in coordinates.key.x .. coordinates.value.x) {
                line.add(i)
            }
            if (mapOfLines.containsKey(coordinates.key.y)) {
                mapOfLines[coordinates.key.y]?.addAll(line)
            } else {
                mapOfLines.putIfAbsent(coordinates.key.y, line)
            }
        }
    }



    // TODO Count Overlapping

    return 1
}

fun main(args: Array<String>) {
    val linesFromFileFirstStar = file.readFileForCoordinates("src/day5/day5_test_first_star.txt")

    val winningBingoBoardNumberFirstStar = calculateOverLappingVentClouds(linesFromFileFirstStar)

    println("Result for first star $winningBingoBoardNumberFirstStar") // 5124

//    val linesFromFileSecondStar = file.readFileForBingoBoards("src/day4/day4_second_star.txt")

//    val winningBingoBoardNumberSecondStar = calculateLastWinningBingoBoardScore(linesFromFileSecondStar)

//    println("Result for second star $winningBingoBoardNumberSecondStar") // 19771
}
