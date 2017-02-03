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
        //new Players();
        Players.add("Test1", 1, Token.TOPHAT);
        Players.add("Test2", 2, Token.RACECAR);
        Players.add("Test3", 3, Token.BATTLESHIP);

        Rules.setPassGoCredit(200);
        Rules.setGoLandingBonus(true);

        //Add defualt chest & chance card decks and gameboard cells
        new ChanceCards("chanceCardDeck.CSV");
        new ChestCards("chestCardDeck.CSV");
        new Cells("CellData.CSV");
        //Add dice - a pair of six-sided dice
        new Dice(6, 6);

        //Begin game.  Play for 5 rounds.
        int playerRounds = 1;
        int PLAYER_ROUNDS_AMOUNT = 100;
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

//TODO track jail bonds
//complete rule class
//begin jail exit strategies
