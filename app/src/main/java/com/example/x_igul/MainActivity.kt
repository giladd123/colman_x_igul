package com.example.x_igul

import android.os.Bundle
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.x_igul.objects.Result
import com.example.x_igul.objects.XIgul
import com.example.x_igul.ui.theme.X_igulTheme

class MainActivity : ComponentActivity() {
    private var board = Array(3) { Array(3) {""} }
    private var turn = "X"
    //private var winner = ""
    private lateinit var currentPlayerTextView: TextView
    private var isGameFinished = false
    private lateinit var buttons: Array<Array<Button>>
    private lateinit var game: XIgul

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        game = XIgul()
        buttons = Array(3) { row ->
            Array(3) { column ->
                Button(this).apply {
                    textSize = 26f
                    setOnClickListener { handleMove(row, column) }
                }
            }
        }
        val gridLayout = findViewById<GridLayout>(R.id.activity_main_grid_layout)
        gridLayout.rowCount = 3
        gridLayout.columnCount = 3

        for (row in 0..2) {
            for (column in 0..2) {
                val button = buttons[row][column]

                val params = GridLayout.LayoutParams().apply {
                    width = 0
                    height = 0
                    rowSpec = GridLayout.spec(row, 1f)
                    columnSpec = GridLayout.spec(column, 1f)
                }

                button.layoutParams = params
                gridLayout.addView(button)
            }
        }
        findViewById<Button>(R.id.activity_main_reset_button).setOnClickListener {
            game.reset()
            updateBoard()
        }

        updateBoard()
    }
    private fun handleMove(row: Int, column: Int) {
        if (game.validateTurn(row, column)) {
            game.move(row, column)
            updateBoard()
            val winner = game.checkForWinner()
            if (winner != null) {
                val message = "The Winner is " + when (winner) {
                    Result.X -> "Player X!!!"
                    Result.O -> "Player O!!!"
                    Result.DRAW -> "Nobody - It's a Drow"
                }
                Toast.makeText(this,message, Toast.LENGTH_LONG).show()
                game.reset()
            }
        }
    }
    private fun updateBoard() {
        val board = game.board
        for (row in board.indices) {
            for (column in board[row].indices) {
                buttons[row][column].text = board[row][column].toString()
                buttons[row][column].isEnabled = board[row][column] == ' '
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    X_igulTheme {
        Greeting("Android")
    }
}