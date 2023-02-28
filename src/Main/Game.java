package Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

enum Status {
    // there is a row, column or diagonal consisting of only player1's game pieces:
    PLAYER1_WON,
    // there is a row, column or diagonal consisting of only player2's game pieces:
    PLAYER2_WON,
    // there is no row, column, or diagonal consisting of a single player's pieces:
    TIE,
    // there are spots on the board which are uninitialized;
    UNFINISHED
}

public class Game {
    Player player1;
    Player player2;
    Board board;
    Status status;

    Integer[] nums = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
    List<Integer> cells = new ArrayList<>(Arrays.asList(nums));

    // this function should prompt the players for names and gamePiece and assign it
    // to the player objects:
    private void initPlayers(Scanner in) {
        System.out.print("Player X please type in name: ");
        String name  = in.nextLine();
        this.player1 = new Player(name, 'X');
        System.out.print("Player O please type in name: ");
        name = in.nextLine();
        this.player2 = new Player(name,'O');
    }

    // this method should let each player pick a spot and also print the board between selections:
    private void playRound(int spot, Player c) {
        c.getCells().add(spot);
        cells.remove(0);
        switch (spot){
            case 1:
                this.board.updateBoard(0,0,c.getGamePiece());
                break;
            case 2:
                this.board.updateBoard(0,1,c.getGamePiece());
                break;
            case 3:
                this.board.updateBoard(0,2,c.getGamePiece());
                break;
            case 4:
                this.board.updateBoard(1,0,c.getGamePiece());
                break;
            case 5:
                this.board.updateBoard(1,1,c.getGamePiece());
                break;
            case 6:
                this.board.updateBoard(1,2,c.getGamePiece());
                break;
            case 7:
                this.board.updateBoard(2,0,c.getGamePiece());
                break;
            case 8:
                this.board.updateBoard(2,1,c.getGamePiece());
                break;
            case 9:
                this.board.updateBoard(2,2,c.getGamePiece());
                break;
            default:
                break;
        }
        if(c.getCells().contains(1) && c.getCells().contains(2) && c.getCells().contains(3)){
            this.status = (c.getGamePiece()=='X') ? Status.PLAYER1_WON:Status.PLAYER2_WON;
        } else if (c.getCells().contains(4) && c.getCells().contains(5) && c.getCells().contains(6)) {
            this.status = (c.getGamePiece()=='X') ? Status.PLAYER1_WON:Status.PLAYER2_WON;
        } else if (c.getCells().contains(7) && c.getCells().contains(8) && c.getCells().contains(9)) {
            this.status = (c.getGamePiece()=='X') ? Status.PLAYER1_WON:Status.PLAYER2_WON;
        } else if (c.getCells().contains(1) && c.getCells().contains(4) && c.getCells().contains(7)) {
            this.status = (c.getGamePiece()=='X') ? Status.PLAYER1_WON:Status.PLAYER2_WON;
        } else if (c.getCells().contains(2) && c.getCells().contains(5) && c.getCells().contains(8)) {
            this.status = (c.getGamePiece()=='X') ? Status.PLAYER1_WON:Status.PLAYER2_WON;
        } else if (c.getCells().contains(3) && c.getCells().contains(6) && c.getCells().contains(9)) {
            this.status = (c.getGamePiece()=='X') ? Status.PLAYER1_WON:Status.PLAYER2_WON;
        } else if (c.getCells().contains(3) && c.getCells().contains(5) && c.getCells().contains(7)) {
            this.status = (c.getGamePiece()=='X') ? Status.PLAYER1_WON:Status.PLAYER2_WON;
        } else if (c.getCells().contains(1) && c.getCells().contains(5) && c.getCells().contains(9)) {
            this.status = (c.getGamePiece()=='X') ? Status.PLAYER1_WON:Status.PLAYER2_WON;
        } else if (cells.isEmpty()) {
            this.status = Status.TIE;
        }
    }

    // This should return a status of the board, using the enum Status:
    private Status getStatus() {
        return this.status;
    }

    // 1. Set up, ask players for names and game piece
    // 2. For each round, let each player pick a spot
    // 3. Print the board after each move:
    // 4. When the game is over, announce the winner or that it is a tie
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Game game = new Game();
        game.board = new Board();
        game.status = Status.UNFINISHED;

        int round = 0;
        game.initPlayers(in);
        System.out.println(game.board.toString());
        while(game.getStatus() == Status.UNFINISHED){
            Player player = (round%2==0) ? game.player1:game.player2;
            System.out.println("It's "+player.getName()+"s turn");
            System.out.print("Please pick the number of the spot want (1-9): ");
            int spot = in.nextInt();
            game.playRound(spot, player);
            round ++;
            System.out.println(game.board.toString());
        }
        if(game.status == Status.TIE){
            System.out.println("Game ended in a tie");
        } else if (game.status == Status.PLAYER1_WON) {
            System.out.println(game.player1.getName()+" has won!");
        } else if (game.status == Status.PLAYER2_WON) {
            System.out.println(game.player2.getName()+" has won!");
        }else{
            System.out.println("Something appears to have gone wrong.");
        }

    }


}
