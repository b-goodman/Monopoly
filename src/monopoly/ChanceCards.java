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
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author bgood_000
 */
public final class ChanceCards {

    private static final List<Card> CHANCE_CARD_LIB = new ArrayList<>();
    private static final ArrayDeque<Card> CHANCE_CARD_DECK = new ArrayDeque<>();
    private static final ArrayDeque<Card> JAIL_BONDS = new ArrayDeque<>();
//Individual card defined as class

    class Card {
        //Unique identifier for card

        public String cardID;
        //Textual content of card; what player reads
        public String cardContent;
        //Type of action the card requires
        public String actionType;
        //Details action paramaters
        public String actionPrimary;
        //Details second (if applicable) action paramater
        public String actionSecondary;
        //Constructs default card

        public Card(
                String cardID,
                String cardContent,
                String actionType,
                String actionPrimary,
                String actionSecondary
        ) {
            this.cardID = cardID;
            this.cardContent = cardContent;
            this.actionType = actionType;
            this.actionPrimary = actionPrimary;
            this.actionSecondary = actionSecondary;
        }

        public List getCardContent() {
            List<String> cardReturn = Arrays.asList(cardID, cardContent, actionType, actionPrimary, actionSecondary);
            return cardReturn;
        }
    }

    public ChanceCards(String filePath) throws FileNotFoundException, IOException {
        //Chance Card Import Testing:
        //Instantiate new CSV reader with specified filepath
        CSVReader reader = new CSVReader(new FileReader(filePath));//TODO - change to relative FP.
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

        //Loop deck entry constructor over all CSV entries
        for (int i = 0; i < entries; i++) {
            //Initilize ith paramaters with imported values:
            index = getCSV.get(i)[0];
            text = getCSV.get(i)[1];
            type = getCSV.get(i)[2];
            typeParamater1 = getCSV.get(i)[3];
            typeParamater2 = getCSV.get(i)[4];
            //Construct new chance card and add to library.
            add(index, text, type, typeParamater1, typeParamater2);
        }
        //Initilize new deck by shuffling copy of library
        shuffleDeck();
    }

    /**
     * initialises a new library and deck of chance cards according to the
     * default specification.
     *
     * @throws IOException
     */
    public static void init() throws IOException {
        ChanceCards chanceCards = new ChanceCards("chanceCardDeck.CSV");
    }

    public static void init(String configPath) throws IOException {
        ChanceCards chanceCards = new ChanceCards(configPath);
    }

    /**
     * Create new Card and add to library
     *
     * @param cardID [String] Unique identifier for card
     * @param cardContent [String] Text viewed by user when reading card
     * @param actionType [String] Defines type of action card performs
     * @param actionPrimary [String] Parameterises action of card
     * @param actionSecondary [String] Provides further instructions of cards
     * action (if applicable)
     */
    public void add(
            String cardID,
            String cardContent,
            String actionType,
            String actionPrimary,
            String actionSecondary
    ) {
        CHANCE_CARD_LIB.add(new Card(cardID, cardContent, actionType, actionPrimary, actionSecondary));
    }

    /**
     * Initialises a new deque by shuffling List CARD_LIB and copying to Deque
     * CARD_DECK
     */
    public static void shuffleDeck() {
        Collections.shuffle(CHANCE_CARD_LIB);
        for (int i = 0; i < CHANCE_CARD_LIB.size(); i++) {
            CHANCE_CARD_DECK.add(CHANCE_CARD_LIB.get(i));
        }
    }

    //
    /**
     * Read the next card without removing it
     *
     * @return [Card] Next Card obj. in deque
     */
    private static Card getNextCard() {
        return CHANCE_CARD_DECK.peekFirst();
    }

    /**
     * Reads current card (without removal) and returns text content
     *
     * @return [List] Next Card obj. parsed as list (printable) in deque
     */
    public List readNextCard() {
        return getNextCard().getCardContent();
    }

    /**
     * Returns next card parsed as List in deque with removal. Init. new deque
     * and returns first card if last card was drawn prev.
     *
     * @return [List] New random (w/o replacement)card from deque.
     */
    public static List drawCard() {
        if (getNextCard() == null) {
            shuffleDeck();
        }
        //Removal of "Get out of jail free cards" from deck
        //on drawing card
        Card drawnCard = CHANCE_CARD_DECK.pollFirst();
        List drawnCardContent = drawnCard.getCardContent();
        //check if type JAIL with action OUT
        if ("JAIL".equals(drawnCardContent.get(2)) && "OUT".equals(drawnCardContent.get(3))) {
            //if so, remove from CHANCE_CARD_LIB, add to JAIL_BONDS
            JAIL_BONDS.add(drawnCard);
            CHANCE_CARD_LIB.remove(drawnCard);
        }
        return drawnCardContent;
    }

    public static void reinsertJailBond() {
        //Polls form bail holding deque, appends result to current deck.
        CHANCE_CARD_DECK.add(JAIL_BONDS.pollFirst());
    }

}
