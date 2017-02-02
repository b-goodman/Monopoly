/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import monopoly.Enums.CellType;
import java.util.*;

/**
 *
 * @author bgood_000
 */
public class Cell {

    public String name;
    public CellType cellType;
    public String color;
    public boolean isOwnable;
    public char groupID;
    public Integer currentOwner;
    public int baseValue;
    public int mortgageValue;
    public boolean mortgageState;
    public int improvemntState;
    public static Map<String, Integer> PLAYER_OWNERSHIP = new HashMap<>();
    public static Map<String, Character> PROPERTY_ID_BY_NAME = new HashMap<>();
    public int houseValue;
    public int hotelValue;
    public int rentBase;
    public int rent1H;
    public int rent2H;
    public int rent3H;
    public int rent4H;
    public int rentHotel;
    public int houseCount;
    public int hotelCount;
    public int rent2R;
    public int rent3R;
    public int rent4R;
    public int oneUtilityMult;
    public int twoUtilityMult;
    public String actionType;
    public String actionParamater;

    /**
     * Constructs a property type cell
     *
     * @param name Cell/Location name
     * @param color Cell/Location color
     * @param groupID Defines a property group for improvements - Cells with
     * equal groupIDs are part of the same group
     * @param baseValue The purchase price player must make when buying this
     * property from bank
     * @param mortgageValue The cash value returned to player when property is
     * mortgaged
     * @param houseValue The cash needed to add one house onto this property
     * @param hotelValue The cash needed to add one hotel onto this property
     * @param rentBase The rent collected for this property if it is unimproved
     * and is not part of a complete set
     * @param rent1H Rent for 1 house
     * @param rent2H Rent for 2 houses
     * @param rent3H Rent for 3 houses
     * @param rent4H Rent for 4 houses
     * @param rentHotel Rent for 1 hotel
     */
    public Cell(
            String name,
            String color,
            char groupID,
            int baseValue,
            int mortgageValue,
            int houseValue,
            int hotelValue,
            int rentBase,
            int rent1H,
            int rent2H,
            int rent3H,
            int rent4H,
            int rentHotel) {
        this.name = name;
        this.cellType = CellType.PROPERTY;
        this.color = color;
        this.groupID = groupID;
        this.isOwnable = true;
        this.baseValue = baseValue;
        this.mortgageValue = mortgageValue;
        this.mortgageState = false;
        this.improvemntState = 0;
        this.houseValue = houseValue;
        this.rentBase = rentBase;
        this.rent1H = rent1H;
        this.rent2H = rent2H;
        this.rent3H = rent3H;
        this.rent4H = rent4H;
        this.rentHotel = rentHotel;
        this.houseCount = 0;
        this.hotelCount = 0;
        this.currentOwner = null;
        PROPERTY_ID_BY_NAME.put(name, groupID);
    }

    /**
     * Constructor for railroad type cell
     *
     * @param name Cell name
     * @param color Cell colour
     * @param baseValue The purchase price player must make when buying this
     * railroad from bank
     * @param mortgageValue The cash value returned to player when this railroad
     * is mortgaged
     * @param rentBase The rent the owning player receives when only this
     * railroad is owned
     * @param rent2R The rent the owning player receives when this railroad plus
     * one other is owned
     * @param rent3R The rent the owning player receives when this railroad plus
     * two others are owned
     * @param rent4R The rent the owning player receives when this player owns
     * all railroads
     */
    public Cell(
            String name,
            String color,
            int baseValue,
            int mortgageValue,
            int rentBase,
            int rent2R,
            int rent3R,
            int rent4R
    ) {
        this.name = name;
        this.cellType = CellType.RAILROAD;
        this.color = color;
        this.isOwnable = true;
        this.baseValue = baseValue;
        this.mortgageValue = mortgageValue;
        this.mortgageState = false;
        this.improvemntState = 0;
        this.rentBase = rentBase;
        this.rent2R = rent2R;
        this.rent3R = rent3R;
        this.rent4R = rent4R;
        this.currentOwner = null;
        this.groupID = 'r';
        PROPERTY_ID_BY_NAME.put(name, groupID);
    }

