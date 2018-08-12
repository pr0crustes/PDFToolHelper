package me.pr0crustes.backend.exeptions;

import java.io.IOException;

/**
 * NoFileException is a custom exception that should be thrown when a file is not found.
 */
public class NoFileException extends IOException {
    public NoFileException() {
        super();
    }
}
