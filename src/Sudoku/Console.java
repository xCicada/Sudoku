package Sudoku; /**
 * Created by Adonis on 02/04/2016.
 */

import java.util.*;

public class Console
{
    public static final int BoardWidth = 9;
    public static final int BoardHeight = 9;
    int[][] board;
    int[][] boardH;

    /**
     * Constructor that resets board to zeros.
     * var board is the full bord while var boardH is the board with holes
     */

    public Console() {
        board = new int[BoardWidth][BoardHeight];
        boardH = new int[BoardWidth][BoardHeight];
    }

    /**
     * param holes is the number of blank spaces we want to insert
     * Return a board with holes
     */
    public int[][] createBoard(int holes)
    {
        nextCell(0,0);
        makeHoles(holes);
        return boardH;
    }

    /**
     * Recursive method that place every number in a cell.
     * Return true if the board completed legally, false if this cell has no solutions.
     */
    public boolean nextCell(int x, int y)
    {
        int nX = x;
        int nY = y;
        int[] check = {1,2,3,4,5,6,7,8,9};
        Random r = new Random();
        int tmp = 0;
        int current = 0;
        int topList = check.length;

        for(int i=topList-1;i>0;i--)
        {
            current = r.nextInt(i);
            tmp = check[current];
            check[current] = check[i];
            check[i] = tmp;
        }

        int i;
        for(i = 0; i<check.length; i++)
            if (legalMove(x, y, check[i])) {
                board[x][y] = check[i];
                boardH[x][y] = check[i];
                if (x == 8) {
                    if (y == 8) {
                        return true;
                    } else {
                        nX = 0;
                        nY = y + 1;
                    }
                } else {
                    nX = x + 1;
                }
                if (nextCell(nX, nY))
                    return true;
            }
        board[x][y] = 0;
        boardH[x][y] = 0;
        return false;
    }

    /**
     * Determine if a number can be inserted into a cell legally.

     * param current is the value to check in said cell.
     * Return true if current is legal, false otherwise.
     */
    private boolean legalMove(int x, int y, int current) {
        for(int i=0;i<9;i++) {
            if(current == board[x][i])
                return false;
        }
        for(int i=0;i<9;i++) {
            if(current == board[i][y])
                return false;
        }
        int cornerX = 0;
        int cornerY = 0;
        if(x > 2)
            if(x > 5)
                cornerX = 6;
            else
                cornerX = 3;
        if(y > 2)
            if(y > 5)
                cornerY = 6;
            else
                cornerY = 3;
        for(int i=cornerX;i<10 && i<cornerX+3;i++)
            for(int j=cornerY;j<10 && j<cornerY+3;j++)
                if(current == board[i][j])
                    return false;
        return true;
    }

    /**
     * Replace a given amount of cells with 0s for blanks
     * param holesToMake is how many 0s we want in the board.
     */
    public void makeHoles(int holesToMake)
    {
        double remainingSquares = 81;
        double remainingHoles = (double)holesToMake;

        for(int i=0;i<9;i++)
            for(int j=0;j<9;j++)
            {
                double holeChance = remainingHoles/remainingSquares;
                if(Math.random() <= holeChance)
                {
                    boardH[i][j] = 0;
                    remainingHoles--;
                }
                remainingSquares--;
            }
    }

    public static void main(String[] args)
    {
        Console sudoku = new Console();
        GUI GridPanel = new GUI(sudoku);
        GridPanel.createGui(GridPanel);
    }
}