package me.pr0crustes.frontend.gui.classes;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Class mostly composed with static methods that handles text internationalization.
 */
public class LocalizableStringGetter {

    /**
     * The resource bundle variable.
     * Inits with english as default language.
     */
    private static ResourceBundle bundle = LocalizableStringGetter.bundleWithLocale(Locale.ENGLISH);

    /**
     * Static method that sets the bundle to a bundle with a new location.
     * @param locale the new locale.
     */
    public static void setLocale(Locale locale) {
        LocalizableStringGetter.bundle = LocalizableStringGetter.bundleWithLocale(locale);
    }

    /**
     * Static method that creates a bundle, given a location.
     * @param locale the locale to be used.
     * @return the resource bundle with that location.
     */
    private static ResourceBundle bundleWithLocale(Locale locale) {
        return ResourceBundle.getBundle("Strings", locale);
    }

    /**
     * Static method that returns the localized string, given the identifier.
     * @param identifier the string to find.
     * @return the corresponding string.
     */
    static String getString(String identifier) {
        return LocalizableStringGetter.bundle.getString(identifier);
    }

}
