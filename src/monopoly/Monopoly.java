/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import GUI.Log;
import GUI.Setup;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import static monopoly.Enums.CellType.PROPERTY;
//import static java.lang.Math.ceil;
//import static java.lang.Math.floor;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.IntStream;
//import monopoly.Dice.DiceStats;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Set;

/**
 *
 * @author bgood_000
 */
public class Monopoly {

    //batch rounds
    public static void batchRound(int limit) {
        for (int playerRounds = 1; playerRounds <= limit; playerRounds++) {
            for (int i = 0; i < Players.amount(); i++) {
                Players.get(i).initializeTurn();
                do {
                    Players.get(i).beginTurn();
                    Players.get(i).midTurn();
                } while (Dice.isDouble(Dice.getFaceValues()) && !Players.get(i).isInJail() && !Players.get(i).isPlayerExitingJail());
                Players.get(i).endTurn();
            }
        }
    }

    //step round
    public static void stepRound() {
        for (int i = 0; i < Players.amount(); i++) {
            Players.get(i).initializeTurn();
            do {
                Players.get(i).beginTurn();
                Players.get(i).midTurn();
            } while (Dice.isDouble(Dice.getFaceValues()) && !Players.get(i).isInJail() && !Players.get(i).isPlayerExitingJail());
            Players.get(i).endTurn();
        }
    }

    //step turn
    public static void stepTurn(Integer playerID) {
        Players.get(playerID).initializeTurn();
        do {
            Players.get(playerID).beginTurn();
            Players.get(playerID).midTurn();
        } while (Dice.isDouble(Dice.getFaceValues()) && !Players.get(playerID).isInJail() && !Players.get(playerID).isPlayerExitingJail());
        Players.get(playerID).endTurn();
    }

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
//
//        //Add players
//        Players.add("Test1", 0, Token.TOPHAT);
//        Players.add("Test2", 1, Token.RACECAR);
//        Players.add("Test3", 2, Token.BATTLESHIP);
//        Rules.init();
//
//        //Add defualt chest & chance card decks, gameboard cells and 2X 6-side dice.
//        ChanceCards.init();
//        ChestCards.init();
//        ChanceCards.shuffleDeck();
//        ChestCards.shuffleDeck();
//        Cells.init();
        Dice.init();

//        Players.get(0).initializeTurn();
//        Players.get(0).setOwnership(2);
//        Players.get(0).setOwnership(4);
//        Players.get(0).setOwnership(16);
        //System.out.println(Players.get(0).getPropertyGroupMembers('A'));
        //Cells.get(2).addImprovement();
        //System.out.println(Cells.get(2).getHouseCount());
        //System.out.println(Players.get(0).cellBenefit(Cells.get(39)));
//        for (int n : Dice.getRollProbabilities().keySet()) {
//            Cell testCell = Cells.get(11 + n);
//            System.out.println(testCell.getName());
//        }
//        for (int n : Dice.getRollProbabilities().keySet()) {
//
//            System.out.println((11 + n));
//        }
//        for (int n : Dice.getRollProbabilities().keySet()) {
//            Cell testCell = Cells.get(11 + n);
//            System.out.println(Players.get(0).cellBenefit(testCell));
//        }
        //System.out.println(Players.get(0).leaveJailEarly());
//        Players.get(0).initializeTurn();
//        do {
//            Players.get(0).beginTurn();
//            Players.get(0).midTurn();
//        } while (Dice.isDouble(Dice.getFaceValues()) && !Players.get(0).isInJail() && !Players.get(0).isPlayerExitingJail());
//        Players.get(0).endTurn();
//        Monopoly.batchRound(100);
//        Monopoly.stepRound();
//        Monopoly.stepTurn(1);
////
        //     System.out.println(Dice.getExpectedRoll());
//
//        do {
//            Players.get(1).beginTurn();
//            Players.get(1).midTurn();
//            if (Dice.isDouble(Dice.getFaceValues()) && !Players.get(1).isInJail() && !Players.get(1).isPlayerExitingJail()) {
//                System.out.println("\t" + Players.get(1).getName() + " takes another turn");
//            }
//        } while (Dice.isDouble(Dice.getFaceValues()) && !Players.get(1).isInJail() && !Players.get(1).isPlayerExitingJail());
//        Players.get(1).endTurn();
//        Players.get(1).playerCashRecieve(0, 5000);
//
//        Players.get(1).setOwnership(6);
//        Players.get(1).setOwnership(16);
//
//        Players.get(1).setOwnership(40);
//        Players.get(1).setOwnership(38);
//
//        System.out.println(Cells.get(6).getRent());
//        System.out.println(Cells.get(16).getRent());
//        Players.get(1).mortgageProperty(6);
//        System.out.println(Cells.get(6).getRent());
//        System.out.println(Cells.get(16).getRent());
//        System.out.println(Cells.get(16).memberGroupMortgageCount());
//
//game GUI
        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(() -> {
//            new Log().setVisible(true);
//        });
//config GUI
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Setup().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Monopoly.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

}

// TODO:
// generalise jail location
//GO setup post rules
// Predict net outcome of next N turns, ML.
//--TODO player reinsertion
//complete rule class
//begin jail exit strategies
// - spend card
// - pay fine
// - roll doubles
// - wait
//unify player/bank cash transfers
//RAILROAD DONE - generalise conditions for RR and U rent - redesign import, include TYPE for refrencing - not param. signature.

