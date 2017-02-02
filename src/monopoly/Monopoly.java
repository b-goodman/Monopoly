/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author bgood_000
 */
public class Monopoly {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {

        //Add players
        Players players = new Players();
        players.add("Test1", 1, Token.TOPHAT);
        players.add("Test2", 2, Token.RACECAR);
        players.add("Test3", 3, Token.BATTLESHIP);
        //Add defualt chest & chance card decks and gameboard cells
        ChanceCards chanceCardDeck = new ChanceCards("chanceCardDeck.CSV");
        ChestCards chestCardDeck = new ChestCards("chestCardDeck.CSV");
        Cells cells = new Cells("CellData.CSV");
        //Add dice - a pair of six-sided dice
        Dice dice = new Dice(6, 6);

        //Begin game.  Play for 5 rounds.
        int playerRounds = 1;
        int PLAYER_ROUNDS_AMOUNT = 10;
        do {
            System.out.println("Round " + playerRounds + ":");
            for (int i = 1; i <= Players.amount(); i++) {
                do {
                    Players.get(i).beginTurn();
                    Players.get(i).midTurn();
                } while (Dice.isDouble() && !Players.get(i).isInJail());
                Players.get(i).endTurn();
            }
            playerRounds++;
            System.out.println();
        } while (playerRounds < PLAYER_ROUNDS_AMOUNT);

    }

}
