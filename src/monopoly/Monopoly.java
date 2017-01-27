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
        players.add("Ben", 1, Token.TOPHAT);
        players.add("Test 1", 2, Token.RACECAR);

        //create chest & chance card decks and gameboard cells
        ChanceCards chanceCardDeck = new ChanceCards("chanceCardDeck.CSV");
        ChestCards chestCardDeck = new ChestCards("chestCardDeck.CSV");
        Cells cells = new Cells("CellData.CSV");

//        System.out.println(Players.get(1).drawChanceCard());
//        System.out.println(Players.get(2).drawChanceCard());
//        System.out.println(Players.get(1).drawChanceCard());
//        System.out.println(Players.get(1).drawChestCard());
//        System.out.println(Players.get(2).drawChestCard());
//        System.out.println(Players.get(1).drawChestCard());
        System.out.println(Players.get(1).findNextCellType("utility"));
        System.out.println(Cells.get(13).getName());

    }

}
