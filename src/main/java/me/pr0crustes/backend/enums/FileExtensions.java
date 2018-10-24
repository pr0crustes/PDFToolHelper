package me.pr0crustes.backend.enums;

import javafx.stage.FileChooser;

/**
 * FileExtensions is just a self explanatory ENUM made to help with file extensions.
 */
public enum FileExtensions {

    PDF("pdf"),
    PNG("png"),
    JPG("jpg"),
    JPEG("jpeg"),
    DOC("doc"),
    DOCX("docx");

    private final String value;

    /**
     * Just a constructor.
     * @param value String that the FileExtension will hold.
     */
    FileExtensions(String value) {
        this.value = value;
    }

    /**
     * Override of toString.
     * @return the value that the FileExtension holds.
     */
    public String toString() {
        return this.value;
    }

    /**
     * Method that returns a string representing it description.
     * @return a description string.
     */
    private String getFilterDescription() {
        return this.toString().toUpperCase() + "s Files";
    }

    /**
     * Returns a string formally representing the extension.
     * Useful for displaying to the user.
     * @return a string formally representative.
     */
    public String getExtension() {
        return "*." + this.toString();
    }

    /**
     * asFilter is a method that converts the FileExtension into a FileChooser.ExtensionFilter
     * @return the FileExtension as a FileChooser.ExtensionFilter.
     */
    public FileChooser.ExtensionFilter asFilter() {
        return new FileChooser.ExtensionFilter(this.getFilterDescription(), this.getExtension());
    }

}
