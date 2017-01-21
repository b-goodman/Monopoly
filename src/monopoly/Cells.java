/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author bgood_000
 */
public class Cells {

    /**
     * LOCATIONS maps the index (K) of each position on the game board to it's
     * defining Cell object (V).
     */
    public static Map<Integer, Cell> LOCATIONS = new HashMap<>();

    /**
     * Creates a new Property Cell and adds it to LOCATIONS.
     *
     * @param boardLocation index of the board position that this cell occupies
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
    public void add(
            Integer boardLocation,
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
            int rentHotel
    ) {
        LOCATIONS.put(boardLocation, new Cell(name, color, groupID, baseValue, mortgageValue, houseValue, hotelValue, rentBase, rent1H, rent2H, rent3H, rent4H, rentHotel));
    }

    /**
     * Creates a new Railroad Cell and adds it to LOCATIONS.
     *
     * @param boardLocation Index of the board position that this cell occupies
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
    public void add(
            Integer boardLocation,
            String name,
            String color,
            int baseValue,
            int mortgageValue,
            int rentBase,
            int rent2R,
            int rent3R,
            int rent4R
    ) {
        LOCATIONS.put(boardLocation, new Cell(name, color, baseValue, mortgageValue, rentBase, rent2R, rent3R, rent4R));
    }

    /**
     * Creates a new utility Cell and adds it to LOCATIONS.
     *
     * @param boardLocation Index of the board position that this cell occupies
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
    public void add(
            Integer boardLocation,
            String name,
            String color,
            int baseValue,
            int mortgageValue,
            int oneUtilityMult,
            int twoUtilityMult
    ) {
        LOCATIONS.put(boardLocation, new Cell(name, color, baseValue, mortgageValue, oneUtilityMult, twoUtilityMult));
    }

    /**
     * Creates a new special type Cell and adds it to LOCATIONS.
     *
     * @param boardLocation Index of the board position that this cell occupies
     * @param name Cell name
     * @param color Cell colour
     * @param actionType Classifies the action the player mist take when landing
     * on this location.
     * @param actionParamater Gives specific behaviour to action.
     */
    public void add(
            Integer boardLocation,
            String name,
            String color,
            String actionType,
            String actionParamater
    ) {
        LOCATIONS.put(boardLocation, new Cell(name, color, actionType, actionParamater));
    }

    /**
     * Creates a new jail type Cell and adds it to LOCATIONS.
     *
     * @param boardLocation Index of the board position that this cell occupies
     * @param name Cell name
     * @param color Cell colour
     */
    public void add(
            Integer boardLocation,
            String name,
            String color
    ) {
        LOCATIONS.put(boardLocation, new Cell(name, color));
    }

    /**
     * Gets the Cell object stored at position boardLocaion in the map
     * LOCATIONS.
     *
     * @param boardLocation the location of a position on the board counted from
     * GO = 1
     * @return the Cell object representing that position.
     */
    public static Cell get(Integer boardLocation) {
        return LOCATIONS.get(boardLocation);
    }
}
