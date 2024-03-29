import java.awt.Taskbar.State;
import java.util.*;

public class Board {
    Scanner scan = new Scanner(System.in);
    private int[][] board;
    private ArrayList<String> colors = new ArrayList<>();
    private ArrayList<State2> states = new ArrayList<>();
    private ArrayList<int[]> badSquares = new ArrayList<>();
    private ArrayList<int[]> alreadySwapped = new ArrayList<>();

    //Creating the color arrayList because of indexOf
    public Board() {
        colors.add("R");
        colors.add("G");
        colors.add("B");
        colors.add("C");
        colors.add("P");
        colors.add("Y");
    }




    //returns the board in format [a, b, c]\n[d, e, f]
    public String toString() {
        String printBoard = "";
        for (int[] ints : board) {
            printBoard += "\n[";
            for (int j = 0; j < ints.length; j++) {
                if (j == ints.length - 1) { printBoard += ints[j] + "]"; }
                else { printBoard += ints[j] + ", "; }
            }
        }
        return printBoard;
    }


    //based on path it returns row - 0, column - 1, area - 2, else - (-1)
    public int boardSize(int path) {
        if (path == 0) {
            return board.length;
        } else if (path == 1) {
            return board[0].length;
        } else if (path == 2) {
            return board.length * board[0].length;
        } else {
            return -1;
        }
    }

    //Checks how many instances of a color are in the board
    private int colorCount(int value) {
        int count = 0;
        for (int[] ints : board) {
            for (int j = 0; j < board[0].length; j++) {
                if (ints[j] == value) { count++; }
            }
        }
        return count;
    }

    //checks if any colors exceed half of the board.ceiling()
    //i.e. 25/2 = 12.5 --> 13 --> (13 > colorCount?)
    public boolean solvable() {
        for (int i = 0; i < 6; i++) {
            if (Math.ceil(boardSize(2) / 2d) < colorCount(i)) { return false; }
        }
        return true;
    }

    //asks for the rows, columns, and creates the board from user input
    public void createBoard() {
        System.out.println("Enter in the amount of rows");
        int rowLength = scan.nextInt();
        System.out.println("Enter in the amount of columns");
        int columnLength = scan.nextInt();
        board = new int[rowLength][columnLength];
        System.out.println("Enter in the tiles\nOptions : R, G, B, C, P, Y");
        for (int i = 0; i < rowLength; i++) {
            System.out.println("Row " + i);
            for (int j = 0; j < columnLength; j++) {
                System.out.println("Column " + j);
                board[i][j] = colors.indexOf(scan.next().toUpperCase());
            }
        }
    }

    //adds 1 to count every pair of 2 similarly colored tiles
    //start vertically then horizontally
    //might not work with small boards
    public int boardValue() {
        int count = 0;
        for (int i = 1; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == board[i - 1][j]) {
                    count++;
                    badSquares.add(new int[] {i, j});
                    badSquares.add(new int[] {i - 1 , j});
                }
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 1; j < board[0].length; j++) {
                if (board[i][j] == board[i][j - 1]) {
                    count++;
                    badSquares.add(new int[] {i, j});
                    badSquares.add(new int[] {i , j - 1});
                }
            }
        }
        return count;
    }



    //returns a board with the swap made
    //0 - up, 1 - left, 2 - right, 3 - down
    public int[][] swap(int x1, int y1, int direction) {
        int x2 = x1;
        int y2 = y1;
        int[][] copy = new int[board.length][];
        for (int i = 0; i < board.length; i++) {
          copy[i] = board[i].clone();
        }
        switch (direction) {
            case 0 :
                x2--;
            case 1 :
                y2--;
            case 2 :
                y2++;
            case 3 :
                x2++;
        }
        int temp = copy[x1][y1];
        copy[x1][y1] = copy[x2][y2];
        copy[x2][y2] = temp;
        return copy;
    }


    //checks if it can swap in the direction provided and its not the same value
    //Up Left Right Down - 1 2 3 4 (same convention as earlier)
    private boolean canSwap(int x, int y, int direction) {
        switch (direction) {
            case 0 :
                if (x - 1 < 0 || board[x][y] == board[x - 1][y]) {
                    return false;
                }
            case 1 :
                if (y - 1 < 0 || board[x][y] == board[x][y - 1]) {
                    return false;
                }
            case 2 :
                if (y + 1 >= board[0].length || board[x][y] == board[x][y + 1]) {
                    return false;
                }
            case 3 :
                if (x + 1 >= board.length || board[x][y] == board[x + 1][y]) {
                    return false;
                }
        }
        return true;
    }



    //adds a new state given the initial tile coordinates and the direction
    public void addState(int x, int y, int direction) {
        System.out.printf("[%d , %d]\n%d - Direction\n", x, y, direction);
        State2 temp = new State2(swap(x, y, direction));
        states.add(temp);
    }





    public int minimizeAlgorithm() {
        for (int[] badSquare : badSquares) {
          for (int i = 0; i < 4; i++) {
            if (canSwap(badSquare[0], badSquare[1], i)) {
              addState(badSquare[0], badSquare[1], i);
            }
          }
        }
        for (State2 state : states) {
          System.out.println(state);
          //System.out.println(state.boardValue());
          
        }
      
      return 256;
    }






}
