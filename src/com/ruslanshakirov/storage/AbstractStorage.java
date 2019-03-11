package com.ruslanshakirov.storage;

import com.ruslanshakirov.exceptions.ExistStorageException;
import com.ruslanshakirov.exceptions.NotExistStorageException;
import com.ruslanshakirov.model.Resume;

import java.util.Objects;

public abstract class AbstractStorage implements Storage {

    @Override
    public Resume get(String uuid) {
        Objects.requireNonNull(uuid);
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return doGet(uuid, index);
    }

    @Override
    public void save(Resume r) {
        Objects.requireNonNull(r);
        String uuid = r.getUuid();
        int index = getIndex(uuid);
        if (index > 0) {
            throw new ExistStorageException(uuid);
        }
        doSave(r, index);
    }

    @Override
    public void update(Resume r) {
        String uuid = r.getUuid();
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        doUpdate(r, index);
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        doDelete(uuid, index);
    }

    private Object getNotExistStorageKey(String uuid) {
        if (isExist(uuid)) {
            throw new NotExistStorageException(uuid);
        }
        return
    }

    protected abstract boolean isExist(String uuid);

    @Override
    public Resume[] getAll() {
        return doGetAll();
    }

    protected abstract int getIndex(String uuid);

    protected abstract Resume doGet(String uuid, int index);

    protected abstract void doSave(Resume resume, int index);

    protected abstract void doUpdate(Resume resume, int index);

    protected abstract void doDelete(String uuid, int index);

    protected abstract Resume[] doGetAll();
}
