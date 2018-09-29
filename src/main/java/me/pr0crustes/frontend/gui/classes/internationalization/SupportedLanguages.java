package me.pr0crustes.frontend.gui.classes.internationalization;

import java.util.Locale;

/**
 * Enum made of supported languages (has bundle to).
 */
public enum SupportedLanguages {

    DEFAULT(Locale.ENGLISH),  // The default is english.
    ENGLISH(Locale.ENGLISH),
    PORTUGUESE(new Locale("pt", "BR"));

    /**
     * Property to hold the locale.
     */
    private Locale locale;

    /**
     * Constructor to init the locale.
     */
    SupportedLanguages(Locale locale) {
        this.locale = locale;
    }

    /**
     * Method that returns the corresponding locale.
     * @return the local assigned.
     */
    public Locale getLocale() {
        return locale;
    }

}
