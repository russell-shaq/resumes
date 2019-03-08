package com.ruslanshakirov.exceptions;

public class NotExistStorageException extends StorageException {
    public NotExistStorageException(String uuid) {
        super("Resume " + uuid + " doesn't exist", uuid);
    }
}
