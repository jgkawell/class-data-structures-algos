//Program:      QueenGameSolver.java
//Course:       COSC210
//Description:  A program to play a simple game which positions N number of Xs on 
//              an NxN board for any positive value of N such that no Xs are in the 
//              same row, the same column, or along the same diagonal. The program  
//              prompts the user for the number of Xs, finds the solution (if it 
//              exists), and shows the result. It can also show the placements of 
//              each individual trial placements of the Xs on the user's request.
//              Also included in the result is the number of boards evaluated by 
//              the algorithm.              
//Author:       Jack Kawell
//Revised:      4/18/16
//Language:     Java
//IDE:          NetBeans 8.0.2
//*******************************************************************************
//*******************************************************************************
//Class:        QueenGameSolver
//Description:  This class contains all the necessary methods to complete the game.
//              It also calls KeyboardInputClass to get input from the user.

public class QueenGameSolver {

    private static char[][] board;
    private static int sideLength; //the number of X's the user wants to find a board for which = board.length
    private static KeyboardInputClass keyboardInput;
    private static char showBoards;
    private static int numberOfBoards;
    private static boolean primeNumber;

//*******************************************************************************
//Method:       Main
//Description:  Main method that calls all other relavant methods. Also prints 
//              the output for the user and prompts the user for the number of X's
//              and whether they want to quit or not.
//Parameters:   Default
//Returns:      None
//Calls:        KeyboardInputClass
//              buildBoard
//              keyboardInput.getCharacter  
//              placeX
//              printBoard
//Globals:      keyboardInput
//              showBoards
//              keyboardInput
//              numberOfBoards

    public static void main(String[] args) {
        keyboardInput = new KeyboardInputClass();
        char quit = 'N';

        while (quit == 'N') {
            buildBoard();
            showBoards = keyboardInput.getCharacter(true, 'N', "YN", 1,
                    "Would you like to show each board? (Y/N; default = N):");

            System.out.println("Evaluating...");
            primeNumber = isPrime();//check to see if prime

            if (placeX(0, 0)) {
                System.out.println("This is a board that fits the requirements:");
                printBoard();
            } else {
                System.out.println("No possible board that fits the requirements.");
            }

            System.out.println("The number of boards evaluated was: " + numberOfBoards);
            numberOfBoards = 0;
            System.out.println("");

            quit = keyboardInput.getCharacter(true, 'Y', "YN", 1,
                    "Would you like to quit? (Y/N; default = Y):");
        }//end while loop
    }
//*******************************************************************************
//Method:       buildBoard
//Description:  Creates a 2D char array of size sideLength x sidelength filled with '*'.              
//Parameters:   None
//Returns:      Nothing
//Calls:        keyboardInput.getInteger
//Globals:      sideLength
//              keyboardInput
//              board
//              Integer.MAX_VALUE
//              board.length

    private static void buildBoard() {
        String prompt = "Enter an integer N to find a solution for puting N number\n"
                + "of X's on an NxN board where no X is on the same row, column or\n"
                + "diagonal as another X (default N = 4):";

        sideLength = keyboardInput.getInteger(true, 4, 0, Integer.MAX_VALUE, prompt);
        board = new char[sideLength][sideLength];

        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board.length; column++) {
                board[row][column] = '*';
            }//end inner for loop
        }//end outer for loop
    }
//*******************************************************************************
//Method:       placeX
//Description:  A recursive method which calls checkValidity to see if the current
//              position is an allowable position for an X. If true, it adds an X
//              at the current position then makes a recursive call to the next row
//              of the board. If false, it returns false and the higher recursive call
//              shifts over a column and tries again.
//Parameters:   row        --   the current row that is being checked
//              column     --   the current column being checked (always is zero)
//Returns:      boolean
//Calls:        printBoard
//              keyboardInput.getKeyboardInput
//              checkValidity 
//              placeX
//Globals:      showBoards
//              keyboardInput
//              board.length
//              board

    private static boolean placeX(int row, int column) {
        int passedColumn = column;
        int loopLimit = board.length;

        if (row < board.length) {
            for (; column < loopLimit; column++) {
                boolean validPosition = checkValidity(row, column);
                board[row][column] = 'X';

                if (showBoards == 'Y') {
                    printBoard();
                    System.out.println("The number of boards evaluated is: " + numberOfBoards);
                    keyboardInput.getKeyboardInput("");
                }//end if

                if (validPosition) {
                    int startingColumnForNextRow;
                    //check to see if prime, if true iterate over 2 columns to make it O(N).
                    //Otherwise iterate over 3 for better efficiency.
                    if (primeNumber) {
                        startingColumnForNextRow = (column + 2) % (board.length);
                    } else {
                        startingColumnForNextRow = (column + 3) % (board.length);
                    }//end of inner if/else

                    if (placeX(row + 1, startingColumnForNextRow)) {
                        return true;
                    } else {
                        board[row][column] = '*';
                    }//end of inner if/else
                } else {
                    board[row][column] = '*';
                }//end of outer if/else

                if (column == board.length - 1) {
                    column = -1;
                    loopLimit = passedColumn;
                }
            }//end for loop
            return false;
        }//end outer if
        return true;
    }
//*******************************************************************************
//Method:       printBoard
//Description:  Prints the current board layout.              
//Parameters:   None
//Returns:      Nothing
//Calls:        Nothing
//Globals:      board.length
//              board

    private static void printBoard() {
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board.length; column++) {
                System.out.print(board[row][column] + " ");
            }//end inner for loop
            System.out.println("");
        }//end outer for loop
    }
//*******************************************************************************
//Method:       checkValidity
//Description:  Checks the current position to see if it is a valid position to 
//              place an X. If an X is on the vertical above the current position
//              or on a upward diagonal, it returns false. Otherwise, it returns true.
//Parameters:   row     --      the row to be checked
//              column  --      the column to be checked
//Returns:      boolean
//Calls:        Nothing                     
//Globals:      numberOfBoards++;
//              board
//              board.length

    private static boolean checkValidity(int row, int column) {
        numberOfBoards++;
        int columnLeft = column;
        int columnRight = column;

        for (int rowUp = row - 1; rowUp >= 0; rowUp--) {
            if (board[rowUp][column] == 'X') {
                return false;
            }//end if
            columnLeft--;
            if (columnLeft >= 0) {
                if (board[rowUp][columnLeft] == 'X') {
                    return false;
                }//end inner if
            }//end outer if
            columnRight++;
            if (columnRight < board.length) {
                if (board[rowUp][columnRight] == 'X') {
                    return false;
                }//emd inner if
            }//end outer if
        }//end of for loop
        return true;
    }
//*******************************************************************************
//Method:       isPrime
//Description:  Checks to see if the number entered by the user is prime. If it is,
//              then the program runs at O(N).
//Parameters:   None
//Returns:      boolean
//Calls:        Nothing
//Globals:      sideLength

    private static boolean isPrime() {

        if (sideLength % 2 == 0) {
            return false;
        }

        for (int i = 3; i < sideLength; i += 2) {
            if (sideLength % i == 0) {
                return false;
            }
        }

        return true;
    }
}//end class
//*******************************************************************************
//*******************************************************************************
