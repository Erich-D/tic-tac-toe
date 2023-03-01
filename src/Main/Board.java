package Main;

public class Board {
    // a 2D array to represent the pieces on the board:
    char [][] board;

    // should instantiate the board to be a 3x3 array:
    public Board() {
        this.board = new char[][]{{'7','8','9'},{'4','5','6'},{'1','2','3'}};
    }

    // this method should take in a row, column, and a newChar and update the board accordingly:
    public void updateBoard(int row, int column, char newChar) {
        this.board[row][column] = newChar;
    }

    // this method should return a string representation of the board:
    public String toString() {
        return "+---+---+---+\n" +
                "| "+this.board[0][0]+" | "+this.board[0][1]+" | "+this.board[0][2]+" |\n"+
                "+---+---+---+\n"+
                "| "+this.board[1][0]+" | "+this.board[1][1]+" | "+this.board[1][2]+" |\n"+
                "+---+---+---+\n"+
                "| "+this.board[2][0]+" | "+this.board[2][1]+" | "+this.board[2][2]+" |\n"+
                "+---+---+---+\n";
    }
}
