# Sudoku
Sudoku by Arthur

Description: 
This project makes a Sudoku board for you to solve!
It creates a solved board, randomly gets rid of elements and its YOUR job to solve it.
It creates this board by recursively and randomly taking numbers from a number bank.
If these random numbers result in an unsolvable board, it undoes its change and continues to solve.

How 2 Run: 
To run the program, you run the main file which is Game.java. From there, instructions on playing will be printed. 
Solve the board!

Generation Summary: 
The sudoku board is generated using recursion and random-ness. First, there is a number bank that is created
to ensure that there is no dupilcate numbers in rows, columns, and squares. For each slot in the board, a random number
is chosen from the available banks to put into the board. From there, it keeps doing this recursively until
the board is full. If the board cannot be solved due to the randomness locking it into a corner, then
the method undoes a decisions until it results in a solvable board. 

Files included: 
Sudoku.java - generates and prints the sudoku board and the sudoku board that needs to be solved.
Game.java - the way to play Sudoku