import java.util.Scanner


class TicTacToe {
    //presets to be used throughout program
    private val board: Array<CharArray> = Array(3) {CharArray(3) {' '}}
    private var currentPlay: Char = 'X'
    private var gameEnd: Boolean = false

    fun play() {
        //as long as we haven't reached the end of the game, we will 'play'
        while (!gameEnd) {
            printBoard()
            println("It is $currentPlay turn") //print who's turn it is
            //player will enter the row, column they want to place their marker
            val row = getValidInput("Enter the row (0-2): ")
            val column = getValidInput("Enter the column (0-2): ")
            
            // if someone is already there, they will get an error message and it
            //  will prompt them to select a row and column again.
            if (board[row][column] != ' '){
                println("Someone is already there, try again.")
                continue
            }
            // sets player's marker to the spot specified
            board[row][column] = currentPlay

            // calls the checkWinner function to see if there has been a winner to end the game
            if (checkWinner(row, column)) {
                gameEnd = true
                printBoard()
                println("Player $currentPlay wins!")
            } 
            // calls the checkDraw function to see if all spaces have been filled without completing a win to end the game
            if (checkDraw()) {
                gameEnd = true
                printBoard()
                println("It's a draw, better luck next time!")
            }

            // Switches who the current player is
            currentPlay = if (currentPlay == 'X') 'O' else 'X'

        }
    }

    // Prints out our board with column and row numbers, as well as 
    // any player's markers that have already been placed.
    private fun printBoard() {
        println("- 0 1 2 ") //prints the top row displaying the column numbers
        for (i in 0..2) {
            print("$i ") //prints the row numbers before each row in the board
            for (j in 0..2) {
                print("${board[i][j]} ") //prints the players' markers that are in the board array
            }
            println()
        } 
        println()
    }

    // Checks to make sure that we have a valid input when asking for row and column numbers
    private fun getValidInput(prompt: String): Int {
        val scanner = Scanner (System. `in`)
        var input: Int
        while (true) {
            print(prompt)
            input = scanner.nextInt()
            if (input in 0..2) {
                break
            }
            println("Invaild input, try again.") //this would print unless the input is a 0, 1, or 2
        }
        return input
    }

    //checks to see if we have a winner. A winner is determined if three of the same marker 
    // is placed in a row/column/diagonal, this is checked at the end of every turn to 
    // determine if the current player has won. If true, it will trigger game end.
    private fun checkWinner(row: Int, column: Int): Boolean {

        //check rows
        if (board[row][0] == currentPlay && board[row][1] == currentPlay && board[row][2] == currentPlay){
            return true
        }
        //check columns
        if (board[0][column] == currentPlay && board[1][column] == currentPlay && board[2][column] == currentPlay){
            return true
        }
        //check diagonals
        if (board[0][0] == currentPlay && board[1][1] == currentPlay && board[2][2] == currentPlay){
            return true
        }
        if (board[2][0] == currentPlay && board[1][1] == currentPlay && board[0][2] == currentPlay){
            return true
        }
        // current player did not win
        return false
    }

    // checks to see if there are empty spaces on the board. If there are game continues. 
    // If all are full we return true which triggers end of game.
    private fun checkDraw(): Boolean {
        for (row in board) {
            for (cell in row) {
                if (cell == ' '){
                    return false
                }
            }
        }
        return true
    }
}

// calls our TicTacToe game to start play
fun main(){
    val game = TicTacToe()
    game.play()
}
