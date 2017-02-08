/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import monopoly.Enums.CellType;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import static monopoly.Enums.CellType.SPECIAL;
import static monopoly.Enums.CellType.UTILITY;

/**
 *
 * @author bgood_000
 */
public class Player {

    //Player ID
    private final Integer playerID;
    //Players name
    private final String name;
    //Design of player's token
    private final Token token;
    //Player's position by gameboard location index
    private int position;
    //Player's avaliable cash
    private int cash;
    //Ammount of "get out of jail free" cards avaliable to player
    private int jailBondsAvaliableChance, jailBondsAvaliableChest;
    private boolean exitingJail = false;
    //Is player in jail?
    private boolean inJail;
    //ammont of consequtive doubles rolled in sinlge turn
    private int speedingCount;
    //number of turns spent in jail
    private int jailTimeSpent;
    //players currently drawn card
    private List currentCard;
    //Players current dice roll

    /**
     * Constructor for default player. Cash, starting position and bonds
     * avaliable all set to default.
     *
     * @param playerID Unique integer to identify player and locate in static
     * PLAYERS map.
     * @param name String to identify players to user
     * @param token Token enum to visually locate player on game board
     */
    public Player(Integer playerID, String name, Token token) {
        this.playerID = playerID;
        this.name = name;
        this.token = token;
        this.position = 1;
        this.cash = 1500;
        this.jailBondsAvaliableChance = 0;
        this.jailBondsAvaliableChest = 0;
    }

    /**
     * Constructor for custom player. User may specify players starting cash,
     * position and amount of jail bonds avaliable.
     *
     * @param playerID Unique integer to identify player and locate in static
     * PLAYERS map.
     * @param name String to identify players to user
     * @param token Token enum to visually locate player on game board
     * @param position The starting location for the player. Position 1 by
     * default.
     * @param cash The starting cash amount for the player. 1500 by default
     * @param jailBondsAvaliable The amount of jail bonds avaliable to the
     * player. 0 by default.
     */
    public Player(Integer playerID, String name, Token token, int position, int cash, int jailBondsAvaliable) {
        this.playerID = playerID;
        this.name = name;
        this.token = token;
        this.position = position;
        this.cash = cash;
        //this.jailBondsAvaliableChance = jailBondsAvaliableChance;
        //this.jailBondsAvaliableChest = jailBondsAvaliableChest;
    }

//Get player's -
    /**
     * Returns the players unique integer identifier
     *
     * @return int - players ID
     */
    public Integer getPlayerID() {
        return playerID;
    }

    /**
     * Returns the players name
     *
     * @return String - player's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the players token (enum)
     *
     * @return enum - player's token
     */
    public Token getToken() {
        return token;
    }

    /**
     * Returns the position of the player in terms of its location on the game
     * board/index of LOCATIONS
     *
     * @return LOCATIONS key / board position
     */
    public int getPosition() {
        return position;
    }

    /**
     * Gets the type of cell which the player is currently occupying
     *
     * @return [string] Cell type currently occupied by player.
     */
    public CellType getPositionType() {
        return Cells.get(getPosition()).getCellType();
    }

    public String getActionType() {
        return Cells.get(getPosition()).getActionType();
    }

    public String getActionParamater() {
        return Cells.get(getPosition()).getActionParamater();
    }

    /**
     * Gets name of current position cell
     *
     * @return
     */
    public String getPositionName() {
        return Cells.get(getPosition()).getName();
    }

    /**
     * Gets name of specified cell
     *
     * @param cellPosition [int] specification for cell. Cells position on
     * board.
     * @return [String] Name of cell
     */
    public String getPositionName(int cellPosition) {
        return Cells.get(cellPosition).getName();
    }

    /**
     * Returns the integer value of cash avaliable to the player
     *
     * @return
     */
    public int getCash() {
        return cash;
    }

    /**
     * Return amount of "get out of jail free" card(s) avaliable to the player
     *
     * @return
     */
    public int getBonds() {
        return jailBondsAvaliableChance + jailBondsAvaliableChest;
    }

    /**
     * Is the player in jail? T/F
     *
     * @return True if player is in jail, False otherwise.
     */
    public boolean isInJail() {
        return inJail;
    }

    /**
     * How many consecutive turns has the player spent in jail
     *
     * @return [int] number of turns spent in current 'in jail' event.
     */
    public int getJailTimeSpent() {
        return jailTimeSpent;
    }

