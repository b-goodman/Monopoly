/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import java.util.*;

/**
 *
 * @author bgood_000
 */
public class Players {

    /**
     * Maps unique player ID (K, Integer) to player (V, Player)
     */
    private final static Map<Integer, Player> PLAYERS = new HashMap<>();

    /**
     * Creates a new instance of the default Player class and stores it within
     * PLAYERS
     *
     * @param name String - name of player
     * @param index Integer - unique player ID
     * @param token Token - players token
     */
    public void add(
            String name,
            Integer index,
            Token token
    ) {
        PLAYERS.put(index, new Player(index, name, token));
    }

    /**
     * Creates a new instance of the custom player class and stores it within
     * PLAYERS
     *
     * @param name String - name of player
     * @param index Integer - unique player ID
     * @param token Token - players token
     * @param position int - Starting position of player (default: 1)
     * @param cash int - Initial amount of cash avaliable to player (default:
     * 1500)
     * @param jailBondsAvaliable int - Initial amount of jail bonds avaliable to
     * player (default: 0)
     */
    public void add(
            String name,
            Integer index,
            Token token,
            int position,
            int cash,
            int jailBondsAvaliable
    ) {
        PLAYERS.put(index, new Player(index, name, token, position, cash, jailBondsAvaliable));
    }

    /**
     * Gets Player obj. corresponding to player ID from PLAYERS map.
     *
     * @param playerID Returns specified Player using players ID
     * @return
     */
    public static Player get(Integer playerID) {
        return PLAYERS.get(playerID);
    }

    public static int amount() {
        return PLAYERS.size();
    }
}
