/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import GUI.Log;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.Math.ceil;
import static java.lang.Math.floor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import monopoly.Dice.DiceStats;
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

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {

        //Add players
        Players.add("Test1", 1, Token.TOPHAT);
        Players.add("Test2", 2, Token.RACECAR);
        Players.add("Test3", 3, Token.BATTLESHIP);

        //Override any default rules
        Rules.setFreeParkingBonusEnabled(true);

        //Add defualt chest & chance card decks, gameboard cells and 2X 6-side dice.
        ChanceCards.init();
        ChestCards.init();
        Cells.init();
        Dice.init();
//
//        System.out.println(Dice.getExpectedRoll());
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
        int PLAYER_ROUNDS_AMOUNT = 20;

        for (int playerRounds = 1; playerRounds <= PLAYER_ROUNDS_AMOUNT; playerRounds++) {
            for (int i = 1; i <= Players.amount(); i++) {
                Players.get(i).initializeTurn();
                do {
                    Players.get(i).beginTurn();
                    Players.get(i).midTurn();
                } while (Dice.isDouble(Dice.getFaceValues()) && !Players.get(i).isInJail() && !Players.get(i).isPlayerExitingJail());
                Players.get(i).endTurn();
            }
        }

        //step turn
        Players.get(1).initializeTurn();
        do {
            Players.get(1).beginTurn();
            Players.get(1).midTurn();
        } while (Dice.isDouble(Dice.getFaceValues()) && !Players.get(1).isInJail() && !Players.get(1).isPlayerExitingJail());
        Players.get(1).endTurn();

        //step round
        for (int i = 1; i <= Players.amount(); i++) {
            Players.get(i).initializeTurn();
            do {
                Players.get(i).beginTurn();
                Players.get(i).midTurn();
            } while (Dice.isDouble(Dice.getFaceValues()) && !Players.get(i).isInJail() && !Players.get(i).isPlayerExitingJail());
            Players.get(i).endTurn();
        }


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Log().setVisible(true);
        });
    }

}

// TODO:
// Predict net outcome of next N turns, ML.
//---track jail bonds
//--COMPLETE draw with removal
//--TODO player reinsertion
//complete rule class
//begin jail exit strategies
// - spend card
// - pay fine
// - roll doubles
// - wait
//unify player/bank cash transfers
// DONE genaralise board length in token roll-over
//RAILROAD DONE - generalise conditions for RR and U rent - redesign import, include TYPE for refrencing - not param. signature.