    /**
     * Constructor for utility type cell
     *
     * @param name Cell name
     * @param color Cell colour
     * @param baseValue The purchase price player must make when buying this
     * utility from bank
     * @param mortgageValue The cash value returned to player when this utility
     * is mortgaged
     * @param oneUtilityMult Calculates rent by multiplying this by the current
     * dice value. The owning player receives this when only this utility is
     * owned.
     * @param twoUtilityMult Calculates rent by multiplying this by the current
     * dice value. The owning player receives this when both utilities are
     * owned.
     */
    public Cell(
            String name,
            String color,
            int baseValue,
            int mortgageValue,
            int oneUtilityMult,
            int twoUtilityMult
    ) {
        this.name = name;
        this.cellType = CellType.UTILITY;
        this.color = color;
        this.isOwnable = true;
        this.baseValue = baseValue;
        this.mortgageValue = mortgageValue;
        this.mortgageState = false;
        this.oneUtilityMult = oneUtilityMult;
        this.twoUtilityMult = twoUtilityMult;
        this.groupID = 'u';
        this.currentOwner = null;
        PROPERTY_ID_BY_NAME.put(name, groupID);
    }

    /**
     * Constructor for special type cell
     *
     * @param name Cell name
     * @param color Cell colour
     * @param actionType Classifies the action the player mist take when landing
     * on this location.
     * @param actionParamater Gives specific behaviour to action.
     */
    public Cell(
            String name,
            String color,
            String actionType,
            String actionParamater
    ) {
        this.name = name;
        this.cellType = CellType.SPECIAL;
        this.color = color;
        this.isOwnable = false;
        this.groupID = 's';
        this.actionType = actionType;
        this.actionParamater = actionParamater;
        PROPERTY_ID_BY_NAME.put(name, groupID);
    }

    /**
     * Constructor for jail cell
     *
     * @param name Cell name
     * @param color Cell color
     */
    public Cell(
            String name,
            String color
    ) {
        this.name = name;
        this.cellType = CellType.JAIL;
        this.color = color;
        this.isOwnable = false;
        this.groupID = 'j';
        PROPERTY_ID_BY_NAME.put(name, groupID);
    }

//All cells:
    /**
     * Gets name of Cell
     *
     * @return name of this cell
     */
    public String getName() {
        return name;
    }

    /**
     * Gets color of Cell.
     *
     * @return color of this cell
     */
    public String getColor() {
        return color;
    }

    /**
     * States if this Cell can be owned by a player.
     *
     * @return True if Cell can be owned by a player, False otherwise
     */
    public boolean getOwnable() {
        return isOwnable;
    }

    public int getBaseValue() {
        return baseValue;
    }

    /**
     * Gets type of Cell. Type describes nature and enables different possible
     * method behaviours.
     *
     * @return String describing type of cell ("property", "railroad",
     * "utility", "jail", "special")
     */
    public CellType getCellType() {
        return cellType;
    }

//Special Cells:
    public String getActionType() {
        return actionType;
    }

    public String getActionParamater() {
        return actionParamater;
    }

//Ownable cells:
    /**
     * Defines the ownership of this Cell. Sets the Cells ownership field to its
     * new value and adds/overwrites static PLAYER_OWNERSHIP field with Cells
     * name mapped to player ID
     *
     * @param newOwnerID the unique identifier for the owning player
     */
    public void setOwnership(Integer newOwnerID) {
        currentOwner = newOwnerID;
        PLAYER_OWNERSHIP.put(name, newOwnerID);
    }

    /**
     * Returns the unique ID of the player who owns this Cell.
     *
     * @return owning player ID, null if this Cell is not owned
     */
    public Integer getOwnership() {
        return currentOwner;
    }

    /**
     * States if the Cell is currently mortgaged
     *
     * @return True if this Cell is mortgaged (mortgageState = true), False
     * otherwise
     */
    public boolean isMortgaged() {
        return mortgageState;
    }

    /**
     * Designates Cell as being (un)mortgaged. Does not implement higher rules
     * of game
     *
     * @param set true if property is to be mortgaged, false if property is
     * being un-mortgaged
     */
    public void setMortgageState(boolean set) {
        mortgageState = set;
    }

    /**
     * Checks if Cell can be (un)mortgaged
     *
     * @param action The mortgage action to be checked for. Either "mortgage" or
     * "unmortgage".
     * @return True if stated action type is valid under current rules and
     * property state. False otherwise
     */
    public boolean isMortgageActionValid(String action) {
        boolean returnStatement = false;
        switch (action) {
            //check if cell can be mortgaged
            case "mortgage":
                //check if cell is ownable; if not then cannot be mortgaged
                if (!getOwnable()) {
                    System.out.println("This type of cell (" + getCellType() + ") is not ownable and so cannot be mortgaged");
                } else if (isMortgaged()) {
                    //Property type is ownable
                    //check if property is already mortgaged; if so then cannot be mortgaged
                    System.out.println("This property is already mortgaged");
                } else if (getImprovmentState() > 0) {
                    //Property is ownable and is not currently mortgaged.
                    //Check if property is improved; if so then cannot be mortgaged
                    System.out.println("Improved properties cannot be mortgaged");
                } else {
                    //Property is ownable, unmortgaged and unimproved and thus may be mortgaged
                    returnStatement = true;
                }
                break;
            case "unmortgage":
                //Check if cell is already un-mortgaged
                if (!isMortgaged()) {
                    //If property is not mortgaged then it cannot be unmortgaged.
                    System.out.println("This property is not mortgaged");
                }
                break;
        }
        return returnStatement;
    }

