/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import java.util.List;

/**
 *
 * @author bgood_000
 */
public class Monopoly {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //Add players
        Players players = new Players();
        players.add("Ben", 1, Token.TOPHAT);

        //Construct game board
        Cells cells = new Cells();
        cells.add(1, "GO", "grey", "creditAbs", "200");
        cells.add(2, "Old Kent Road", "brown", 'A', 60, 50, 30, 30, 2, 10, 30, 90, 160, 250);
        cells.add(3, "Community Chest A", "grey", "drawCard", "chest");
        cells.add(4, "Whitechapel Road", "brown", 'A', 60, 50, 30, 30, 4, 20, 60, 180, 360, 450);
        cells.add(5, "Income Tax", "grey", "debitAbs", "100");
        cells.add(6, "Reading Railroad", "grey", 200, 100, 25, 50, 100, 200);

        //Construct chance card deck
        CardDeck chanceCardDeck = new CardDeck();
        chanceCardDeck.add("1", "Advance to GO", "transitionAbs", "1", "null");
        chanceCardDeck.add("2", "Advance to Trafalgar Square", "transitionAbs", "25", "null");
        chanceCardDeck.add("3", "Advance to Pall Mall", "transitionAbs", "12", "null");
        chanceCardDeck.shuffleDeck();

        List current = chanceCardDeck.drawCard();
        System.out.println(current.get(2));

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
    }

}