    /**
     * How many doubles has the player rolled so far this turn? Resets to 0 at
     * end of turn.
     *
     * @return [int] amount of doubles rolled on this current turn.
     */
    public int getSpeedingCount() {
        return speedingCount;
    }

    /**
     * Returns last card drawn by this player
     *
     * @return [List] Card last drawn by player.
     */
    public List getCurrentCard() {
        return currentCard;
    }

//Set players:
    /**
     * Sets the player as being in Jail.
     *
     * @param jailState True: in Jail, False otherwise.
     */
    public void setJailState(boolean jailState) {
        inJail = jailState;
    }

    /**
     * Sets the amount of time (in turns) the player has currently been in jail
     * for.
     *
     * @param time
     */
    public void setJailTimeSpent(int time) {
        jailTimeSpent = time;
    }

    /**
     * Set new position for player on game board
     *
     * @param newPosition Location on game board given as index of Cells
     * position in LOCATIONS
     */
    public void setPosition(int newPosition) {
//        if (newPosition < position && newPosition != 0) {
//            System.out.println("\t" + name + " passes GO - Collect " + Rules.getPassGoCredit());
//            playerCashRecieve(0, Rules.getPassGoCredit());
//        }
        position = newPosition;
    }

    /**
     * Overrides players cash amount with new value
     *
     * @param newCashAmmount New value of players cash
     */
    public void setCash(int newCashAmmount) {
        cash = newCashAmmount;
    }

    public void playerCashRecieve(Integer payingPlayerID, int cashAmount) {
        if (payingPlayerID != 0) {
            Player payingPlayer = Players.get(payingPlayerID);
            addCash(cashAmount);
            payingPlayer.addCash(-cashAmount);
            System.out.println("\t" + payingPlayer.getName() + " pays you " + cashAmount + " (Bal: " + getCash() + ")");
        } else {
            addCash(cashAmount);
            System.out.println("\t" + name + " recieves " + cashAmount + " (Bal: " + getCash() + ")");
        }
    }

    public void playerCashPay(Integer recievingPlayerID, int cashAmount) {
        if (recievingPlayerID != 0) {
            Player recievingPlayer = Players.get(recievingPlayerID);
            addCash(-cashAmount);
            recievingPlayer.addCash(cashAmount);
            System.out.println("\t" + name + " pays " + recievingPlayer.getName() + " " + cashAmount + " (Bal: " + getCash() + ")");
        } else {
            addCash(-cashAmount);
            System.out.println("\t" + name + " pays " + cashAmount + " (Bal: " + getCash() + ")");
        }
    }

    /**
     * Add(take) an amount of cash to(from) player via an increment(decrement)
     *
     * @param addAmmount Positive(negative) values will add to(remove from)
     * players account
     */
    public void addCash(int addAmmount) {
        cash += addAmmount;
    }

//    /**
//     * Add(take) a "Get out of jail free card" to(from) the player
//     *
//     * @param addAmmount Positive(negative) integer value will give to(remove
//     * from) player
//     */
//    public void addJailBond(int addAmmount) {
//        jailBondsAvaliable += addAmmount;
//    }
    /**
     * Sets player as owner of specified Cell at location on game board
     *
     * @param propertyBoardLocation The location of the property in terms of its
     * position on the game board/index in LOCATIONS
     */
    public void setOwnership(Integer propertyBoardLocation) {
        (Cells.get(propertyBoardLocation)).setOwnership(getPlayerID());
    }

    public List getOwnership() {
        List<Cell> playerOwnershipList = new ArrayList<>();
        Cells.getPlayerOwnership().keySet().stream().filter((propertyName) -> ((int) Cells.getPlayerOwnership().get(propertyName) == 1)).forEach((propertyName) -> {
            playerOwnershipList.add((Cell) propertyName);
        });
        return playerOwnershipList;
    }

    public List getCompleteSets() {
        List<Cell> completeProperties = new ArrayList<>();
        Iterator<Cell> test = getOwnership().iterator();
        while (test.hasNext()) {
            Cell cand = test.next();
            if (cand.isSetComplete()) {
                completeProperties.add(cand);
            }
        }
        return completeProperties;
    }

    public List getPropertyGroupMembers(char groupID) {
        List<Cell> groupMembers = new ArrayList<>();
        for (int i = 1; i <= Cells.locationsAmount(); i++) {
            if (Cells.get(i).getPropertyGroupID() == groupID) {
                groupMembers.add(Cells.get(i));
            }
        }
        return groupMembers;
    }

