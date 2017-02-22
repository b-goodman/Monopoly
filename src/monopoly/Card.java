/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author bgood_000
 */
public class Card {
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
