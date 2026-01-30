// Oliver Giordano 
// 01/24/2026
// CSE 123 
// C1: AbstractStrategyGame
// TA: Ishita Mundra

import java.util.*;

// Represents a game of connect four, with functions to manage placing peices, game winning
// taking turns and game state.
public class ConnectFour extends AbstractStrategyGame {
    public static final char P1Token = 'Y';
    public static final char P2Token = 'R';
    public static final char PLAYER_1 = 1;
    public static final char PLAYER_2 = 2;
    public static final int GAME_NOT_OVER = -1;
    public static final int TIE = 0;

    private char[][] board;
    private boolean isYTurn;
    private int lastX;
    private int lastY;

    // Behavior:
    //  - Initializes a empty 6x7 connect for board and sets the game to player 1's turn
    // Parameters:
    //  - None
    // Returns:
    //  - None
    // Exceptions:
    //  - None
    public ConnectFour() {
        board = new char[6][7];
        isYTurn = true;
    }
    // Behavior:
    //  - Returns the player who plays next
    // Parameters:
    //  - None
    // Returns:
    //  - None
    // Exceptions:
    //  - None
    public int getNextPlayer(){
        if(isYTurn){
            return PLAYER_1;
        } else {
            return PLAYER_2;
        }
    }
    // Behavior:
    //  - Asks the user which column they would like to place in
    // Parameters:
    //  - A Scanner object used to read input
    // Returns:
    //  - A string value of which column was chosen
    // Exceptions:
    //  - IllegalArgumentException if the function argument is null
    public String getMove(Scanner input) {
        if(input == null){
            throw new IllegalArgumentException();
        }
        System.out.print("Column? ");
        return Integer.toString(input.nextInt());
    }

    // Behavior:
    //  - Places a token in the lowest possible position of a specified column
    // Parameters:
    //  - A string value of the chosen column
    // Returns:
    //  - None
    // Exceptions:
    //  - IllegalArgumentException if the column is full, or out of board range
    public void makeMove(String input){
        int cell = Integer.parseInt(input)-1;
        if(cell < 0 || cell >= board[0].length){
            throw new IllegalArgumentException("Out of column range");
        }
        if(board[0][cell] != 0){
            throw new IllegalArgumentException("Column full");
        }
        char currToken;
        if(isYTurn) {
            currToken = P1Token;
        } else {
            currToken = P2Token;
        }
        for(int i = 0; i < board.length; i++){
            if(board[i][cell] != 0){
                board[i-1][cell] = currToken;
                lastY = i-1;
                lastX = cell;
                i = board[0].length+1;
            } else if(i+1 >= board.length){
                board[i][cell] = currToken;
                lastY = i;
                lastX = cell;
                i = board[0].length+1;
            }
        }
        isYTurn = !isYTurn;
    }

