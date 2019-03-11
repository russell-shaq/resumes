package com.ruslanshakirov.storage;

import com.ruslanshakirov.exceptions.StorageException;
import com.ruslanshakirov.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected Resume[] storage = new Resume[10000];
    protected int size = 0;

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    protected Resume doGet(String uuid, int index) {
        return storage[index];
    }

    @Override
    protected void doSave(Resume resume, int index) {
        if (size >= storage.length) {
            throw new StorageException("Storage overflow", resume.getUuid());
        }
        addElement(index, resume);
        size++;
    }

    @Override
    protected void doUpdate(Resume resume, int index) {
        storage[index] = resume;
    }

    @Override
    protected void doDelete(String uuid, int index) {
        fillDeletedElement(index);
        size--;
    }

    @Override
    protected Resume[] doGetAll() {
        Resume[] resumes = new Resume[size];
        return Arrays.copyOf(storage, size);
    }

    protected abstract void addElement(int index, Resume r);

    protected abstract void fillDeletedElement(int index);
}
