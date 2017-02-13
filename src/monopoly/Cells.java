/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bgood_000
 */
public final class Cells {

    /**
     * LOCATIONS maps the index (K) of each position on the game board to it's
     * defining Cell object (V).
     */
    public static Map<Integer, Cell> LOCATIONS = new HashMap<>();
    public static Map<Cell, Integer> PLAYER_OWNERSHIP = new HashMap<>();

    public Cells(String filePath) throws IOException {
        //"In Jail" is always at position 0
        add(0, "In Jail", "Gray");
        //"GO" is always at position 1
        add(1, "GO", "grey", "creditAbs", Integer.toString((Rules.isGoLandBonusEnabled()) ? Rules.getGoLandingBonusValue() + Rules.getPassGoCredit() : Rules.getPassGoCredit()));
        //Instantiate new CSV reader with specified filepath
        CSVReader reader = new CSVReader(new FileReader(filePath));//TODO - change to relative FP.
        //Read all entries found in CSV file
        List<String[]> getCSV = reader.readAll();
        //get amount of entries
        int entries = getCSV.size();

        for (int i = 0; i < entries; i++) {
            //for each entry:
            //Each cell begins with its type -- used to determine correct constructor
            String entryType = Arrays.asList(getCSV.get(i)).get(0);
            //Then its index
            Integer cellIndex = Integer.valueOf(Arrays.asList(getCSV.get(i)).get(1));
            //And continues with its name
            String cellName = Arrays.asList(getCSV.get(i)).get(2);
            //And lastly color
            String cellColor = Arrays.asList(getCSV.get(i)).get(3);

            //Use size of each entry as signature to identify cell type it represents and parse accordingly
            switch (entryType) {
                case "PROPERTY":
                    //property signatue - char,int,int,int,int,int,int,int,int,int
                    //if celltype is "property" then parse remaining entries according to signature
                    char propertyGroupID = Arrays.asList(getCSV.get(i)).get(4).charAt(0);
                    int propertyBaseValue = Integer.parseInt(Arrays.asList(getCSV.get(i)).get(5));
                    int propertyMortgageValue = Integer.parseInt(Arrays.asList(getCSV.get(i)).get(6));
                    int propertyHouseValue = Integer.parseInt(Arrays.asList(getCSV.get(i)).get(7));
                    int propertyHotelValue = Integer.parseInt(Arrays.asList(getCSV.get(i)).get(8));
                    int propertyRentBase = Integer.parseInt(Arrays.asList(getCSV.get(i)).get(9));
                    int propertyRent1H = Integer.parseInt(Arrays.asList(getCSV.get(i)).get(10));
                    int propertyRent2H = Integer.parseInt(Arrays.asList(getCSV.get(i)).get(11));
                    int propertyRent3H = Integer.parseInt(Arrays.asList(getCSV.get(i)).get(12));
                    int propertyRent4H = Integer.parseInt(Arrays.asList(getCSV.get(i)).get(13));
                    int propertyRentHotel = Integer.parseInt(Arrays.asList(getCSV.get(i)).get(14));
                    //Add to collection
                    add(
                            cellIndex,
                            cellName,
                            cellColor,
                            propertyGroupID,
                            propertyBaseValue,
                            propertyMortgageValue,
                            propertyHouseValue,
                            propertyHotelValue,
                            propertyRentBase,
                            propertyRent1H,
                            propertyRent2H,
                            propertyRent3H,
                            propertyRent4H,
                            propertyRentHotel
                    );
                    break;
                case "RAILROAD":
                    //railroad signature - int,int,int[]
                    //If celltype is "railroad" then parse remaining entries as primirtive int.
                    int railroadBaseValue = Integer.parseInt(Arrays.asList(getCSV.get(i)).get(4));
                    int railroadMortgageValue = Integer.parseInt(Arrays.asList(getCSV.get(i)).get(5));

                    //get length of RAILROAD type entry
                    int entryLegth = Arrays.asList(getCSV.get(i)).size();
                    //entries i=6+ are part of the rent conditions array.  i.e., i = [6,entryLength]
                    //get entries, convert to int, add to array
                    ArrayList<Integer> railroadRentConditions = new ArrayList<>();
                    for (int j = 6; j < entryLegth; j++) {
                        railroadRentConditions.add(Integer.valueOf(Arrays.asList(getCSV.get(i)).get(j)));
                    }
                    railroadRentConditions.add(0, 0);

//                    int railroadRentBase = Integer.parseInt(Arrays.asList(getCSV.get(i)).get(6));
//                    int railroadRent2R = Integer.parseInt(Arrays.asList(getCSV.get(i)).get(7));
//                    int railroadRent3R = Integer.parseInt(Arrays.asList(getCSV.get(i)).get(8));
//                    int railroadRent4R = Integer.parseInt(Arrays.asList(getCSV.get(i)).get(9));
                    //Add railroad cell to collection
                    add(
                            cellIndex,
                            cellName,
                            cellColor,
                            railroadBaseValue,
                            railroadMortgageValue,
                            railroadRentConditions
                    //                            railroadRentBase,
                    //                            railroadRent2R,
                    //                            railroadRent3R,
                    //                            railroadRent4R
                    );
                    break;
                case "UTILITY":
                    //utility signature
                    //int,int,int,int
                    int utilityBaseValue = Integer.parseInt(Arrays.asList(getCSV.get(i)).get(4));
                    int utilityMortgageValue = Integer.parseInt(Arrays.asList(getCSV.get(i)).get(5));
                    int utilityOneMult = Integer.parseInt(Arrays.asList(getCSV.get(i)).get(6));
                    int utilityTwoMult = Integer.parseInt(Arrays.asList(getCSV.get(i)).get(7));
                    //Add utulity cell to collection
                    add(
                            cellIndex,
                            cellName,
                            cellColor,
                            utilityBaseValue,
                            utilityMortgageValue,
                            utilityOneMult,
                            utilityTwoMult
                    );
                    break;
                case "SPECIAL":
                    //special signature - String,String
                    //If celltype is "special" then parse remaning entries as Strings
                    String specialType = Arrays.asList(getCSV.get(i)).get(4);
                    String specialParamater = Arrays.asList(getCSV.get(i)).get(5);
                    //Add cell to collection
                    add(
                            cellIndex,
                            cellName,
                            cellColor,
                            specialType,
                            specialParamater
                    );
                    break;
            }
        }
    }

    /**
     * initialises a new collection of cells according to default specification.
     *
     * @throws IOException
     */
    public static void init() throws IOException {
        Cells cells = new Cells("CellData.CSV");
    }

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
     * @param railroadRentConditions // * @param rentBase The rent the owning
     * player receives when only this // * railroad is owned // * @param rent2R
     * The rent the owning player receives when this railroad plus // * one
     * other is owned // * @param rent3R The rent the owning player receives
     * when this railroad plus // * two others are owned // * @param rent4R The
     * rent the owning player receives when this player owns // * all railroads
     */
    public void add(
            Integer boardLocation,
            String name,
            String color,
            int baseValue,
            int mortgageValue,
            List railroadRentConditions
    //            int rentBase,
    //            int rent2R,
    //            int rent3R,
    //            int rent4R
    ) {
        LOCATIONS.put(boardLocation, new Cell(name, color, baseValue, mortgageValue, railroadRentConditions));
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

    public static int locationsAmount() {
        return LOCATIONS.size() - 1;
    }

    public static Map getPlayerOwnership() {
        return PLAYER_OWNERSHIP;
    }

    public static Map<Integer, Cell> getCells() {
        return LOCATIONS;
    }
}
