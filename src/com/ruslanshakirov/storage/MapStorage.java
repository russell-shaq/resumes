package com.ruslanshakirov.storage;

import com.ruslanshakirov.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    protected Map<String, Resume> storage = new HashMap<String, Resume>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected int getIndex(String uuid) {
        return storage.containsKey(uuid) ? 1 : -1;
    }

    @Override
    protected Resume doGet(String uuid, int index) {
        return storage.get(uuid);
    }

    @Override
    protected void doSave(Resume resume, int index) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void doUpdate(Resume resume, int index) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void doDelete(String uuid, int index) {
        storage.remove(uuid);
    }

    @Override
    protected Resume[] doGetAll() {
        return storage.values().toArray(new Resume[0]);
    }
}