    // Behavior:
    //  - Determines whether the game has been won, tied or still ongoing, winning counts as four
    //    in row in any oriantation, tieing occurs when the game board is full, and ongoing occurs
    //    when neither of the other two conditions are met
    // Parameters:
    //  - None
    // Returns:
    //  - PLAYER_1 if Player 1 wins
    //  - PLAYER_2 if Player 2 wins
    //  - TIE if the game is tied
    //  - GAME_NOT_OVER if the game is not over
    // Exceptions:
    //  - None
    public int getWinner() {
        //check horizantal winner
        int connectionCheck = getRowWinner(lastY);
        if(connectionCheck != -1){
            return connectionCheck;
        }
        //check vertical winner
        connectionCheck = getColumnWinner(lastX);
        if(connectionCheck != -1){
            return connectionCheck;
        }
        //check diagonalRight winner
        int tmpLastY = lastY;
        int tmpLastX = lastX;
        while(tmpLastY > 0 && tmpLastX > 0){
            tmpLastY--;
            tmpLastX--;
        }
        connectionCheck = getDiagonalRightWinner(tmpLastX, tmpLastY);
        if(connectionCheck != -1){
            return connectionCheck;
        }

        tmpLastY = lastY;
        tmpLastX = lastX;
        while(tmpLastY > 0 && tmpLastX < board[0].length-1){
            tmpLastY--;
            tmpLastX++;
        }
        connectionCheck = getDiagonalLeftWinner(tmpLastX, tmpLastY);
        if(connectionCheck != -1){
            return connectionCheck;
        }
        //check if full
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == 0){
                    return -1;
                }
            }
        }
        return  TIE;
    }

    // Behavior:
    //  - Determines whether a win condition has been met on a given row
    // Parameters:
    //  - The row to check for the win condition
    // Returns:
    //  - PLAYER_1 if Player 1 wins
    //  - PLAYER_2 if Player 2 wins
    //  - GAME_NOT_OVER if the game is not over
    // Exceptions:
    //  - None
    private int getRowWinner(int row){
        int inARowCount = 0;
        for(int i = 0; i < board[0].length; i++){
            if(board[row][i] == 'Y' && inARowCount >= 0){
                inARowCount++;
            } else if(board[row][i] == 'R' && inARowCount <= 0){
                inARowCount--;
            } else if(board[row][i] == 'Y' && inARowCount < 0){
                inARowCount = 1;
            } else if(board[row][i] == 'R' && inARowCount > 0){
                inARowCount = -1;
            } else {
                inARowCount = 0;
            }
            if(inARowCount >= 4){
                return PLAYER_1;
            } else if(inARowCount <= -4){
                return PLAYER_2;
            }
        }
        return GAME_NOT_OVER;
    }
    // Behavior:
    //  - Determines whether a win condition has been met on a given column
    // Parameters:
    //  - The column to check for the win condition
    // Returns:
    //  - PLAYER_1 if Player 1 wins
    //  - PLAYER_2 if Player 2 wins
    //  - GAME_NOT_OVER if the game is not over
    // Exceptions:
    //  - None
    private int getColumnWinner(int col){
        int inAColCount = 0;
        for(int i = 0; i < board.length; i++){
            if(board[i][col] == 'Y' && inAColCount >= 0){
                inAColCount++;
            } else if(board[i][col] == 'R' && inAColCount <= 0){
                inAColCount--;
            } else if(board[i][col] == 'Y' && inAColCount < 0){
                inAColCount = 1;
            } else if(board[i][col] == 'R' && inAColCount > 0){
                inAColCount = -1;
            } else {
                inAColCount = 0;
            }
            if(inAColCount >= 4){
                return PLAYER_1;
            } else if(inAColCount <= -4){
                return PLAYER_2;
            }
        }
        return GAME_NOT_OVER;
    }

    // Behavior:
    //  - Determines whether a win condition has been met in the diagonal right direction of
    //    given peice
    // Parameters:
    //  - The position of the last placed token
    // Returns:
    //  - PLAYER_1 if Player 1 wins
    //  - PLAYER_2 if Player 2 wins
    //  - GAME_NOT_OVER if the game is not over
    // Exceptions:
    //  - None
    private int getDiagonalRightWinner(int x, int y){
        int inARowCount= 0;
        while(x < board[0].length && y < board.length){
            if(board[y][x] == 'Y' && inARowCount >= 0){
                inARowCount++;
            } else if(board[y][x] == 'R' && inARowCount <= 0){
                inARowCount--;
            } else if(board[y][x] == 'Y' && inARowCount < 0){
                inARowCount = 1;
            } else if(board[y][x] == 'R' && inARowCount > 0){
                inARowCount = -1;
            } else {
                inARowCount = 0;
            }
            if(inARowCount >= 4){
                return PLAYER_1;
            } else if (inARowCount <= -4){
                return PLAYER_2;
            }
            x++;
            y++;
        }
        return GAME_NOT_OVER;
    }

    // Behavior:
    //  - Determines whether a win condition has been met in the diagonal left direction of
    //    given peice
    // Parameters:
    //  - The position of the last placed token
    // Returns:
    //  - PLAYER_1 if Player 1 wins
    //  - PLAYER_2 if Player 2 wins
    //  - GAME_NOT_OVER if the game is not over
    // Exceptions:
    //  - None
    private int getDiagonalLeftWinner(int x, int y){  
        int inARowCount= 0;
        while(x >= 0 && y < board.length){
            if(board[y][x] == 'Y' && inARowCount >= 0){
                inARowCount++;
            } else if(board[y][x] == 'R' && inARowCount <= 0){
                inARowCount--;
            } else if(board[y][x] == 'Y' && inARowCount < 0){
                inARowCount = 1;
            } else if(board[y][x] == 'R' && inARowCount > 0){
                inARowCount = -1;
            } else {
                inARowCount = 0;
            }
            if(inARowCount >= 4){
                return PLAYER_1;
            } else if (inARowCount <= -4){
                return PLAYER_2;
            }
            x--;
            y++;
        }
        return GAME_NOT_OVER;
    }

    // Behavior:
    //  - Returns the games instructions of the game
    // Parameters:
    //  - None
    // Returns:
    //  - A string contianing the games instructions
    // Exceptions:
    //  - None
    public String instructions(){
          return "Players take turns dropping red and yellow tokens into a columns 1–7. "
               + "Only 6 peices can be placed per column. "
               + "The token falls to the lowest open space. "
               + "The player that connects four in a row in any oriantation wins";
    }    

    // Behavior:
    //  - Returns a string copy of the game board
    // Parameters:
    //  - None
    // Returns:
    //  - Returns a String of the game board
    // Exceptions:
    //  - None
    public String toString(){
        String output = "";
        for(int i = 0; i < board.length; i++){
            //output += "|";
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == 0){
                    output += "|   ";
                } else {
                    output += "| " + board[i][j] + " ";
                }
            }
            output += "|\n";
        }
        return output;
    }
}
