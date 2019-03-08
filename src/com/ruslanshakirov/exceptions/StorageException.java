package com.ruslanshakirov.exceptions;

import java.util.UUID;

public class StorageException extends RuntimeException {
    private final String uuid;

    public StorageException(String message, String uuid) {
        super(message);
        this.uuid = uuid;
    }

    public StorageException() {
        uuid = UUID.randomUUID().toString();
    }
}
