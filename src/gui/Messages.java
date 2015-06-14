package gui;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Created by monika03 on 17.05.15.
 */
public class Messages {
    private static final String PREFIX = "gui.messages";
    private static String bundleName = PREFIX + "_pl_PL";

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle(bundleName);

    private Messages() {
    }

    public static void setLocale(Locale loc) {
        bundleName = PREFIX + "_" + loc.getLanguage() + "_" + loc.getCountry();
        try {
            resourceBundle = ResourceBundle.getBundle(bundleName);
//            System.out.println("ustawiam jezyk"+bundleName);
        } catch (Exception e) {
            //domyslnie ustawiamy polski
//            System.out.println("WYJATEK W MESSAGES");
            resourceBundle = ResourceBundle.getBundle(PREFIX + "_pl_PL");
        }
    }

    public static String getString(String key) {
        try {
            return resourceBundle.getString(key);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }

}