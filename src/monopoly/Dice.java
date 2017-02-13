/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import static java.lang.Math.ceil;
import static java.lang.Math.floor;
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
    //public static final List<Integer> SIDE_AMOUNT = new ArrayList<>();
    public static final List<Integer> EXPECTED_ROLL = new ArrayList<>();

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
        List<Integer> SIDE_AMOUNT = new ArrayList<>();
        DICE_LIST.add(new Die(6));
        DICE_LIST.add(new Die(6));
        SIDE_AMOUNT.add(6);
        SIDE_AMOUNT.add(6);

        double sum = 0;
        for (int j = 0; j < SIDE_AMOUNT.size(); j++) {
            for (double i = 1; i <= SIDE_AMOUNT.get(j); i++) {
                sum += (i / SIDE_AMOUNT.get(j));
            }
        }
        EXPECTED_ROLL.add((int) sum);
    }

    /**
     * Custom dice constructor. Initilalizes DICE_LIST with dice, each with a
     * user specified amount of sides.
     *
     * @param sides The amount of sides the Nth die has. E.G., (12,12,6) gives
     * two 12-sided dice and one six-sided die.
     */
    public Dice(int... sides) {
        List<Integer> SIDE_AMOUNT = new ArrayList<>();
        for (int i = 0; i < sides.length; i++) {
            DICE_LIST.add(new Die(sides[i]));
            SIDE_AMOUNT.add(sides[i]);
        }
        double sum = 0;
        for (int j = 0; j < SIDE_AMOUNT.size(); j++) {
            for (double i = 1; i <= SIDE_AMOUNT.get(j); i++) {
                sum += (i / SIDE_AMOUNT.get(j));
            }
        }
        if ((sum % 1) != 0) {
            EXPECTED_ROLL.add((int) floor(sum));
            EXPECTED_ROLL.add((int) ceil(sum));
        } else {
            EXPECTED_ROLL.add((int) sum);
        }
    }

    public static void init() {
        Dice dice = new Dice();
    }

    public static void init(int... sides) {
        Dice dice = new Dice(sides);
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
    public static boolean isDouble(List<Integer> rollValues) {
        boolean chk = true;
        if (rollValues.size() == 1) {
            chk = false;
        } else {
            for (int i = 0; i < rollValues.size() && chk; i++) {
                if (!Objects.equals(rollValues.get(i), rollValues.get(0))) {
                    chk = false;
                    break;
                }
            }
        }
        return chk;
    }

    public static List getExpectedRoll() {
        return EXPECTED_ROLL;
    }

}
