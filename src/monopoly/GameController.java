/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import java.io.IOException;

/**
 *
 * @author bgood_000
 */
public class GameController {

//
    //batch rounds
    public void batchRounds(int limit) {
        for (int playerRounds = 1; playerRounds <= limit; playerRounds++) {
            for (int i = 1; i <= Players.amount(); i++) {
                Players.get(i).initializeTurn();
                do {
                    Players.get(i).beginTurn();
                    Players.get(i).midTurn();
                } while (Dice.isDouble(Dice.getFaceValues()) && !Players.get(i).isInJail() && !Players.get(i).isPlayerExitingJail());
                Players.get(i).endTurn();
            }
        }
    }

    //step turn
    public void stepTurn(Integer playerID) {
        Players.get(playerID).initializeTurn();
        do {
            Players.get(playerID).beginTurn();
            Players.get(playerID).midTurn();
        } while (Dice.isDouble(Dice.getFaceValues()) && !Players.get(1).isInJail() && !Players.get(1).isPlayerExitingJail());
        Players.get(playerID).endTurn();
    }

    //step round
    public void stepRound() {
        for (int i = 1; i <= Players.amount(); i++) {
            Players.get(i).initializeTurn();
            do {
                Players.get(i).beginTurn();
                Players.get(i).midTurn();
            } while (Dice.isDouble(Dice.getFaceValues()) && !Players.get(i).isInJail() && !Players.get(i).isPlayerExitingJail());
            Players.get(i).endTurn();
        }
    }

}
