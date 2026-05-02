// my imports
import java.util.ArrayList;
import java.util.Random;

public class Sudoku{
    // create the sudoku board;
    private static int[][] board = {
        {-1, -1, -1, -1, -1, -1, -1, -1,-1}, {-1, -1, -1,-1, -1, -1, -1, -1, -1}, {-1, -1, -1,-1, -1, -1, -1, -1, -1},
        {-1, -1, -1, -1, -1, -1, -1, -1,-1}, {-1, -1, -1,-1, -1, -1, -1, -1, -1}, {-1, -1, -1,-1, -1, -1, -1, -1, -1},
        {-1, -1, -1, -1, -1, -1, -1, -1,-1}, {-1, -1, -1,-1, -1, -1, -1, -1, -1}, {-1, -1, -1,-1, -1, -1, -1, -1, -1},
    };
    // create base board for refrence in scramble
    private static int[][] base = {
        {-1, -1, -1, -1, -1, -1, -1, -1,-1}, {-1, -1, -1,-1, -1, -1, -1, -1, -1}, {-1, -1, -1,-1, -1, -1, -1, -1, -1},
        {-1, -1, -1, -1, -1, -1, -1, -1,-1}, {-1, -1, -1,-1, -1, -1, -1, -1, -1}, {-1, -1, -1,-1, -1, -1, -1, -1, -1},
        {-1, -1, -1, -1, -1, -1, -1, -1,-1}, {-1, -1, -1,-1, -1, -1, -1, -1, -1}, {-1, -1, -1,-1, -1, -1, -1, -1, -1},
    };
    // create scrambled board
    private static int[][] scrambled = {
        {-1, -1, -1, -1, -1, -1, -1, -1,-1}, {-1, -1, -1,-1, -1, -1, -1, -1, -1}, {-1, -1, -1,-1, -1, -1, -1, -1, -1},
        {-1, -1, -1, -1, -1, -1, -1, -1,-1}, {-1, -1, -1,-1, -1, -1, -1, -1, -1}, {-1, -1, -1,-1, -1, -1, -1, -1, -1},
        {-1, -1, -1, -1, -1, -1, -1, -1,-1}, {-1, -1, -1,-1, -1, -1, -1, -1, -1}, {-1, -1, -1,-1, -1, -1, -1, -1, -1},
    };

    // Make sure there is no duplicates by using a ALOT of arraylists
    private static ArrayList<ArrayList<Integer>> cols = new ArrayList<ArrayList<Integer>>(9);
    private static ArrayList<ArrayList<Integer>> rows = new ArrayList<ArrayList<Integer>>(9);
    private static ArrayList<ArrayList<Integer>> sqrs = new ArrayList<ArrayList<Integer>>(9);

    public static Random rand = new Random();

    public Sudoku(){
        boardSettup();
    }
    

    // methods
    public static void numberBank(){
        // creates a number bank for us to use to make sure there are
        // no dupes! 
        for(int i = 0; i < 9; i++){
            cols.add(new ArrayList<Integer>(9));
            rows.add(new ArrayList<Integer>(9));
            sqrs.add(new ArrayList<Integer>(9));
        }
        for(int i = 0; i < 9; i++){
            for(int j = 1; j < 10; j++){
                cols.get(i).add(j);
                rows.get(i).add(j);
                sqrs.get(i).add(j);
            }
        }
    }

    // NEW BOARD SET UP
    private static void boardSettup(){
        // creates our board!
        numberBank();
        checkSlot(0, 0);
    }

    private static boolean checkSlot(int square, int slot){
        // check if a slot is valid and then reccursively checks the
        // next slots till board is full

        //  base case
        if(square > 8) return true;
        
        // check which numbers are valid along with
        // the correct coordinates for each slot per square
        int row = ((square / 3) * 3) + slot/3;
        int col = ((square % 3) * 3) + slot%3;
        ArrayList<Integer> isValid = new ArrayList<Integer>(9);
        for(int numb : sqrs.get(square)){
            if(cols.get(col).contains(numb) && rows.get(row).contains(numb)){
                isValid.add(numb);
            }
        }

        for(int i =0; i < isValid.size();i++){
            // randomly checks possible branches of
            // isValid
            int chosen = rand.nextInt(isValid.size());
            int numb = isValid.get(chosen);
            isValid.remove(chosen);
            
            // remove the number from the square and board slots
            // so it can not be used again for that specific
            // row, column, and square
            board[row][col] = numb;
            cols.get(col).remove(Integer.valueOf(numb));
            rows.get(row).remove(Integer.valueOf(numb));
            sqrs.get(square).remove(Integer.valueOf(numb));

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
            cols.get(col).add(numb);
            rows.get(row).add(numb);
            sqrs.get(square).add(numb);
            isValid.add(numb);
        }

        return false;
    }

    public void printBoard(int isScrambled){
        int[][] arr = base;
        if(isScrambled == 1) arr = scrambled;
        if(isScrambled == 2) arr = board;

        System.out.println("+-------++-------++-------+");
        for(int i = 0; i < 9; i++){
            System.out.print("| ");
            for(int j = 0; j < 9; j++){
                System.out.print(arr[i][j] + " ");
                if(j == 2 || j == 5) System.out.print("|| ");  }
            System.out.println("|");
            if(i == 2 || i == 5) System.out.println("+-------++-------++-------+");
        }
        System.out.println("+-------++-------++-------+");
    }

    public void scramble(int difficulty){
        // harder difficulty = more empty slots
        int x = 0;
        switch(difficulty){
            case 1: x = 3; break;
            case 2: x = 2; break;
            case 3: x = 1; break;
        }
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                // scrambles the board by randomly taking
                // numbers from the solved board
                if(rand.nextInt(4) < x){
                    scrambled[i][j] = board[i][j];
                    base[i][j] = board[i][j];
                }else{
                    scrambled[i][j] = 0;
                    base[i][j] = 0;
                }
            }
        }
    }

    public boolean checkWin(){
        // check if the scrambled board is equal to
        // the solved board
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                if(scrambled[i][j] != board[i][j]) return false;
            }
        }
        return true;
    }

    // getters
    public int[][] getBoard(){
        return board;
    }
    public int[][] getScrambled(){
        return scrambled;
    }
    public int[][] getBase(){
        return base;
    }

//------------------------------------------------------------------------------------
    // Below is my PREVIOUS boardSettup method
    // it does NOT work... But I used a lot of
    // logic from it to make my working algorithm
    private static void LEGACY_boardSettup(){
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
                for(int numb : sqrs.get(square)){
                    if(cols.get(col).contains(numb) && rows.get(row).contains(numb)){
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
                cols.get(col).remove(Integer.valueOf(numb));
                rows.get(row).remove(Integer.valueOf(numb));
                sqrs.get(square).remove(Integer.valueOf(numb));
            }
        }
    }
    
}
