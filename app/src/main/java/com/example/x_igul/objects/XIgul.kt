package com.example.x_igul.objects

const val X = 'X'
const val O = 'O'
const val EMPTY= ' '

enum class Result {
    X, O, DRAW
}
class XIgul {
    val board = Array(3) { Array(3) {EMPTY} }
    private var currPlayer = X

    fun reset() {
        for (row in board.indices) {
            for (col in board[row].indices) {
                board[row][col] = EMPTY
            }
        }
        currPlayer = X
    }

    fun move(row: Int, col: Int) {
        if (board[row][col] == EMPTY) {
            board[row][col] = currPlayer
        }
        currPlayer = if (currPlayer == X) O else X
    }

    fun validateTurn(row: Int,column: Int) : Boolean {
        return !(row !in board.indices || column !in board.indices || board[row][column] != EMPTY)
    }

    fun returnResult(winner: Char) : Result {
        return if(winner == X) {
            Result.X
        } else {
            Result.O
        }
    }

    fun checkForWinner (): Result? {
        for (i in board.indices) {
            if (board[i][0] != EMPTY && board[i][0] == board[i][1] && board[i][0] == board[i][2]) {
                return returnResult(board[i][0])
            }
            if (board[0][i] != EMPTY && board[0][i] == board[1][i] && board[0][i] == board[2][i]) {
                return returnResult(board[0][i])
            }
        }

        if (board[0][0] != EMPTY && board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
            return returnResult(board[0][0])
        }
        if (board[0][2] != EMPTY && board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
            return returnResult(board[0][2])
        }

        if (board.all { row -> row.all { cell -> cell != EMPTY } }) {
            return Result.DRAW
        }

        return null

    }
}