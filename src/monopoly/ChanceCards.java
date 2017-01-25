/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author bgood_000
 */
public class ChanceCards {

    private static final List<Card> CHANCE_CARD_LIB = new ArrayList<>();
    private static final ArrayDeque<Card> CHANCE_CARD_DECK = new ArrayDeque<>();
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

    /**
     * Read the next card without removing it
     *
     * @return [Card] Next Card obj. in deque
     */
    public static Card getNextCard() {
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
        return CHANCE_CARD_DECK.pollFirst().getCardContent();
    }

}
