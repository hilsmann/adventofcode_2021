package day4

fun calculateFirstWinningBingoBoardScore(bingoSubsystem: BingoSubsystem): Int {
    var lastBingoNumber = 0
    var winningBingoBoard = BingoBoard()

    loop@
    for (bingoNumber in bingoSubsystem.bingoNumbers) {

        for (bingoBoards in bingoSubsystem.bingoBoards) {

            for (bingoBoardLine in bingoBoards.board) {
                for (i in 0 until bingoBoardLine.size) {
                    val bingoBoardNumber = bingoBoardLine[i]
                    if (bingoBoardNumber == bingoNumber) {
                        bingoBoardLine[i] = 0
                    }
                }
            }
        }

        for (bingoBoard in bingoSubsystem.bingoBoards) {

            for (bingoBoardLine in bingoBoard.board) {
                if (bingoBoardLine.sum() == 0) {
                    lastBingoNumber = bingoNumber
                    winningBingoBoard = bingoBoard
                    break@loop
                }
            }

            for (i in 0 until  bingoBoard.board.size) {
                if ((bingoBoard.board[0][i] + bingoBoard.board[1][i] +
                            bingoBoard.board[2][i] + bingoBoard.board[3][i] + bingoBoard.board[4][i]) == 0) {
                    lastBingoNumber = bingoNumber
                    winningBingoBoard = bingoBoard
                    break@loop
                }
            }
        }
    }

    return winningBingoBoard.board.sumOf { outerList -> outerList.sumOf { innerList -> innerList } } * lastBingoNumber
}

fun calculateLastWinningBingoBoardScore(bingoSubsystem: BingoSubsystem): Int {
    var lastBingoNumber = 0
    val winningBingoBoards = mutableListOf<BingoBoard>()

    for (bingoNumber in bingoSubsystem.bingoNumbers) {

        for (bingoBoard in bingoSubsystem.bingoBoards) {
            if (!bingoBoard.isWinningBoard) {
                for (bingoBoardLine in bingoBoard.board) {
                    for (i in 0 until bingoBoardLine.size) {
                        val bingoBoardNumber = bingoBoardLine[i]
                        if (bingoBoardNumber == bingoNumber) {
                            bingoBoardLine[i] = -1
                        }
                    }
                }
            }
        }

        for (bingoBoard in bingoSubsystem.bingoBoards) {
            if (!bingoBoard.isWinningBoard) {
                // Vertical
                for (i in 0 until  bingoBoard.board.size) {
                    if ((bingoBoard.board[0][i] + bingoBoard.board[1][i] +
                                bingoBoard.board[2][i] + bingoBoard.board[3][i] + bingoBoard.board[4][i]) == -5) {
                        lastBingoNumber = bingoNumber
                        winningBingoBoards.add(bingoBoard)
                        bingoBoard.isWinningBoard = true
                    }
                }
            }
            if (!bingoBoard.isWinningBoard) {
                // Horizontal
                for (bingoBoardLine in bingoBoard.board) {
                    if (bingoBoardLine.sum() == -5) {
                        lastBingoNumber = bingoNumber
                        winningBingoBoards.add(bingoBoard)
                        bingoBoard.isWinningBoard = true
                    }
                }
            }
        }
    }

    val winning = winningBingoBoards[winningBingoBoards.size - 1].board
    for (i in 0 until  winning.size) {
        winning[i] = winning[i].filter { it > 0 }.toMutableList()
    }

    return winning.sumOf { outerList -> outerList.sumOf { innerList -> innerList } } * lastBingoNumber // 4495
}

fun main() {
    val linesFromFileFirstStar = file.readFileForBingoBoards("src/day4/day4_first_star.txt")

    val winningBingoBoardNumberFirstStar = calculateFirstWinningBingoBoardScore(linesFromFileFirstStar)

    println("Result for first star $winningBingoBoardNumberFirstStar")

    val linesFromFileSecondStar = file.readFileForBingoBoards("src/day4/day4_second_star.txt")

    val winningBingoBoardNumberSecondStar = calculateLastWinningBingoBoardScore(linesFromFileSecondStar)

    println("Result for second star $winningBingoBoardNumberSecondStar")
}
