package me.pr0crustes.frontend.gui.classes.internationalization;

import java.util.Locale;

/**
 * Enum made of supported languages (has bundle to).
 */
public enum SupportedLanguages {

    ENGLISH(new Locale("en", "US")),
    PORTUGUESE(new Locale("pt", "BR"));

    /**
     * Property to hold the locale.
     */
    private final Locale locale;

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
    Locale getLocale() {
        return locale;
    }

    /**
     * Set the current SupportedLanguages (this) as the app language.
     */
    public void setAppLanguage() {
        LocalizableStringGetter.setLocale(this);
    }

}