    /**
     * Mortgages Cell with additional checks
     */
    public void mortgageProperty() {
        if (isMortgageActionValid("mortgage")) {
            setMortgageState(true);
        }
    }

    /**
     * Un-mortgages Cell after checking
     */
    public void unmortgageProperty() {
        if (isMortgageActionValid("unmortgage")) {
            setMortgageState(false);
        }
    }

    /**
     * Returns PROPERTY_ID_BY_NAME; a static Map between ownable Cells (K,
     * String) and their group IDs (V, Character). Used to track which
     * property-types are members of which groups for determining improvements
     *
     * @return PROPERTY_ID_BY_NAME
     */
    public Map<String, Character> getPropertyIDbyName() {
        return PROPERTY_ID_BY_NAME;
    }

    /**
     * Takes keys of PLAYER_OWNERSHIP which have corresponding values equal to
     * the ID of the player who owns this property.
     *
     * @return List of all properties (including this one) owned by the player
     * who owns this property
     */
    public ArrayList getOwningPlayerOwnership() {
        ArrayList<String> returnList;
        returnList = new ArrayList<>();
        PLAYER_OWNERSHIP.keySet().stream().filter((o) -> (PLAYER_OWNERSHIP.get(o).equals(currentOwner))).forEach((String o) -> {
            returnList.add((String) o);
        });
        return returnList;
    }

    /**
     * Returns this Cells property group ID character
     *
     * @return this Cells property group ID character
     */
    public char getPropertyGroupID() {
        return groupID;
    }

    /**
     * Returns list of group IDs of properties owned by player who owns this
     * Cell
     *
     * @return list of group IDs of properties owned by player who owns this
     * Cell
     */
    public ArrayList getOwningPlayerGroupID() {
        ArrayList<Character> returnList;
        returnList = new ArrayList<>();
        for (int i = 0; i < getOwningPlayerOwnership().size(); i++) {
            returnList.add(getPropertyIDbyName().get(getOwningPlayerOwnership().get(i)));
        }
        return returnList;
    }
    //get number of properties owned by player which have same group IDs

    /**
     * Returns the amount of properties in this Cells group owned by the player
     * who owns this Cell
     *
     * @return Returns the amount of properties in this Cells group owned by the
     * player who owns this Cell
     */
    public int getOwningPlayerGroupFrequency() {
        return Collections.frequency(getOwningPlayerGroupID(), getPropertyGroupID());
    }

    /**
     * Returns the number of properties in the group that this Cell is a member
     * of
     *
     * @return Returns the number of properties in the group that this Cell is a
     * member of
     */
    public int getGroupFrequency() {
        return Collections.frequency(getPropertyIDbyName().values(), getPropertyGroupID());
    }

    /**
     * Returns current rent for this Cell (property or railroad)
     *
     * @return current rent value
     */
    public int getRent() {
        int returnCase = 0;
        if (isMortgaged()) {
            System.out.println("Rent cannot be collected on mortgaged properties");
        } else {
            switch (getCellType()) {
                case PROPERTY:
                    switch (getImprovmentState()) {
                        case 0:
                            if (isSetComplete()) {
                                returnCase = rentBase * 2;
                            } else {
                                returnCase = rentBase;
                            }
                            break;
                        case 1:
                            returnCase = rent1H;
                            break;
                        case 2:
                            returnCase = rent2H;
                            break;
                        case 3:
                            returnCase = rent3H;
                            break;
                        case 4:
                            returnCase = rent4H;
                            break;
                        case 5:
                            returnCase = rentHotel;
                            break;
                    }
                    break;
                case RAILROAD:
                    switch (getOwningPlayerGroupFrequency()) {
                        case 1:
                            returnCase = rentBase;
                            break;
                        case 2:
                            returnCase = rent2R;
                            break;
                        case 3:
                            returnCase = rent3R;
                            break;
                        case 4:
                            returnCase = rent4R;
                            break;
                    }
                    break;
            }
        }
        return returnCase;
    }

    /**
     * Returns the rent for this Cell (utility) Multiplies current dice roll by
     * value determined by players utility ownership.
     *
     * @param diceValue The total value of the latest dice roll (sum of all
     * faces)
     * @return current rent value
     */
    public int getRent(int diceValue) {
        int returnCase = 0;
        switch (getOwningPlayerGroupFrequency()) {
            case 1:
                returnCase = (oneUtilityMult * diceValue);
                break;
            case 2:
                returnCase = (twoUtilityMult * diceValue);
                break;
        }
        return returnCase;
    }

