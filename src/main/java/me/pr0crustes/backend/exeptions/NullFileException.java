package me.pr0crustes.backend.exeptions;

/**
 * NullFileException is a custom exception that should be thrown when a file is not present / is null.
 * Primary used when the user cancel the select file action.
 */
public class NullFileException extends NullPointerException {
    public NullFileException() {
        super();
    }
}
