/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import java.util.ArrayList;
import java.util.List;
import monopoly.Enums.EventType;

/**
 *
 * @author bgood_000
 */
public class GameLog {

    private static List<LogEntry> GAME_LOG = new ArrayList<>();
    private static int logCounter = 0;

    public static void logPlayerTurn(LogEntry playerTurn) {

        GAME_LOG.add(playerTurn);
    }

    public static List<LogEntry> getGameLog() {
        return GAME_LOG;
    }

    public static int getLogCounter() {
        return logCounter;
    }

    public static void incLogCounter() {
        logCounter++;
    }
}
