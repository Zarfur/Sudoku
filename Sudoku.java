// my imports
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class Sudoku{
    // create the sudoku board;
    public static int[][] board = {
        {-1, -1, -1, -1, -1, -1, -1, -1,-1}, {-1, -1, -1,-1, -1, -1, -1, -1, -1}, {-1, -1, -1,-1, -1, -1, -1, -1, -1},
        {-1, -1, -1, -1, -1, -1, -1, -1,-1}, {-1, -1, -1,-1, -1, -1, -1, -1, -1}, {-1, -1, -1,-1, -1, -1, -1, -1, -1},
        {-1, -1, -1, -1, -1, -1, -1, -1,-1}, {-1, -1, -1,-1, -1, -1, -1, -1, -1}, {-1, -1, -1,-1, -1, -1, -1, -1, -1},
    };
    // Make sure there is no duplicates by using a ALOT of arraylists
    public static ArrayList<Integer>[] cols = new ArrayList[9];
    public static ArrayList<Integer>[] rows = new ArrayList[9];
    public static ArrayList<Integer>[] sqrs = new ArrayList[9];

    public static Random rand = new Random();

    public static void main(String[] args){
        boardSettup();
        printBoard();
    }

    // methods
    public static void numberBank(){
        // creates a number bank for us to use to make sure there are
        // no dupes! 
        for(int i = 0; i < 9; i++){
            cols[i] = new ArrayList<Integer>(9);
            rows[i] = new ArrayList<Integer>(9);
            sqrs[i] = new ArrayList<Integer>(9);
        }
        for(int i = 0; i < 9; i++){
            for(int j = 1; j < 10; j++){
                cols[i].add(j);
                rows[i].add(j);
                sqrs[i].add(j);
            }
        }
    }

    // Below is my PREVIOUS boardSettup method
    // it does NOT work... But I used a lot of
    // logic from it to make my working algorithm
    public static void LEGACY_boardSettup(){
        // OLD board creation without
        // recursion / backtrack checking
        numberBank();
        int x = 0; // for debug
        for(int square = 0; square < 9; square++){
            
            int sCol = (square%3) * 3;
            int sRow = (square/3) * 3;
            
            for(int slot = 0; slot < 9; slot++){
                // make an arraylist of valid numbers
                // to chose from
                int col = slot%3 + sCol, row = slot/3 + sRow;
                ArrayList<Integer> isValid = new ArrayList<Integer>(9);
                for(int numb : sqrs[square]){
                    if(cols[col].contains(numb) && rows[row].contains(numb)){
                        isValid.add(numb);
                    }
                }
                System.out.println("iteration " + x + ": " + isValid); x++;


                // fall-back error quick fix that led to the
                // realization that i needed to use a different
                // strategy...
                if(isValid.size() <= 0) isValid.add(0);


                int chosen = rand.nextInt(isValid.size());
                int numb = isValid.get(chosen);

                // remove the number from the square and board slots
                // so it can not be used again for that specific
                // row, column, and square
                board[row][col] = numb;
                cols[col].remove(Integer.valueOf(numb));
                rows[row].remove(Integer.valueOf(numb));
                sqrs[square].remove(Integer.valueOf(numb));
            }
        }
    }

    // NEW BOARD SET UP
    public static void boardSettup(){
        // creates our board!
        numberBank();
        checkSlot(0, 0);
    }

    public static boolean checkSlot(int square, int slot){
        // check if a slot is valid and then reccursively checks the
        // next slots till board is full

        //  base case
        if(square > 8){
            return true;
        }
        
        // check which numbers are valid along with
        // the correct coordinates for each slot per square
        int row = ((square / 3) * 3) + slot/3;
        int col = ((square % 3) * 3) + slot%3;
        ArrayList<Integer> isValid = new ArrayList<Integer>(9);
        for(int numb : sqrs[square]){
            if(cols[col].contains(numb) && rows[row].contains(numb)){
                isValid.add(numb);
            }
        }

        for(int i : isValid){
            // randomly checks possible branches of
            // isValid
            int chosen = rand.nextInt(isValid.size());
            int numb = isValid.get(chosen);
            
            // remove the number from the square and board slots
            // so it can not be used again for that specific
            // row, column, and square
            board[row][col] = numb;
            cols[col].remove(Integer.valueOf(numb));
            rows[row].remove(Integer.valueOf(numb));
            sqrs[square].remove(Integer.valueOf(numb));

            // check next slot 
            int nextSqr = square, nextSlt = slot+1;
            if(nextSlt==9){
                nextSqr++;
                nextSlt = 0;
            }

            // solve the board recursively 
            if(checkSlot(nextSqr, nextSlt)) return true;

            // if can't solve
            // revert change if the number selection
            // leads to invalid number
            board[row][col] = -1;
            cols[col].add(numb);
            rows[row].add(numb);
            sqrs[square].add(numb);
        }

        return false;
    }

    public static void printBoard(){
        // prints our board
        for(int[] sqr : board){
            for(int i : sqr){
                System.out.print(i +" ");
            }
            System.out.println();
        }
    }

}