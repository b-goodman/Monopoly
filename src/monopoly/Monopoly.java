/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

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
        players.add("Ben", 1, Token.TOPHAT);
        players.add("Test 1", 2, Token.RACECAR);
        //Add defualt chest & chance card decks and gameboard cells
        ChanceCards chanceCardDeck = new ChanceCards("chanceCardDeck.CSV");
        ChestCards chestCardDeck = new ChestCards("chestCardDeck.CSV");
        Cells cells = new Cells("CellData.CSV");
        //Add dice - a pair of six-sided dice
        Dice dice = new Dice(6, 6);
        //Begin game.  Play for 5 rounds.
        int playerRounds = 0;
        do {
            for (int i = 1; i <= Players.amount(); i++) {
                do {
                    System.out.println(i);
                    Players.get(i).beginTurn();
                    //Players.get(i).midTurn();
                } while (Dice.isDouble() && !Players.get(i).isInJail());
                Players.get(i).endTurn();
            }
            playerRounds++;
        } while (playerRounds < 5);

    }

//        for (int i = 1; i < 20; i++) {
//            Players.get(1).drawChanceCard();
//            List playerCard = Players.get(1).readCurrentCard();
//            System.out.println(playerCard.get(1));
//            Players.get(1).parseCardAction(playerCard);
//        }
//        System.out.println(Players.get(2).drawChanceCard());
//        System.out.println(Players.get(1).drawChanceCard());
//        System.out.println(Players.get(1).drawChestCard());
//        System.out.println(Players.get(2).drawChestCard());
//        System.out.println(Players.get(1).drawChestCard());
}
