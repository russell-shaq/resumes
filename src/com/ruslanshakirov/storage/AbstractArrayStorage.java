package com.ruslanshakirov.storage;

import com.ruslanshakirov.model.Resume;

import java.util.Arrays;
import java.util.Objects;

public abstract class AbstractArrayStorage implements Storage {
    protected Resume[] storage = new Resume[10000];
    protected int size = 0;
    protected static final String OBJECT_DOESNT_EXIST = "This object doesn't exist in storage";
    protected static final String OBJECT_EXISTS = "This object already exists";
    protected static final String ARRAY_OVERFLOW = "Array is overflown";

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public Resume get(String uuid) {
        Objects.requireNonNull(uuid);
        int index = getIndex(uuid);
        if (index < 0) {
            System.err.println(OBJECT_DOESNT_EXIST);
            return null;
        }
        return storage[index];
    }

    @Override
    public int size() {
        return size;

    }

    @Override
    public Resume[] getAll() {
        Resume[] resumes = new Resume[size];
        return Arrays.copyOf(storage, size);
        //System.arraycopy(storage, 0, resumes, 0, size);
    }

    @Override
    public void delete(String uuid) {
        Objects.requireNonNull(uuid);
        int index = getIndex(uuid);
        if (index < 0) {
            System.err.println(OBJECT_DOESNT_EXIST);
        } else {
            fillDeletedElement(index);
            size--;
        }
    }

    @Override
    public void update(Resume r) {
        Objects.requireNonNull(r);
        int index = getIndex(r.getUuid());
        if (index < 0) {
            System.err.println(OBJECT_DOESNT_EXIST);
        } else {
            storage[index] = r;
        }
    }

    @Override
    public void save(Resume r) {
        Objects.requireNonNull(r);
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            System.err.println(OBJECT_EXISTS);
        } else if (size() >= storage.length) {
            System.out.println(ARRAY_OVERFLOW);
        } else {
            addElement(index, r);
            size++;
        }
    }

    protected abstract int getIndex(String uuid);

    protected abstract void addElement(int index, Resume r);

    protected abstract void fillDeletedElement(int index);
}
