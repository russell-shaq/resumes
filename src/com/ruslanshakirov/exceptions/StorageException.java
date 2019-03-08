package com.ruslanshakirov;

public class StorageException extends RuntimeException {
    private String uuid;

    public StorageException(String message, String uuid) {
        super(message);
        this.uuid = uuid;
    }
}
