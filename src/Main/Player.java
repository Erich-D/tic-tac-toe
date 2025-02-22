package Main;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    // gamePiece refers to which character will be placed on the board to indicate
    // that the player claims that spot:
    private char gamePiece;

    private int icells = 0;

    // this constructor should take in a name and a game piece and assign the values to the fields:
    public Player(String name, char gamePiece) {
        this.name = name;
        this.gamePiece = gamePiece;
    }

    // should return the player's name:
    public String getName() {
        return this.name;
    }

    // should return the game piece of the player:
    public char getGamePiece() {
        return this.gamePiece;
    }

    public int getIcells() {
        return icells;
    }

    public void setIcells(int icells) {
        this.icells = icells;
    }
}
