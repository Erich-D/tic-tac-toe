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

    Integer[] nums = new Integer[]{7, 8, 9, 4, 5, 6, 1, 2, 3};
    List<Integer> cells;

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
        c.getCells().add(this.nums[spot-1]);
        cells.remove((Integer)spot);
        int cell = this.nums[spot-1]-1;
        this.board.updateBoard((cell)/3,(cell)%3,c.getGamePiece());
//
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

    private boolean checkPlay(int spot){
        return cells.contains((Integer)spot);
    }

    private void loadCells(){
        cells = new ArrayList<>(Arrays.asList(nums));
    }
    // 1. Set up, ask players for names and game piece
    // 2. For each round, let each player pick a spot
    // 3. Print the board after each move:
    // 4. When the game is over, announce the winner or that it is a tie
    public static void main(String[] args) {
        boolean playGame = true;
        Scanner gs = new Scanner(System.in);
        Game game = new Game();
        game.initPlayers(gs);
        while(playGame) {
            Scanner in = new Scanner(System.in);
            game.loadCells();
            game.board = new Board();
            game.status = Status.UNFINISHED;
            int spot = 0;
            int round = 0;
            game.player1.getCells().clear();
            game.player2.getCells().clear();
            System.out.println(game.board.toString());
            while (game.getStatus() == Status.UNFINISHED) {
                Player player = (round % 2 == 0) ? game.player1 : game.player2;
                System.out.println("It's " + player.getName() + "s turn");
                boolean turn = true;
                while (turn) {
                    System.out.print("Please pick the number of the spot want (1-9): ");
                    if (!in.hasNext("\\b[1-9]\\b")) {
                        System.out.println("Input must be a single digit from one to nine. ");
                        in.next();
                    } else {
                        spot = in.nextInt();
                        if (!game.checkPlay(spot)) {
                            System.out.println("That spot has been selected, please pick another. ");
                        } else {
                            turn = false;
                        }
                    }
                }
                game.playRound(spot, player);
                round++;
                System.out.println(game.board.toString());
            }
            if (game.status == Status.TIE) {
                System.out.println("Game ended in a tie");
            } else if (game.status == Status.PLAYER1_WON) {
                System.out.println(game.player1.getName() + " has won!");
            } else if (game.status == Status.PLAYER2_WON) {
                System.out.println(game.player2.getName() + " has won!");
            } else {
                System.out.println("Something appears to have gone wrong.");
            }
            System.out.print("Would you like to try again? (enter y/n)");
            String newGame = gs.nextLine();
            if(newGame.equals("y")){
                System.out.println("Good Luck");
            }else {playGame = false;}
        }
    }
}
