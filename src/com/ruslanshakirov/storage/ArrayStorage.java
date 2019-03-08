package com.ruslanshakirov.storage;

import com.ruslanshakirov.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void addElement(int index, Resume r) {
        if (size < storage.length) {
            storage[size++] = r;
        }
    }

    @Override
    protected void fillDeletedElement(int index) {
        storage[index] = storage[size - 1];
        storage[size - 1] = null;
    }

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
