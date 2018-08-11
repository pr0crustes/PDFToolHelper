package me.pr0crustes.backend.exeptions;

import java.io.IOException;

/**
 * PermissionException is a custom exception that should be thrown when the wanted read / write operation cannot be completed because of OS permissions.
 */
public class PermissionException extends IOException {
    public PermissionException() {
        super();
    }
}
