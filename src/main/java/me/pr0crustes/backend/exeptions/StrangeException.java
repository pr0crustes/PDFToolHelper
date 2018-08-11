package me.pr0crustes.backend.exeptions;

/**
 * StrangeException is a custom exception that should be thrown when something gone wrong and the program cannot identify what caused it.
 * Should be throw with caution, since not a lot of methods catch it.
 */
public class StrangeException extends Exception {
    public StrangeException() {
        super();
    }
}
