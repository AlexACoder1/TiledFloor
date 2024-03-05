public class State2 extends Board {

    private int[][] board;




    public State2(int[][] board) {
        this.board = board;
      
    }


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










}
