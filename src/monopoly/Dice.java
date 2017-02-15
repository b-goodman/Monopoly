/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import static java.lang.Math.ceil;
import static java.lang.Math.floor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
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
    public static Map<Integer, Double> ROLL_PROBABILITIES;

    public class DiceStats {

        private final Integer[] input;
        private Integer rolloverCounter = 0;
        private final List<String> output = new ArrayList<>();
        private final Integer[] rolloverValues;
        private final Integer[] set;

        public DiceStats(Integer... faces) {
            //save parameter
            this.input = faces;
            //init. rollover values
            this.rolloverValues = new Integer[input.length];
            for (int i = 0; i < input.length; i++) {
                rolloverValues[i] = 0;
            }
            //init set array
            this.set = new Integer[input.length];
            for (Integer i = 0; i < input.length; i++) {
                set[i] = 1;
            }
            enumerateDiceValues();
        }

        public void rollSequence() {
            for (int i = 0; i < input.length; i++) {
                set[i] = 1;
            }
            //for each k = [0,K) reset counter digit EXCEPT the last (K), add corresponding kth rollover value if less than counter digit max (input[k])
            for (int k = 0; k < rolloverValues.length - 1; k++) {
                if (rolloverValues[k] < input[k]) {
                    set[k] += rolloverValues[k];
                }
            }
            output.add(Arrays.toString(set));
            while (set[(input.length) - 1] < input[(input.length) - 1]) {
                set[(input.length) - 1]++;
                output.add(Arrays.toString(set));
            }
            rolloverCounter++;
            rollover(input.length - 1);
        }

        public void rollover(int index) {
            if (index > 0 && (rolloverCounter % Math.pow(input[index], ((input.length - 1) - index))) == 0) {
                //int newIndex = index - 1;
                rollover(index - 1);
            }
            if (Objects.equals(rolloverValues[index], input[index])) {
                rolloverValues[index] = 1;
            } else {
                rolloverValues[index]++;
            }
        }

        public void enumerateDiceValues() {
            while (!Arrays.equals(rolloverValues, input)) {
                rollSequence();
            }
        }

        public List<List<Integer>> getDiceValues() {
            List<List<Integer>> returnList = new ArrayList<>();
            for (String s : output) {
                String[] items = s.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\s", "").split(",");
                List<Integer> results = new ArrayList<>();
                for (String item : items) {
                    try {
                        results.add(Integer.parseInt(item));
                    } catch (NumberFormatException nfe) {
                    }
                }
                returnList.add(results);
            }
            return returnList;
        }

        public List<Integer> getDiceTotals() {
            List<Integer> sumList = new ArrayList<>();
            for (List<Integer> l : getDiceValues()) {
                Integer sum = 0;
                for (Integer i : l) {
                    sum += i;
                }
                sumList.add(sum);
            }
            return sumList;
        }

        public Set<Integer> getDiceTotalsSet() {
            Set<Integer> values = new HashSet<>(getDiceTotals());
            return values;
        }

        public Map<Integer, Double> getDiceRollProb() {
            Map<Integer, Double> freq = new HashMap<>();
            for (Integer total : getDiceTotalsSet()) {
                Integer count = Collections.frequency(getDiceTotals(), total);
                Double f = ((double) count / (double) getDiceTotals().size());
                freq.put(total, f);
            }
            return freq;
        }

    }

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
        DiceStats dStats = new DiceStats(6, 6);
        //dStats.enumerateDiceValues();
        ROLL_PROBABILITIES = new HashMap<>(dStats.getDiceRollProb());
    }

    /**
     * Custom dice constructor. Initilalizes DICE_LIST with dice, each with a
     * user specified amount of sides.
     *
     * @param sides The amount of sides the Nth die has. E.G., (12,12,6) gives
     * two 12-sided dice and one six-sided die.
     */
    public Dice(Integer... sides) {
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
        DiceStats dStats = new DiceStats(sides);
        ROLL_PROBABILITIES = dStats.getDiceRollProb();
    }

    public static void init() {
        Dice dice = new Dice();
    }

    public static void init(Integer... sides) {
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
        List<Integer> returnList = new ArrayList<>(ROLL_LIST);
        return returnList;
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
     * @param rollValues
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

    public static Map<Integer, Double> getRollProbabilities() {

        return ROLL_PROBABILITIES;
    }

}
