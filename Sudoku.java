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
    // Make sure there is no duplicates by using a horribly space inefficient
    // arraylists
    public static ArrayList<Integer>[] cols = new ArrayList[9];
    public static ArrayList<Integer>[] rows = new ArrayList[9];
    public static ArrayList<Integer>[] sqrs = new ArrayList[9];

    public static void main(String[] args){
        boardSettup();

        printBoard();
    }

    // methods
    public static void numberBank(){
        // creates a number bank for us to use to make sure there are
        // no dupes! I know space and time efficiency isnt the best but
        // this will be fixed in future, i hope.
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

    public static void boardSettup(){
        // creates our board!
        Random rand = new Random();
        numberBank();
        for(int square = 0; square < 9; square++){
            
            int sCol = (square%3) * 3;
            int sRow = (square/3) * 3;
            
            for(int slot = 0; slot < 9; slot++){
                // make an arraylist of valid numbers
                // to chose from
                int col = sCol + slot%3, row = sRow + slot/3;
                ArrayList<Integer> isValid = new ArrayList<Integer>(9);
                for(int numb : sqrs[square]){
                    if(cols[col].contains(numb) && rows[row].contains(numb)){
                        isValid.add(numb);
                    }
                }
                if(isValid.size() <= 0) isValid.add(-1);
                int chosen = rand.nextInt(isValid.size());
                int numb = sqrs[square].get(chosen);

                // remove the number from the square and board slots
                // so it can not be used again for that specific
                // row, column, and square
                board[square][slot] = numb;
                cols[col].remove(Integer.valueOf(numb));
                rows[row].remove(Integer.valueOf(numb));
                sqrs[square].remove(Integer.valueOf(numb));
            }
        }
    }

    public static void printBoard(){
        // prints our board
        for(int[] sqr : board){
            for(int i : sqr){
                System.out.println(i);
            }
            System.out.println();
        }
    }

}