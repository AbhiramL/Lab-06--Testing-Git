//package com.company;
import java.util.Scanner;
public class ConnectFour {

    public static void main(String[] args)
    {
//Git hub change comment
        //init scanner and input variables
        Scanner in = new Scanner(System.in);
        int height = 0;
        int width = 0;
        boolean gameOver = false;
        boolean player1Win = false, player2Win = false, draw = false;
        int column = 0;
        int row = 0;

        //get width and height
        System.out.print("What would you like the height of the board to be? ");
        height = in.nextInt();

        System.out.print("What would you like the width of the board to be? ");
        width = in.nextInt();


        //create double array for board
        char[][] board = new char[height][width];

        initializeBoard(board);
        printBoard(board);
        System.out.print("\n");

        //tell players their tokens
        System.out.println("Player 1: x\n" + "Player 2: o");
        System.out.print("\n");

	    while(!gameOver && !draw)
        {
            //player 1 turn
            System.out.print("Player 1: Which column would you like to choose? ");
            column = in.nextInt();
            row = insertChip(board, column, 'x');

            //check for winner
            gameOver = checkIfWinner(board, column, row, 'x');

            if(gameOver)
            {
                player1Win = true;
            }

            //check for draw
            if(checkForDraw(board) == 0)
            {
                draw = true;
            }
            //print updated board
            printBoard(board);
            System.out.print("\n");
            //if player 1 didn't win last turn, and its not a draw then proceed to player two's turn
            if((!gameOver))
            {
                if((!draw))
                {
                    //player 2 turn
                    System.out.print("Player 2: Which column would you like to choose? ");
                    column = in.nextInt();
                    row = insertChip(board, column, 'o');


                    //check for winner
                    gameOver = checkIfWinner(board, column, row, 'o');

                    if(gameOver)
                    {
                        player2Win = true;
                    }

                    //check for draw
                    if(checkForDraw(board) == 0)
                    {
                        draw = true;
                    }
                    //print updated board
                    printBoard(board);
                    System.out.print("\n");
                }

            }

        }

        if(player1Win)
        {
            System.out.println("Player 1 won the game!");
        }
        else if(player2Win)
        {
            System.out.println("Player 2 won the game!");
        }
        else if(draw)
        {
            System.out.println("Draw. Nobody wins.");
        }


    }//end of main
    public static void printBoard(char[][] array)
    {
         //print each array element
        for(int i = array.length-1; i >= 0; i--)
        {
            for(int j = 0; j < array[i].length; j++)
            {
                System.out.print(array[i][j] + " ");
            }
            System.out.print("\n");
        }
    }
    public static void initializeBoard(char[][] array)
    {
        //i = row, j = column, init all indexes to - chars
        for(int i = array.length - 1; i >= 0; i--)
        {
            for(int j = array[i].length-1; j >= 0; j--)
            {
                array[i][j] = '-';
            }
        }
    }
    public static int insertChip(char[][] array, int col, char chipType)
    {
        int rowPlaced = 0;
        int index = 0;

        //check for the first empty space in column
        for(int i = 0; i < array.length; i++)
        {
            //start at the bottom of the column and check for empty spot
            if(array[i][col] == '-')
            {
                index = i;
                break;
            }
        }

        //add token
        array[index][col] = chipType;
        rowPlaced = index;

        //return row placed
        return rowPlaced;
    }
    public static boolean checkIfWinner(char[][] array, int col, int row, char chipType)
    {
        boolean horizontalWin = false;
        boolean verticalWin = false;
        int counter = 0;

        //check horizontal row for winner
        for(int column = 0; column < array[row].length; column++)
        {
            if(array[row][column] == chipType)
            {
                counter++;
                if(counter == 4)
                {
                    horizontalWin = true;
                    break;
                }
            }
            else
            {
                counter = 0;
            }
        }
        counter = 0;
        //check vertical row for winner
        for(int vertRow = 0; vertRow < array.length; vertRow++)
        {
            if(array[vertRow][col] == chipType)
            {
                counter++;
                if(counter == 4)
                {
                    verticalWin = true;
                    break;
                }
            }
            else
            {
                counter = 0;
            }
        }

        if(horizontalWin || verticalWin)
        {
            return true;
        }
        else
        {
            return false;
        }

    }
    public static int checkForDraw(char[][] array)
    {
        int emptySpace = 0;
        //i = row, j = column, check all indexes for - chars
        for(int i = array.length - 1; i >= 0; i--)
        {
            for(int j = array[i].length - 1; j >= 0; j--)
            {
                if(array[i][j] == '-')
                {
                    emptySpace++;
                }
            }
        }

        return emptySpace;
    }
}
