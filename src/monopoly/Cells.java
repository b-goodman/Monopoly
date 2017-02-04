/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;
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
            //Each cell begins with index
            Integer cellIndex = Integer.valueOf(Arrays.asList(getCSV.get(i)).get(0));
            //And continues with its name
            String cellName = Arrays.asList(getCSV.get(i)).get(1);
            //And lastly color
            String cellColor = Arrays.asList(getCSV.get(i)).get(2);
            //get length of each entry
            int entrySize = Arrays.asList(getCSV.get(i)).size();
            //Use size of each entry as signature to identify cell type it represents and parse accordingly
            switch (entrySize) {
                case 14:
                    //property signatue - char,int,int,int,int,int,int,int,int,int
                    //if celltype is "property" then parse remaining entries according to signature
                    char propertyGroupID = Arrays.asList(getCSV.get(i)).get(3).charAt(0);
                    int propertyBaseValue = Integer.parseInt(Arrays.asList(getCSV.get(i)).get(4));
                    int propertyMortgageValue = Integer.parseInt(Arrays.asList(getCSV.get(i)).get(5));
                    int propertyHouseValue = Integer.parseInt(Arrays.asList(getCSV.get(i)).get(6));
                    int propertyHotelValue = Integer.parseInt(Arrays.asList(getCSV.get(i)).get(7));
                    int propertyRentBase = Integer.parseInt(Arrays.asList(getCSV.get(i)).get(8));
                    int propertyRent1H = Integer.parseInt(Arrays.asList(getCSV.get(i)).get(9));
                    int propertyRent2H = Integer.parseInt(Arrays.asList(getCSV.get(i)).get(10));
                    int propertyRent3H = Integer.parseInt(Arrays.asList(getCSV.get(i)).get(11));
                    int propertyRent4H = Integer.parseInt(Arrays.asList(getCSV.get(i)).get(12));
                    int propertyRentHotel = Integer.parseInt(Arrays.asList(getCSV.get(i)).get(13));
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
                case 9:
                    //railroad signature - int,int,int,int,int,int
                    //If celltype is "railroad" then parse remaining entries as primirtive int.
                    int railroadBaseValue = Integer.parseInt(Arrays.asList(getCSV.get(i)).get(3));
                    int railroadMortgageValue = Integer.parseInt(Arrays.asList(getCSV.get(i)).get(4));
                    int railroadRentBase = Integer.parseInt(Arrays.asList(getCSV.get(i)).get(5));
                    int railroadRent2R = Integer.parseInt(Arrays.asList(getCSV.get(i)).get(6));
                    int railroadRent3R = Integer.parseInt(Arrays.asList(getCSV.get(i)).get(7));
                    int railroadRent4R = Integer.parseInt(Arrays.asList(getCSV.get(i)).get(8));
                    //Add railroad cell to collection
                    add(
                            cellIndex,
                            cellName,
                            cellColor,
                            railroadBaseValue,
                            railroadMortgageValue,
                            railroadRentBase,
                            railroadRent2R,
                            railroadRent3R,
                            railroadRent4R
                    );
                    break;
                case 7:
                    //utility signature
                    //int,int,int,int
                    int utilityBaseValue = Integer.parseInt(Arrays.asList(getCSV.get(i)).get(3));
                    int utilityMortgageValue = Integer.parseInt(Arrays.asList(getCSV.get(i)).get(4));
                    int utilityOneMult = Integer.parseInt(Arrays.asList(getCSV.get(i)).get(5));
                    int utilityTwoMult = Integer.parseInt(Arrays.asList(getCSV.get(i)).get(6));
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
                case 5:
                    //special signature - String,String
                    //If celltype is "special" then parse remaning entries as Strings
                    String specialType = Arrays.asList(getCSV.get(i)).get(3);
                    String specialParamater = Arrays.asList(getCSV.get(i)).get(4);
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
