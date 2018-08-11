package me.pr0crustes.backend.exeptions;

/**
 * NoTargetFileException is a custom exception that should be thrown when a file is not present / is null or an unknown exception happened with a file.
 */
public class NoTargetFileException extends NullPointerException {
    public NoTargetFileException() {
        super();
    }
}
