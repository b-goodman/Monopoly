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
    private int jailBondsAvaliable;
    //Is player in jail?
    private boolean inJail;
    //ammont of consequtive doubles rolled in sinlge turn
    private int speedingCount;
    //number of turns spent in jail
    private int jailTimeSpent;

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
        this.jailBondsAvaliable = 0;
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
        this.jailBondsAvaliable = jailBondsAvaliable;
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
    public String getPositionType() {
        return Cells.get(getPosition()).getCellType();
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
        return jailBondsAvaliable;
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

    /**
     * Add(take) an amount of cash to(from) player via an increment(decrement)
     *
     * @param addAmmount Positive(negative) values will add to(remove from)
     * players account
     */
    public void addCash(int addAmmount) {
        cash += addAmmount;
    }

    /**
     * Add(take) a "Get out of jail free card" to(from) the player
     *
     * @param addAmmount Positive(negative) integer value will give to(remove
     * from) player
     */
    public void addJailBond(int addAmmount) {
        jailBondsAvaliable += addAmmount;
    }

    /**
     * Sets player as owner of specified Cell at location on game board
     *
     * @param propertyBoardLocation The location of the property in terms of its
     * position on the game board/index in LOCATIONS
     */
    public void setOwnership(Integer propertyBoardLocation) {
        (Cells.get(propertyBoardLocation)).setOwnership(getPlayerID());
    }

    /**
     * Returns cells type for specified location
     *
     * @param cellLocation Location of cell on game board
     * @return [String] type of cell ("property","railroad","utility"...)
     */
    public String getCellType(int cellLocation) {
        return Cells.LOCATIONS.get(cellLocation).getCellType();
    }

    /**
     * Sends the player to jail by setting the players inJail state to True and
     * positions the player to 0.
     */
    public void gotoJail() {
        setJailState(true);
        setPosition(0);
    }

    /**
     * Exits the player from jail; sets inJail state as False, repositions the
     * player back onto the game board and resets the jail time counter.
     */
    public void leaveJail() {
        setJailState(false);
        setPosition(11);
        setJailTimeSpent(0);

    }

    //end set
    /**
     * Returns board location of next specified cell type
     *
     * @param target [String] Type of cell to search for
     * @return [int] Board location of target cell
     */
    public int findNextCellType(String target) {
        int i = getPosition();
        String search = getCellType(i);
        while (!search.equals(target)) {
            i++;
            while (i > 40) {
                i -= 40;
            }
            search = getCellType(i);
        }
        return i;
    }

    public List drawChanceCard() {
        return ChanceCards.drawCard();
    }

    public List drawChestCard() {
        return ChestCards.drawCard();
    }

}

/*
* if(action == "drawCard"){
*   switch (type) {
*       case "chance" :
*           /drawChanceCard()
*           break;
*       case "chest" :
*           /drawChestCard()
*           break;
*   }
*
*
*

 */