    /**
     * Returns specific rent value based on Cell type (property, railroad,
     * utility) and condition (1 house, 2 houses,...,1 rail, 2 rails... etc )
     *
     * @param field rent condition ("rentBase","rent1H",.., )
     * @return
     */
    public int getRent(String field) {
        int returnCase = 0;
        switch (getCellType()) {
            case PROPERTY:
                switch (field) {
                    case "Base":
                        if (isSetComplete()) {
                            returnCase = 2 * rentBase;
                            break;
                        } else {
                            returnCase = rentBase;
                            break;
                        }
                    //break;
                    case "1H":
                        returnCase = rent1H;
                        break;
                    //break;
                    case "2H":
                        returnCase = rent2H;
                        break;
                    //break;
                    case "3H":
                        returnCase = rent3H;
                        break;
                    //break;
                    case "4H":
                        returnCase = rent4H;
                        break;
                    //	break;
                    case "Hotel":
                        returnCase = rentHotel;
                    //	break;
                }
                break;
            case RAILROAD:
                switch (field) {
                    case "Base":
                        returnCase = rentBase;
                        break;
                    //break;
                    case "2R":
                        returnCase = rent2R;
                        break;
                    //break;
                    case "3R":
                        returnCase = rent3R;
                        break;
                    //break;
                    case "4R":
                        returnCase = rent4R;
                        break;
                    //break;
                }
                break;
            case UTILITY:
                switch (getOwningPlayerGroupFrequency()) {
                    case 1:
                        returnCase = oneUtilityMult;
                        break;
                    //break;
                    case 2:
                        returnCase = twoUtilityMult;
                        break;
                    //break;
                }
                break;
        }
        return returnCase;
    }

//Property types only:
    /**
     * Returns state of property Cells improvement 0 - unimproved 1 - 1 House
     * .... 5 - Hotel
     *
     * @return integer relating current state of property improvement
     */
    public int getImprovmentState() {
        return improvemntState;
    }

    /**
     * Checks if property is part of a complete, sole player owned set Used to
     * determine validity for buying improvements and current rent
     *
     * @return True if property is part of complete set, false otherwise
     */
    public boolean isSetComplete() {
        return (getOwningPlayerGroupFrequency() == getGroupFrequency());
    }

    /**
     * Checks if property can support an improvement
     *
     * @return True if rules allow property to be improved, false otherwise
     */
    public boolean addImprovementsValid() {
        boolean isValid = false;
        //Check if player owns complete set
        if (!isSetComplete()) {
            //Return message is set uncomplete
            System.out.println("Cannot improve properties belonging to incomplete sets");
            //check if property is of correct type
        } else if (getCellType() != CellType.PROPERTY) {
            System.out.println("This cell type (" + getCellType() + ") cannot be improved");
            //check if property is mortgaged
        } else if (isMortgaged()) {
            // if is mortgaged, throw exception
            System.out.println("Cannot improve mortgaged property");
            //otherwise, proceed to check if property improvment limits are reached
        } else if (getImprovmentState() == 5) {
            System.out.println("Cannot improve property beyond 1 Hotel");
        } else {
            isValid = true;
        }
        return isValid;
    }

    /**
     * Adds 1 improvement to property if allowed by rules. Determines next
     * improvement type and updates Cell field (house/hotel count, improvement
     * level) accordingly
     */
    public void addImprovement() {
        if (addImprovementsValid() && getImprovmentState() < 4) {
            houseCount++;
            improvemntState++;
        } else if (addImprovementsValid() && getImprovmentState() == 4) {
            houseCount = 0;
            hotelCount = 1;
            improvemntState++;
        }
    }

    /**
     * Determines if property can have an improvement (House/Hotel) removed from
     * it
     *
     * @return True if property improvement can be downgraded, False otherwise
     */
    public boolean removeImprovementsValid() {
        boolean isValid = false;
        if (getImprovmentState() == 0) {
            System.out.println("Cannot remove improvements from unimproved property");
        } else {
            isValid = true;
        }
        return isValid;
    }

    /**
     * Removes 1 improvement from property if allowed by rules
     */
    public void removeImprovements() {
        // if action valid and improvement state less than 5 (no hotel) then remove a house
        if (removeImprovementsValid() && getImprovmentState() < 5) {
            houseCount--;
            improvemntState--;
            //Otherwise, if action valid then remove a hotel.
        } else if (removeImprovementsValid()) {
            hotelCount = 0;
            houseCount = 4;
            improvemntState--;
        }
    }

}
