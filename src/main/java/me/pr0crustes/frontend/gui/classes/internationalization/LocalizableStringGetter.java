package me.pr0crustes.frontend.gui.classes.internationalization;

import java.util.ResourceBundle;

/**
 * Class mostly composed with static methods that handles text internationalization.
 */
public class LocalizableStringGetter {

    /**
     * The resource bundle variable.
     * Inits with english as default language.
     */
    private static ResourceBundle bundle = LocalizableStringGetter.bundleWithLocale(SupportedLanguages.DEFAULT);

    /**
     * Static method that sets the bundle to a bundle with a new SupportedLanguages.
     * @param language the new locale. Being a SupportedLanguages makes sure that it not a typo.
     * @see SupportedLanguages
     */
    public static void setLocale(SupportedLanguages language) {
        LocalizableStringGetter.bundle = LocalizableStringGetter.bundleWithLocale(language);
    }

    /**
     * Static method that creates a bundle, given a location.
     * @param language the new locale. Being a SupportedLanguages makes sure that it not a typo.
     * @see SupportedLanguages
     * @return the resource bundle with that location.
     */
    private static ResourceBundle bundleWithLocale(SupportedLanguages language) {
        return ResourceBundle.getBundle("Strings", language.getLocale());
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
