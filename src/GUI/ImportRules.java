/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.HashMap;
import java.util.Map;
import monopoly.Enums.RuleName;

/**
 *
 * @author bgood_000
 */
public class ImportRules {

    Map<RuleName, String> importedRules = new HashMap<>();

    public static boolean isIntegral(String str) {
        try {
            int i = Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isBoolean(String str) {
        return ("true".equals(str) || "false".equals(str));
    }

}
