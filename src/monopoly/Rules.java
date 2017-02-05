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

    //Passing/landing on GO
    private static int PASS_GO_CREDIT = 200;

    private static boolean GO_LANDING_BONUS_ENABLED = false;

    private static int GO_LANDING_BONUS_VALUE = 200;

    //Rolling doubles/speeding
    private static int DOUBLES_SPEEDING_LIMIT = 3;

    private static boolean SPEEDING_ENABLED = true;

    //Free parking bonus
    private static boolean FREE_PARKING_BONUS_ENABLED = false;

    private static boolean FREE_PARKING_BONUS_LIMIT_ENABLED = false;

    private static int FREE_PARKING_BONUS_LIMIT = 500;

    private static int freeParkingBonusValue = 0;

    //House/hotel building
    /**
     * Normally, the amount of improvement resources (houses & hotels) is
     * finite. There are 32 houses and 12 hotels avaliable in a standard game.
     * When true, all improvement resources will remain finite.
     */
    private static boolean IMPROVEMENT_RESOURCES_FINITE = true;

    /**
     * Amount of avaliable houses. Initalized to default value (32).
     */
    private static int improvementAmountHouse = 32;
    /**
     * Amount of avaliable hotels. Initalized to default value (12).
     */
    private static int improvementAmountHotel = 12;
    /**
     * Requires player to build improvements evenly across a property group
     */
    private static boolean PROPERTY_EVEN_BUILD_ENABLED = true;
    /**
     * Amount of houses needed on a property before a hotel may be built.
     */
    private static int PROPERTY_HOTEL_REQ = 4;

    //Methods
    //Property improvement
    /**
     * Normally players are required to build improvements evenly across a
     * property group. Setting to false will disable this requirement (default:
     * true).
     *
     * @return
     */
    public static boolean isPropertyEvenBuildEnabled() {
        return PROPERTY_EVEN_BUILD_ENABLED;
    }

    public static boolean isImprovementResourcesFinite() {
        return IMPROVEMENT_RESOURCES_FINITE;
    }

    public static void setImprovementResourcesFinite(boolean enabled) {
        PROPERTY_EVEN_BUILD_ENABLED = enabled;
    }

    public static int getImprovementAmountHouse() {
        return improvementAmountHouse;
    }

    public static int getImprovementAmountHotel() {
        return improvementAmountHotel;
    }

    public static void setImprovementAmountHotel(int newAmount) {
        improvementAmountHotel = newAmount;
    }

    public static void setImprovementAmountHouse(int newAmount) {
        improvementAmountHouse = newAmount;
    }

    /**
     * Normally players are required to build improvements evenly across a
     * property group. Setting to false will disable this requirement (default:
     * true).
     *
     * @param enabled [boolean] true (default) - The even build rule should be
     * enforced. false - the even build rule is not enforced.
     */
    public static void setPropertyEvenBuildEnabled(boolean enabled) {
        PROPERTY_EVEN_BUILD_ENABLED = enabled;
    }

    /**
     * Normally, a player will need to have build 4 houses on a property before
     * building a hotel. User may override the canonical value to a lower one,
     * potentially shortening the length of a game.
     *
     * @param hotelReq [int (0,4] ] Amount of houses required to be built on a
     * property before a hotel may be built. If argument is out of bounds,
     * PROPERTY_HOTEL_REQ is reset to default value (4).
     */
    public static void setPropertyHotelReq(int hotelReq) {
        if (hotelReq > 0 && hotelReq <= 4) {
            PROPERTY_HOTEL_REQ = hotelReq;
        } else {
            PROPERTY_HOTEL_REQ = 4;
        }
    }

    /**
     * Normally, a player will need to have build 4 houses on a property before
     * building a hotel.
     *
     * @return Amount of houses which must be presently built on a property
     * before a hotel may be built
     */
    public static int getPropertyHotelReq() {
        return PROPERTY_HOTEL_REQ;
    }

    //Free parking bonus
    /**
     * Normally, any fines (cards/bail) paid by the player are retained by the
     * bank and leave the game. This rule requires that the cash is instead
     * saved and is awarded to the player who lands on Free Parking.
     *
     * @param setNewValue [boolean] true - the rule will be enforced. false -
     * the rule is not enforced (default).
     */
    public static void setFreeParkingBonusEnabled(boolean setNewValue) {
        FREE_PARKING_BONUS_ENABLED = setNewValue;
    }

    /**
     * Checks if the free parking bonus rule should be enforced (default:
     * false).
     *
     * @return [boolean] true - the rule should be enforced, false otherwise.
     */
    public static boolean isFreeParkingBonusEnabled() {
        return FREE_PARKING_BONUS_ENABLED;
    }

    public static void setFreeParkingBonusLimitValue(int bonusLimit) {
        FREE_PARKING_BONUS_LIMIT = bonusLimit;
    }

    public static void setFreeParkingBonusLimitEnabled(boolean enabled) {
        FREE_PARKING_BONUS_LIMIT_ENABLED = enabled;
    }

    public static void incFreeParkingBonusValue(int incByAmount) {
        //Add funds to the free parking bonus
        freeParkingBonusValue += incByAmount;
        //if a limit on the free parking bonus is being enforced and the bonus is equal/greater than than the limit
        if (FREE_PARKING_BONUS_LIMIT_ENABLED && freeParkingBonusValue >= FREE_PARKING_BONUS_LIMIT) {
            //reset the bonus to the value of the limit.
            freeParkingBonusValue = FREE_PARKING_BONUS_LIMIT;
            //Print message - bonus has been reached.
            System.out.println("\t" + "Free Parking bonus limit has been reached!  (Bal: " + freeParkingBonusValue + ")");
        } else {
            //Print general message.
            System.out.println("\t" + incByAmount + " is paid into Free Parking (Bal: " + freeParkingBonusValue + ")");
        }
    }

    public static int getFreeParkingBonusValue() {
        return freeParkingBonusValue;
    }

    public static void clearFreeParkingBonus() {
        freeParkingBonusValue = 0;
    }

    //Speeding - player is sent to jail for rolling doubles N times in a single turn.
    /**
     * Enable/disable speeding rule. true by default. When disabled, player will
     * not be sent to jail for rolling any amount of doubles per turn.
     *
     * @param speedingEnabledNewValue [boolean] true - enable speeding rule.
     * false - disable speeding rule.
     */
    public static void setSpeedingEnabled(boolean speedingEnabledNewValue) {
        SPEEDING_ENABLED = speedingEnabledNewValue;
    }

    /**
     * Checks if the speeding rule should be enforced.
     *
     * @return true if the speeding rule should be enforced. false otherwise.
     */
    public static boolean isSpeedingEnabled() {
        return SPEEDING_ENABLED;
    }

    /**
     * Allows user to override the default amount of doubles rolled in a single
     * turn required to send the player to jail for 'speeding'. Value will have
     * no effect if speeding rule is disabled.
     *
     * @param newSpeedingLimit [int] Player is sent to jail for rolling this
     * many doubles in a single turn (provided that isSpeedingEnabled() == true)
     */
    public static void setSpeedingLimit(int newSpeedingLimit) {
        DOUBLES_SPEEDING_LIMIT = newSpeedingLimit;
    }

    /**
     * Returns the int value of amount of doubles rolled in a single turn
     * required to send the player to jail for 'speeding'.
     *
     * @return [int] amount of doubles rolled in a single turn which sends the
     * player to jail.
     */
    public static int getDoublesSpeedingLimit() {
        return DOUBLES_SPEEDING_LIMIT;
    }

    // GO passing
    /**
     * Canonically, the player receives 200 when passing GO. PASS_GO_CREDIT is
     * initalized with this default value. Used to determine how much a player
     * receives when passing go.
     *
     * @return [int] The value of cash a player is to receive when passing GO.
     */
    public static int getPassGoCredit() {
        return PASS_GO_CREDIT;
    }

    /**
     * The amount of cash received by a player when passing GO.
     *
     * @param creditAmount [int] The value of cash to be given to the player
     * after passing GO.
     */
    public static void setPassGoCredit(int creditAmount) {
        PASS_GO_CREDIT = creditAmount;
    }

    //GO landing bonus.
    /**
     * In addition to the normal amount received for passing GO, users may
     * received a bonus if they land on GO. This checks if the rule should be
     * enforced or not.
     *
     * @return [boolean] true - the bonus rule should be enforced, false
     * otherwise.
     */
    public static boolean isGoLandBonusEnabled() {
        return GO_LANDING_BONUS_ENABLED;
    }

    /**
     * Allows the GO landing bonus rule to be enforced. (default: false)
     *
     * @param isBonusOn [boolean] true - enable. false - disable.
     */
    public static void setGoLandingBonus(boolean isBonusOn) {
        GO_LANDING_BONUS_ENABLED = isBonusOn;
    }

    /**
     * Overrides the default landing bonus value (default: 200).
     *
     * @param landingBonus [int] The amount of cash player is to receive in
     * addition to the ordinary sum for passing GO. Note: A negative value for
     * landingBonus will result in a subtraction to the player's ordinary sum.
     */
    public static void setGoLandingBonusValue(int landingBonus) {
        GO_LANDING_BONUS_VALUE = landingBonus;
    }

    /**
     * Returns the value of cash to be added to the ordinary sum for passing GO
     * if the player lands on GO.
     *
     * @return the value of cash to be added to the ordinary sum for passing GO
     * if the player lands on GO.
     */
    public static int getGoLandingBonusValue() {
        return GO_LANDING_BONUS_VALUE;
    }

}
