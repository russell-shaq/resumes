package com.ruslanshakirov.storage;

import com.ruslanshakirov.exceptions.ExistStorageException;
import com.ruslanshakirov.exceptions.NotExistStorageException;
import com.ruslanshakirov.model.Resume;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public abstract class AbstractStorageTest {
    protected Storage storage;

    static final String UUID_1 = "uuid1";
    static final Resume RESUME_1 = new Resume(UUID_1);

    static final String UUID_2 = "uuid2";
    static final Resume RESUME_2 = new Resume(UUID_2);

    static final String UUID_3 = "uuid3";
    static final Resume RESUME_3 = new Resume(UUID_3);

    static final String UUID_4 = "uuid4";
    static final Resume RESUME_4 = new Resume(UUID_4);

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
        storage.save(RESUME_4);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void save() {
        storage.delete(UUID_4);
        assertSize(3);
        storage.save(RESUME_4);
        assertSize(4);
        assertGet(RESUME_4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_4);
    }

    @Test
    public void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
        assertGet(RESUME_4);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test
    public void size() {
        assertSize(4);
    }

    @Test
    public void getAll() {
        Resume[] resumes = storage.getAll();
        assertEquals(4, resumes.length);
        assertEquals(RESUME_1, resumes[0]);
        assertEquals(RESUME_2, resumes[1]);
        assertEquals(RESUME_3, resumes[2]);
        assertEquals(RESUME_4, resumes[3]);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(UUID_2);
        assertSize(3);
        storage.get(UUID_2);
    }

    @Test
    public void update() {
        Resume resume = new Resume(UUID_2);
        storage.update(resume);
        assertSize(4);
        assertEquals(resume, storage.get(UUID_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        Resume resume = new Resume("dummy");
        storage.update(resume);
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }

    private void assertGet(Resume resume) {
        assertEquals(resume, storage.get(resume.getUuid()));
    }
}
