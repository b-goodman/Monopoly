/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import monopoly.Enums.CellType;
import static monopoly.Enums.CellType.*;
import static monopoly.Token.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author bgood_000
 */
public class PlayerTest {

    //private Player instance1, instance2, instance3, instance4, instance5, instance6, instance7, instance8;
    public PlayerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws IOException {
        Players.add("Test1", 1, WHEELBARROW);
        Players.add("Test2", 2, BATTLESHIP);
        Players.add("Test3", 3, RACECAR);
        Players.add("Test4", 4, THIMBLE);
        Players.add("Test5", 5, SHOE);
        Players.add("Test6", 6, DOG);
        Players.add("Test7", 7, TOPHAT);
        Players.add("Test8", 8, CAT);

        //Add defualt chest & chance card decks, gameboard cells and 2X 6-side dice.
        ChanceCards.init();
        ChestCards.init();
        Cells.init();
        Dice.init();

//        //loop over each player for N rounds
//        int PLAYER_ROUNDS_AMOUNT = 100;
//        for (int playerRounds = 1; playerRounds <= PLAYER_ROUNDS_AMOUNT; playerRounds++) {
//            for (int i = 1; i <= Players.amount(); i++) {
//                Players.get(i).initializeTurn();
//                do {
//                    Players.get(i).beginTurn();
//                    Players.get(i).midTurn();
//                } while (Dice.isDouble(Dice.getFaceValues()) && !Players.get(i).isInJail() && !Players.get(i).isPlayerExitingJail());
//                Players.get(i).endTurn();
//            }
//        }
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getPlayerID method, of class Player.
     */
    @Test
    public void testGetPlayerID() {
        System.out.println("getPlayerID");
        //for each player, assret that its ID is also its PLAYER key
        for (Integer id : Players.getPlayers().keySet()) {
            assertEquals(id, Players.get(id).getPlayerID());
        }
    }

    /**
     * Test of getName method, of class Player.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        String[] expResult = {"", "Test1", "Test2", "Test3", "Test4", "Test5", "Test6", "Test7", "Test8",};
        for (int i = 1; i <= Players.getPlayers().size(); i++) {
            assertEquals(Players.get(i).getName(), expResult[i]);
        }
    }

    /**
     * Test of getToken method, of class Player.
     */
    @Test
    public void testGetToken() {
        System.out.println("getToken");
        Token[] expResult = {null, WHEELBARROW, BATTLESHIP, RACECAR, THIMBLE, SHOE, DOG, TOPHAT, CAT};
        //foreach player, assert that its token equals expected value
        for (int i = 1; i <= Players.getPlayers().size(); i++) {
            assertEquals(Players.get(i).getToken(), expResult[i]);
        }
    }

    /**
     * Test of getPosition method, of class Player.
     */
    @Test
    public void testGetPosition() {
        System.out.println("getPosition");
        //check that all players begin on GO (pos 1)
        for (Player player : Players.getPlayers().values()) {
            assertEquals(player.getPosition(), 1);
        }
    }

    /**
     * Test of getPositionType method, of class Player.
     */
    @Test
    public void testGetPositionType() {
        System.out.println("getPositionType");
        //check that all players begin on GO (pos 1)

        for (Player player : Players.getPlayers().values()) {
            assertEquals(player.getPositionType(), SPECIAL);
        }
        for (Player player : Players.getPlayers().values()) {
            player.setPosition(2);
            assertEquals(player.getPositionType(), PROPERTY);
        }
        for (Player player : Players.getPlayers().values()) {
            player.setPosition(6);
            assertEquals(player.getPositionType(), RAILROAD);
        }
        for (Player player : Players.getPlayers().values()) {
            player.setPosition(13);
            assertEquals(player.getPositionType(), UTILITY);
        }

    }

    /**
     * Test of getActionType method, of class Player.
     */
    @Test
    public void testGetActionType() {
        System.out.println("getActionType");
        //check that all players identify SPECIAL cell action types
        for (Player player : Players.getPlayers().values()) {
            player.setPosition(1);
            assertEquals(player.getActionType(), "creditAbs");
        }
        for (Player player : Players.getPlayers().values()) {
            player.setPosition(3);
            assertEquals(player.getActionType(), "drawCard");
        }
        for (Player player : Players.getPlayers().values()) {
            player.setPosition(11);
            assertEquals(player.getActionType(), "null");
        }
        for (Player player : Players.getPlayers().values()) {
            player.setPosition(21);
            assertEquals(player.getActionType(), "parking");
        }
        for (Player player : Players.getPlayers().values()) {
            player.setPosition(31);
            assertEquals(player.getActionType(), "transitionAbs");
        }
    }

    /**
     * Test of getActionParamater method, of class Player.
     */
    @Test
    public void testGetActionParamater() {
        System.out.println("getActionParamater");
        for (Player player : Players.getPlayers().values()) {
            player.setPosition(3);
            assertEquals(player.getActionType(), "drawCard");
        }
        for (Player player : Players.getPlayers().values()) {
            player.setPosition(8);
            assertEquals(player.getActionType(), "drawCard");
        }
    }

    /**
     * Test of getCash method, of class Player.
     */
    @Ignore
    @Test
    public void testGetCash() {
        System.out.println("getCash");
        Player instance = null;
        int expResult = 0;
        int result = instance.getCash();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBonds method, of class Player.
     */
    @Ignore
    @Test
    public void testGetBonds() {
        System.out.println("getBonds");
        Player instance = null;
        int expResult = 0;
        int result = instance.getBonds();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isInJail method, of class Player.
     */
    @Ignore
    @Test
    public void testIsInJail() {
        System.out.println("isInJail");
        Player instance = null;
        boolean expResult = false;
        boolean result = instance.isInJail();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getJailTimeSpent method, of class Player.
     */
    @Ignore
    @Test
    public void testGetJailTimeSpent() {
        System.out.println("getJailTimeSpent");
        Player instance = null;
        int expResult = 0;
        int result = instance.getJailTimeSpent();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSpeedingCount method, of class Player.
     */
    @Ignore
    @Test
    public void testGetSpeedingCount() {
        System.out.println("getSpeedingCount");
        Player instance = null;
        int expResult = 0;
        int result = instance.getSpeedingCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentCard method, of class Player.
     */
    @Ignore
    @Test
    public void testGetCurrentCard() {
        System.out.println("getCurrentCard");
        Player instance = null;
        List expResult = null;
        List result = instance.getCurrentCard();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCash method, of class Player.
     */
    @Ignore
    @Test
    public void testSetCash() {
        System.out.println("setCash");
        int newCashAmmount = 0;
        Player instance = null;
        instance.setCash(newCashAmmount);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of playerCashRecieve method, of class Player.
     */
    @Ignore
    @Test
    public void testPlayerCashRecieve() {
        System.out.println("playerCashRecieve");
        Integer payingPlayerID = null;
        int cashAmount = 0;
        Player instance = null;
        instance.playerCashRecieve(payingPlayerID, cashAmount);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of playerCashPay method, of class Player.
     */
    @Ignore
    @Test
    public void testPlayerCashPay() {
        System.out.println("playerCashPay");
        Integer recievingPlayerID = null;
        int cashAmount = 0;
        Player instance = null;
        instance.playerCashPay(recievingPlayerID, cashAmount);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addCash method, of class Player.
     */
    @Ignore
    @Test
    public void testAddCash() {
        System.out.println("addCash");
        int addAmmount = 0;
        Player instance = null;
        instance.addCash(addAmmount);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOwnership method, of class Player.
     */
    @Test
    public void testSetOwnership() {
        System.out.println("setOwnership");
        Integer propertyBoardLocation = 2;
        Player instance = Players.get(1);
        instance.setOwnership(propertyBoardLocation);
        assertEquals(instance.getOwnership().get(0).getOwnership(), instance.getPlayerID());
    }

    /**
     * Test of getOwnership method, of class Player.
     */
    @Ignore
    @Test
    public void testGetOwnership() {
        System.out.println("getOwnership");
        Player instance = null;
        List expResult = null;
        List result = instance.getOwnership();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCompleteSets method, of class Player.
     */
    @Ignore
    @Test
    public void testGetCompleteSets() {
        System.out.println("getCompleteSets");
        Player instance = null;
        List expResult = null;
        List result = instance.getCompleteSets();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPropertyGroupMembers method, of class Player.
     */
    @Ignore
    @Test
    public void testGetPropertyGroupMembers() {
        System.out.println("getPropertyGroupMembers");
        char groupID = ' ';
        Player instance = null;
        List expResult = null;
        List result = instance.getPropertyGroupMembers(groupID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCompleteSetID method, of class Player.
     */
    @Ignore
    @Test
    public void testGetCompleteSetID() {
        System.out.println("getCompleteSetID");
        Player instance = null;
        Set expResult = null;
        Set result = instance.getCompleteSetID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addPropertyImprovementByGroup method, of class Player.
     */
    @Ignore
    @Test
    public void testAddPropertyImprovementByGroup() {
        System.out.println("addPropertyImprovementByGroup");
        char groupID = ' ';
        Player instance = null;
        instance.addPropertyImprovementByGroup(groupID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mortgageProperty method, of class Player.
     */
    @Ignore
    @Test
    public void testMortgageProperty() {
        System.out.println("mortgageProperty");
        Integer propertyID = null;
        Player instance = null;
        instance.mortgageProperty(propertyID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of unMortgageProperty method, of class Player.
     */
    @Ignore
    @Test
    public void testUnMortgageProperty() {
        System.out.println("unMortgageProperty");
        Integer propertyID = null;
        Player instance = null;
        instance.unMortgageProperty(propertyID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCellType method, of class Player.
     */
    @Ignore
    @Test
    public void testGetCellType() {
        System.out.println("getCellType");
        int cellLocation = 0;
        Player instance = null;
        CellType expResult = null;
        CellType result = instance.getCellType(cellLocation);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of gotoJail method, of class Player.
     */
    @Ignore
    @Test
    public void testGotoJail() {
        System.out.println("gotoJail");
        Player instance = null;
        instance.gotoJail();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of leaveJail method, of class Player.
     */
    @Ignore
    @Test
    public void testLeaveJail() {
        System.out.println("leaveJail");
        Player instance = null;
        instance.leaveJail();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isPlayerExitingJail method, of class Player.
     */
    @Ignore
    @Test
    public void testIsPlayerExitingJail() {
        System.out.println("isPlayerExitingJail");
        Player instance = null;
        boolean expResult = false;
        boolean result = instance.isPlayerExitingJail();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findNextCellType method, of class Player.
     */
    @Ignore
    @Test
    public void testFindNextCellType_CellType() {
        System.out.println("findNextCellType");
        CellType target = null;
        Player instance = null;
        int expResult = 0;
        int result = instance.findNextCellType(target);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findNextCellType method, of class Player.
     */
    @Ignore
    @Test
    public void testFindNextCellType_String() {
        System.out.println("findNextCellType");
        String target = "";
        Player instance = null;
        int expResult = 0;
        int result = instance.findNextCellType(target);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initializeTurn method, of class Player.
     */
    @Ignore
    @Test
    public void testInitializeTurn() {
        System.out.println("initializeTurn");
        Player instance = null;
        instance.initializeTurn();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of beginTurn method, of class Player.
     */
    @Ignore
    @Test
    public void testBeginTurn() {
        System.out.println("beginTurn");
        Player instance = null;
        instance.beginTurn();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of midTurn method, of class Player.
     */
    @Ignore
    @Test
    public void testMidTurn() {
        System.out.println("midTurn");
        Player instance = null;
        instance.midTurn();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of endTurn method, of class Player.
     */
    @Ignore
    @Test
    public void testEndTurn() {
        System.out.println("endTurn");
        Player instance = null;
        instance.endTurn();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of advanceToken method, of class Player.
     */
    @Ignore
    @Test
    public void testAdvanceToken() {
        System.out.println("advanceToken");
        int steps = 0;
        Player instance = null;
        instance.advanceToken(steps);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of drawChanceCard method, of class Player.
     */
    @Ignore
    @Test
    public void testDrawChanceCard() {
        System.out.println("drawChanceCard");
        Player instance = null;
        List expResult = null;
        List result = instance.drawChanceCard();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of drawChestCard method, of class Player.
     */
    @Ignore
    @Test
    public void testDrawChestCard() {
        System.out.println("drawChestCard");
        Player instance = null;
        List expResult = null;
        List result = instance.drawChestCard();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readCurrentCard method, of class Player.
     */
    @Ignore
    @Test
    public void testReadCurrentCard() {
        System.out.println("readCurrentCard");
        Player instance = null;
        List expResult = null;
        List result = instance.readCurrentCard();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of parseCardAction method, of class Player.
     */
    @Ignore
    @Test
    public void testParseCardAction() {
        System.out.println("parseCardAction");
        List card = null;
        Player instance = null;
        instance.parseCardAction(card);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
