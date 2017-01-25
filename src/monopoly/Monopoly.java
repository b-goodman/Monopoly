/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import com.opencsv.CSVReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

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

        //Construct game board
        Cells cells = new Cells();
        cells.add(1, "GO", "grey", "creditAbs", "200");
        cells.add(2, "Old Kent Road", "brown", 'A', 60, 50, 30, 30, 2, 10, 30, 90, 160, 250);
        cells.add(3, "Community Chest A", "grey", "drawCard", "chest");
        cells.add(4, "Whitechapel Road", "brown", 'A', 60, 50, 30, 30, 4, 20, 60, 180, 360, 450);
        cells.add(5, "Income Tax", "grey", "debitAbs", "100");
        cells.add(6, "Reading Railroad", "grey", 200, 100, 25, 50, 100, 200);

        //Chance Card Import Testing:
        //Instantiate new CSV reader with specified filepath
        CSVReader reader = new CSVReader(new FileReader("C:/Users/bgood_000/Documents/chanceCardDeck.CSV"));//TODO - change to relative FP.
        //Read all entries found in CSV file
        List<String[]> getCSV = reader.readAll();
        //get amount of entries
        int entries = getCSV.size();
        //Declare Paramaters
        String index;
        String text;
        String type;
        String typeParamater1;
        String typeParamater2;
        //Construct chance card deck
        ChanceCards chanceCardDeck = new ChanceCards();
        //Loop deck entry constructor over all CSV entries
        for (int i = 0; i < entries; i++) {
            //Initilize ith paramaters with imported values:
            index = getCSV.get(i)[0];
            text = getCSV.get(i)[1];
            type = getCSV.get(i)[2];
            typeParamater1 = getCSV.get(i)[3];
            typeParamater2 = getCSV.get(i)[4];
            //Construct new chance card and add to library.
            chanceCardDeck.add(index, text, type, typeParamater1, typeParamater2);
        }
        //Initilize new deck by shuffling copy of library
        ChanceCards.shuffleDeck();

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
    }

}