    public Set getCompleteSetID() {
        List<Character> propertyIDs = new ArrayList<>();
        for (Object q : getCompleteSets()) {
            Cell temp = (Cell) q;
            propertyIDs.add(temp.getPropertyGroupID());
        }
        return new HashSet<>(propertyIDs);
    }

    public void addPropertyImprovementByGroup(char groupID) {
        List q = getPropertyGroupMembers(groupID);
        for (int i = 0; i < q.size(); i++) {
            ((Cell) q.get(i)).addImprovement();
        }
    }

    public void mortgageProperty(Integer propertyID) {
        Cells.get(propertyID).mortgageProperty();
    }

    public void unMortgageProperty(Integer propertyID) {
        Cells.get(propertyID).unmortgageProperty();
    }

    /**
     * Returns cells type for specified location
     *
     * @param cellLocation Location of cell on game board
     * @return [String] type of cell ("property","railroad","utility"...)
     */
    public CellType getCellType(int cellLocation) {
        return Cells.LOCATIONS.get(cellLocation).getCellType();
    }

    /**
     * Sends the player to jail by setting the players inJail state to True and
     * positions the player to 0.
     */
    public void gotoJail() {
        inJail = true;
        position = 0;
    }

    /**
     * Exits the player from jail; sets inJail state as False, repositions the
     * player back onto the game board and resets the jail time counter.
     */
    public void leaveJail() {
        inJail = false;
        position = 11;
        jailTimeSpent = 0;
        exitingJail = true;

    }

    public boolean isPlayerExitingJail() {
        return exitingJail;
    }

    //end set
    /**
     * Returns board location of next specified cell type
     *
     * @param target [String] Type of cell to search for
     * @return [int] Board location of target cell
     */
    public int findNextCellType(CellType target) {
        int i = getPosition();
        CellType search = getCellType(i);
        while (!search.equals(target)) {
            i++;
            while (i > 40) {
                i -= 40;
            }
            search = getCellType(i);
        }
        return i;
    }

    public int findNextCellType(String target) {
        int i = getPosition();
        CellType search = getCellType(i);
        while (!search.equals(CellType.valueOf(target))) {
            i++;
            while (i > 40) {
                i -= 40;
            }
            search = getCellType(i);
        }
        return i;
    }

    /**
     * Calling this method begins the player's turn.
     */
    public void beginTurn() {
        //speedingCount = 0;
        System.out.println("Player " + playerID + " - " + name + " begins turn on " + getPositionName() + ":");
        Dice.clearRoll();
        // player rolls dice and reads value
        Dice.roll();
        int steps = Dice.getRollSum();

        if (isInJail() && jailTimeSpent < Rules.getMaxJailTerm()) {
            //Decide if best to leave jail early or take default method (roll dice)
            //Take dice roll expectation - Dice.getExpectationRoll() -

            //Leave jail early:
            //1: use card if avaliable
            //            if (jailBondsAvaliableChance > 0) {
            //                ChanceCards.reinsertJailBond();
            //                jailBondsAvaliableChance--;
            //            } else if (jailBondsAvaliableChest > 0) {
            //                ChestCards.reinsertJailBond();
            //                jailBondsAvaliableChest--;
            //            }
            //2: pay fee (default: 50)
            //Default action: Roll dice.  If doubles, advance token by thown amount.  Do not roll again.
            if (Dice.isDouble()) {
                System.out.println("\t" + name + " rolls doubles " + Dice.getFaceValues() + " and gets to leave jail early!");
                leaveJail();
                advanceToken(steps);
            } else {
                //else, take another turn in jail
                jailTimeSpent++;
                System.out.println("\t" + name + " fails to roll doubles " + Dice.getFaceValues() + " and spends another turn in jail (turns until release: " + (Rules.getMaxJailTerm() - jailTimeSpent) + ")");
                //endTurn();
            }
            //Player still in jail for maximum duration
        } else if (isInJail() && jailTimeSpent == Rules.getMaxJailTerm()) {
            //Player gets last chance to roll dice
            if (Dice.isDouble()) {
                System.out.println("\t" + name + " rolls doubles " + Dice.getFaceValues() + " and gets to leave jail early!");
                leaveJail();
                advanceToken(steps);
                //if unsucessful, player must pay fine and leave.
            } else {
                System.out.println("\t" + name + " has failed to roll doubles " + Dice.getFaceValues() + " and has thus served the maximum jail term.");
                //check if player can afford fine -
                //if(cash>=Rules.getJailLeaveFee()) -- do below
                System.out.println("\t" + name + " pays the fine");
                playerCashPay(0, Rules.getJailLeaveFee());
                leaveJail();
                advanceToken(steps);
                //else - raise funds >= Rules.getJailLeaveFee()
            }
            //Player is not in jail:
        } else {
            // check if player rolls doubles; if so, and if speeding rule is enabled, increment speed counter
            System.out.println("\t" + name + " rolls " + steps + " " + Dice.getFaceValues());
            if (Dice.isDouble()) {
                speedingCount++;
                System.out.println("\t" + name + " rolls doubles! (" + speedingCount + ")");
            }
            // check if players speed counter has reached limit (default 3); if so, send to jail.
            if (speedingCount == Rules.getDoublesSpeedingLimit()) {
                System.out.println("\t" + name + " sent to jail for speeding");
                gotoJail();
                // otherwise, proceed with turn
            } else {
                //advance token
                advanceToken(steps);
            }
        }
    }

