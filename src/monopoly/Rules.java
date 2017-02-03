/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

/**
 *
 * @author bgood_000
 */
public final class Rules {

    private static int PASS_GO_CREDIT = 200;

    private static boolean PASS_GO_CREDIT_DOUBLE_LAND = false;

    private static int DOUBLES_SPEEDING_LIMIT = 3;

    public static int getPassGoCredit() {
        return PASS_GO_CREDIT;
    }

    public static boolean isGoLandBonus() {
        return PASS_GO_CREDIT_DOUBLE_LAND;
    }

    public static int getDoublesSpeedingLimit() {
        return DOUBLES_SPEEDING_LIMIT;
    }

    public static void setPassGoCredit(int creditAmount) {
        PASS_GO_CREDIT = creditAmount;
    }

    public static void setGoLandingBonus(boolean isBonusOn) {
        PASS_GO_CREDIT_DOUBLE_LAND = isBonusOn;
    }

}
