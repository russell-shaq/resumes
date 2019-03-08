package com.ruslanshakirov.storage;

import com.ruslanshakirov.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected int getIndex(String uuid) {
        return Arrays.binarySearch(storage, 0, size, new Resume(uuid));
    }

    @Override
    protected void addElement(int index, Resume r) {
        int modIndex = Math.abs(index + 1);
        System.arraycopy(storage, modIndex, storage, modIndex + 1, size - modIndex);
        storage[modIndex] = r;
    }

    @Override
    protected void fillDeletedElement(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - index);
    }
}