    public void midTurn() {
        if (getPositionType() == SPECIAL) {
            String type = getActionType();
            String para = getActionParamater();
            switch (type) {
                //Draw a card
                case "drawCard":
                    switch (para) {
                        // Draw Chance card
                        case "chance":
                            drawChanceCard();
                            break;
                        //Draw chest card
                        case "chest":
                            drawChanceCard();
                            break;
                    }
                    // Actions for post card draw.  Print type of card drawn, parse drawn card action.
                    System.out.println("\t" + name + " draws a " + para + " card: " + readCurrentCard().get(1));
                    parseCardAction(readCurrentCard());
                    break;
                //Transition to new fixed location
                case "transitionAbs":
                    // The player is being sent to jail (lands on goto jail cell)
                    if ("0".equals(para)) {
                        gotoJail();
                    } else {
                        // Player has landed on some other transitional cell
                        advanceToken(Integer.parseInt(para));
                    }
                    break;
                //Recieve money
                case "creditAbs":
                    playerCashRecieve(0, Integer.parseInt(para));
                    break;
                //Pay money
                case "debitAbs":
                    //If the free parking bonus rule is being enforced
                    if (Rules.isFreeParkingBonusEnabled()) {
                        //pay the money into the free parking fund
                        Rules.incFreeParkingBonusValue(Integer.parseInt(para));
                    } else {
                        //else, pay the bank
                        playerCashPay(0, Integer.parseInt(para));
                    }
                    break;
                //Potentially do nothing.  Check rules.
                case "parking":
                    if (Rules.isFreeParkingBonusEnabled()) {
                        //get current amount of bonus cash
                        //pay amount to player.
                        playerCashRecieve(0, Rules.getFreeParkingBonusValue());
                        //Clear bonus - set to 0
                        Rules.clearFreeParkingBonus();
                    }
                    //else, do nothing.
                    break;
            }
        } else {
            Cell occupiedCell = Cells.get((Integer) getPosition());
            //can cell be owned? if so..
            if (occupiedCell.getOwnable()) {
                //is it currently unowned? If so, purchace property
                if (occupiedCell.getOwnership() == null) {
                    cash -= occupiedCell.getBaseValue();
                    occupiedCell.setOwnership(getPlayerID());
                    System.out.println("\t" + name + " buys " + occupiedCell.getName() + " for " + occupiedCell.getBaseValue() + " (Bal: " + getCash() + ")");
                    //If it is owned but by the current player, then do nothing
                } else if (Objects.equals(occupiedCell.getOwnership(), getPlayerID())) {
                    //It is owned and by another player, then pay rent
                } else if (getPositionType() == UTILITY) {
                    playerCashPay(occupiedCell.getOwnership(), occupiedCell.getRent(Dice.getRollSum()));
                } else {
                    playerCashPay(occupiedCell.getOwnership(), occupiedCell.getRent());
                }
            }
        }

    }

    /**
     * ends players turn - resets speeding counter
     */
    public void endTurn() {
        System.out.println("\t" + name + " ends turn on " + getPositionName());
        speedingCount = 0;
        exitingJail = false;
    }

