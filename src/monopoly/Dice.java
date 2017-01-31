/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author bgood_000
 */
public final class Dice {

    private static final List<Die> DICE_LIST = new ArrayList<>();
    private static final List<Integer> ROLL_LIST = new ArrayList<>();

    class Die {

        private final int sides;

        public Die(int sides) {
            this.sides = sides;
        }

        public int getSidesAmount() {
            return sides;
        }

        public int rollDie() {
            return ThreadLocalRandom.current().nextInt(1, sides + 1);
        }
    }

    /**
     * Default Dice constructor. Initilalizes DICE_LIST with two six-sided dice.
     */
    public Dice() {
        DICE_LIST.add(new Die(6));
        DICE_LIST.add(new Die(6));
    }

    /**
     * Custom dice constructor. Initilalizes DICE_LIST with dice, each with a
     * user specified amount of sides.
     *
     * @param sides The amount of sides the Nth die has. E.G., (12,12,6) gives
     * two 12-sided dice and one six-sided die.
     */
    public Dice(int... sides) {
        for (int i = 0; i < sides.length; i++) {
            DICE_LIST.add(new Die(sides[i]));
        }
    }

    /**
     * Rolls dice. For each die in DICE_LIST, a random integer [1,sides] is
     * generated and added to ROLL_LIST.
     */
    public static void roll() {
        for (int i = 0; i < DICE_LIST.size(); i++) {
            ROLL_LIST.add(i, DICE_LIST.get(i).rollDie());
        }
    }

    /**
     *
     * @return
     */
    public static List getFaceValues() {
        return ROLL_LIST;
    }

    /**
     * Clears the last roll made by a player.
     */
    public static void clearRoll() {
        ROLL_LIST.clear();
    }

    /**
     *
     * @return
     */
    public static int getRollSum() {
        int returnSum = 0;
        returnSum = ROLL_LIST.stream().map((i) -> i).reduce(returnSum, Integer::sum);
        return (int) returnSum;
    }

    /**
     *
     * @return True if all dice roll the same value.
     */
    public static boolean isDouble() {
        boolean chk = true;
        if (ROLL_LIST.size() == 1) {
            chk = false;
        } else {
            for (int i = 1; i < ROLL_LIST.size() && chk; i++) {
                if (!Objects.equals(ROLL_LIST.get(i), ROLL_LIST.get(0))) {
                    chk = false;
                    break;
                }
            }
        }
        return chk;
    }

}
