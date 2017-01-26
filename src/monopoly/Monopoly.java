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

        ChanceCards chanceCardDeck = new ChanceCards();
        Cells cells = new Cells();

        //Construct community chest deck
        ChestCards chestCardDeck = new ChestCards();
        chestCardDeck.add("1", "Advance to GO", "transitionAbs", "1", "null");
        chestCardDeck.add("2", "Collect £50", "creditAbs", "50", "null");
        chestCardDeck.add("3", "Pay £50", "debitAbs", "50", "null");
        ChestCards.shuffleDeck();

        System.out.println(Players.get(1).drawChanceCard());
        System.out.println(Players.get(2).drawChanceCard());
        System.out.println(Players.get(1).drawChanceCard());
        //System.out.println(Players.get(1).drawChestCard());
        //System.out.println(Players.get(2).drawChestCard());
        //System.out.println(Players.get(1).drawChestCard());

        System.out.println("\n");

        // System.out.println(getCSV.get(16)[0]);
        //System.out.println(CardDeck.CARD_DECK);
        //Test
//        System.out.println(chanceCardDeck.CARD_LIB);
//        System.out.println(chanceCardDeck.CARD_DECK);
//        System.out.println();
//        System.out.println(chanceCardDeck.getNextCard() == null);
//        System.out.println(chanceCardDeck.drawCard());
//        System.out.println(chanceCardDeck.CARD_DECK);
//        System.out.println();
//        System.out.println(chanceCardDeck.getNextCard() == null);
//        System.out.println(chanceCardDeck.drawCard());
//        System.out.println(chanceCardDeck.CARD_DECK);
//        System.out.println();
//        System.out.println(chanceCardDeck.getNextCard() == null);
//        System.out.println(chanceCardDeck.drawCard());
//        System.out.println(chanceCardDeck.CARD_DECK);
//        System.out.println();
//        //   chanceCardDeck.shuffleDeck();
//        System.out.println();
//        System.out.println(chanceCardDeck.getNextCard() == null);
//        System.out.println(chanceCardDeck.drawCard());
//        System.out.println(chanceCardDeck.CARD_DECK);
        //  System.out.println(Cells.LOCATIONS.get(1).getCellType());
        // System.out.println(Cell.PROPERTY_ID_BY_NAME);
        // System.out.println(players.get(1).findNextCellType("railroad"));
        //System.out.println(chanceCardDeck.getNextCard()==null);
        //System.out.println(chanceCardDeck.drawCard());
        //System.out.println(chanceCardDeck.CARD_DECK);
        //  System.out.println(chanceCardDeck.drawNextCard());
        // System.out.println(chanceCardDeck.drawNextCard());
        //System.out.println(chanceCardDeck.drawNextCard());
        //      System.out.println(players.get(1).getPlayerID());
        //	cells.get(2).setOwnership(players.get(1).getPlayerID());
        //     players.get(1).setOwnership(2);
        //System.out.println(cells.get(2).getOwnership().getName());
        //      System.out.println(cells.get(2).getOwningPlayerOwnership());
        //     System.out.println(cells.get(2).isSetComplete());
        //    System.out.println(players.get(1).getToken());
        //    System.out.println(Cell.PLAYER_OWNERSHIP);
        //    players.get(1).setOwnership(4);
        //     cells.get(2).addImprovement();
        //    System.out.println(cells.get(2).getRent());
        //Chance Card Import Testing:
        System.out.println(Cells.LOCATIONS.get(0).getName());
        System.out.println(Cells.LOCATIONS.get(40).getName());

    }

}
