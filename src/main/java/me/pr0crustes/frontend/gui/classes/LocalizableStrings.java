package me.pr0crustes.frontend.gui.classes;

/**
 * Enum composed of strings that should be in languages .properties files.
 * Prevents typos.
 */
public enum LocalizableStrings {

    TITLE("Title"),
    FILE("File"),
    SELECT_FILE("Select File"),
    RANGE_EX("RangeEx"),
    SAVE("Save"),
    INTO_FILE("Into File"),
    AFTER_PAGE("After Page"),
    CROP("Crop"),
    MERGE("Merge"),
    INSERT("Insert"),
    CONVERT("Convert"),
    QUALITY("Quality"),
    DPI("DPI"),
    FILE_ERROR("File Error"),
    AN_ERROR_OCCURRED_WITH_THE_FILE("An error occurred with the file."),
    CHECK_YOUR_INPUT("Check your input."),
    ARGUMENT_ERROR("Argument Error"),
    INVALID_ARGUMENTS("Invalid Arguments"),
    AT_LEAST_ONE_ARGUMENT_IS_INVALID("At least one argument is invalid."),
    UNKNOWN_ERROR("Unknown Error"),
    AN_UNKNOWN_ERROR_OCCURRED("An Unknown Error Occurred"),
    REPORT_IF_YOU_THINK_THIS_IS_A_BUG("Report if you think this is a bug."),
    ALL_FILES("All Files");


    /**
     * Variable that will hold the identifier.
     */
    private final String identifier;

    /**
     * Inits, setting the identifier.
     * @param identifier the enum identifier.
     */
    LocalizableStrings(String identifier) {
        this.identifier = identifier;
    }

    /**
     * Quick shortcut to return the localized string.
     * @return the localized string.
     */
    public String localized() {
        return LocalizableStringGetter.getString(this.identifier);
    }

}
