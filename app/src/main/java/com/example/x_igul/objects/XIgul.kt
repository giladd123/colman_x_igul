package com.example.x_igul.objects

const val X = "X"
const val O = "O"
const val EMPTY= " "

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

}