    /**
     * Move player +- N steps. Rolls over if position exceeds 40.
     *
     * @param steps [int] Amount of steps player takes. If negative, player will
     * move backwards.
     */
    public void advanceToken(int steps) {
        //advance token
        position += steps;
        // get relative (to GO) postion - subtract 40 if abs. positon >40 (i.e., player circumvents the board by passing go)
        if (position > Cells.locationsAmount()) {
            //Player has circumvented the board
            position -= Cells.locationsAmount();
            //has the player passed or landed on GO
            if (position != 1) {
                //The player has passed GO
                System.out.println("\t" + name + " passes GO - Collect " + Rules.getPassGoCredit());
                playerCashRecieve(0, Rules.getPassGoCredit());
            }
        } else if (position < 1) {
            position += Cells.locationsAmount();
        }
        int positionInfoCost = Cells.get(position).getBaseValue();
        Integer positionInfoOwnership = Cells.get(position).getOwnership();
        int positionInfocurrentRent = (getPositionType() == UTILITY) ? (Cells.get(position)).getRent(Dice.getRollSum()) : Cells.get(position).getRent();

        if (Cells.get(position).getOwnable()) {
            if (positionInfoOwnership != null) {
                System.out.println("\t" + name + " moves " + steps + " steps and lands on " + getPositionName() + " - Owned by: " + Players.get(positionInfoOwnership).getName() + ", Rent: " + positionInfocurrentRent);
            } else {
                System.out.println("\t" + name + " moves " + steps + " steps and lands on " + getPositionName() + " - Avaliable to purchace for " + positionInfoCost);
            }
        } else {
            System.out.println("\t" + name + " moves " + steps + " steps and lands on " + getPositionName());
        }
    }

    public List drawChanceCard() {
        List newCard = ChanceCards.drawCard();
        currentCard = newCard;
        return newCard;
    }

    public List drawChestCard() {
        List newCard = ChestCards.drawCard();
        currentCard = newCard;
        return newCard;
    }

    /**
     * Returns last card drawn by player. Returns null if player has not yet
     * drawn a card.
     *
     * @return [List] Last card drawn by player.
     */
    public List readCurrentCard() {
        return currentCard;
    }

    public void parseCardAction(List card) {
        String cardType = (String) card.get(2);
        String cardAction1 = (String) card.get(3);
        String cardAction2 = (String) card.get(4);

        // System.out.println("Parsing Card..");
        switch (cardType) {
            // cases of players transition to fixed, absolute location
            case "TRANSITION_ABS":
                System.out.println("\t" + name + " moves to " + getPositionName(Integer.parseInt(cardAction1)));
                setPosition(Integer.parseInt(cardAction1));
                midTurn();
                return;
            // cases of players transition dependent on current location
            case "TRANSITION_REL":
                switch (cardAction1) {
                    // player advance to next property type (rail, util)
                    case "NEXT":
                        System.out.println("\t" + name + " moves to next " + cardAction2 + " type location (" + getPositionName(findNextCellType(cardAction2)) + ")");
                        setPosition(findNextCellType(cardAction2));
                        midTurn();
                        return;
                    // player advance N spaces from current position
                    case "GO":
                        System.out.println("\t" + name + " adjusts " + cardAction2 + " spaces");
                        advanceToken(Integer.parseInt(cardAction2));
                        midTurn();
                        return;
                }
                return;
            // player recieves jail card
            case "JAIL":
                switch (cardAction1) {
                    case "IN":
                        //player sent to jail
                        System.out.println("\t" + name + " is sent to jail");
                        gotoJail();
                        return;

                    case "OUT":
                        //player gets out of jail free
                        return;
                }
                return;
            // cases of player recieving fixed sum of cash
            case "CREDIT_ABS":
                playerCashRecieve(0, Integer.parseInt(cardAction1));
                return;
            // cases of player recieving variable ammount of cash dependent on current game params.
            case "CREDIT_REL":
                return;
            // cases of player paying fixed ammount of cash
            case "DEBIT_ABS":
                //If the free parking bonus rule is being enforced
                if (Rules.isFreeParkingBonusEnabled()) {
                    //pay the money into the free parking fund
                    Rules.incFreeParkingBonusValue(Integer.parseInt(cardAction1));
                } else {
                    //else, pay the bank
                    playerCashPay(0, Integer.parseInt(cardAction1));
                }
                return;
            // player paying variable ammount of cash
            case "DEBIT_REL":
                switch (cardAction1) {
                    case "PAY_EACH":
                        //pay each player cardAction2
                        for (Integer i = 1; i <= Players.amount(); i++) {
                            if (Objects.equals(i, playerID)) {
                                //do nothing
                            } else {
                                playerCashPay(i, Integer.parseInt(cardAction2));
                            }
                        }
                    // return;
                }
            //return;
        }
    }

}
