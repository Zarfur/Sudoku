// our imports
import java.util.Scanner;
public class Game{
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args){
        Sudoku game = new Sudoku();

        System.out.println("Choose your difficulty: 1. Easy, 2. Medium, 3. Hard");
        int difficulty = sc.nextInt();
        game.scramble(difficulty);
        System.out.println("Heads up! The slots inside the squares are numbered from 1 - 9 starting from top left and going right and then going down.");
        System.out.println("If you took each row and layed them out, the indexes would be like this: 1 2 3, 4 5 6, 7 8 9");
        System.out.println("Here is your board!: ");
        game.printBoard(1);
        while(true){
            
            // get inputs
            System.out.println("\nEnter the square you want to place your number ( 1 - 9 ): ");
            int sqr = sc.nextInt() - 1;
            System.out.println("Enter the slot inside of the square ( 1 - 9 ) : ");
            int slot = sc.nextInt() - 1;
            System.out.println("Enter the number you want to place: ");
            int numb = sc.nextInt();
            
            // check if move is valid 
            if(numb < 1 || numb > 9 || slot < 0 || slot > 8 || sqr < 0 || sqr > 8 || game.getBase()[sqr][slot] > 0){
                System.out.println("Invalid move. Try again!");
                continue;
            }
            // enter move
            game.getScrambled()[sqr][slot] = numb;

            System.out.println("If you want to see the original board to see which numbers were originally there, enter 1. If you give up, enter 2. Enter 0 to continue playing.");
            int input = sc.nextInt();
            if(input == 1) game.printBoard(0);
            if(input == 2){
                System.out.println("\n solved puzzle: ");
                game.printBoard(2);
                break;
            }
            System.out.println("\nHere is the current board: ");
            game.printBoard(1);
            // check game win
            if(game.checkWin()){
                System.out.println("You won sudoku!");
                break;
            }
        }

       
    }
}