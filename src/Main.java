import java.util.*;



public class Main {
    public static void main(String[] args) {
        //oof gl
        Scanner scan = new Scanner(System.in);


        Board originalBoard= new Board();
        originalBoard.createBoard();

        //creates new boards until its solvable
        // SHOULD REPLACE WITH NOT POSSIBLE IN ACCORDANCE TO THE PROBLEM
        // OK?
        while (!originalBoard.solvable()) {
            System.out.println("This board is not solvable\nCreate a new board\n");
            originalBoard.createBoard();
        }

        System.out.println(originalBoard);
        System.out.println(originalBoard.boardValue());

        

        //Minimize Algorithm
        //Make a function that stores all the resulting states
        //it determines the best states and repeats until the optimal way is found



        //More specifically
        /*
        //make a function that determines if it has similarly colored squares around it

        make a function that makes every possible swap with a square that has
        a similarly colored square around it and stores the states

        a function that goes through the state array and only keeps the ones that have
        the lowest board value

        create a while loop that checks everytime a state created another generation
        if the combined values of the generations are lower than the rest then delete
        that generation

        once the board is solved then record the amount of times that it took

        once you find the results make the option to keep the top x highest ones
        that way you can see if there really is such a thing as creating better
        positions from different variations but you can just logic that one out

         */











    }
}