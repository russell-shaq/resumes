package com.ruslanshakirov.storage;

import com.ruslanshakirov.exceptions.ExistStorageException;
import com.ruslanshakirov.exceptions.NotExistStorageException;
import com.ruslanshakirov.exceptions.StorageException;
import com.ruslanshakirov.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public abstract class AbstractArrayStorageTest {

    protected Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_6 = "uuid6";
    private static final String UUID_4 = "uuid4";

    @Before
    public void setUp() throws Exception {
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void get() {
        assertEquals(new Resume(UUID_1), storage.get(UUID_1));
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());

    }

    @Test
    public void getAll() {
        Resume[] resumes = new Resume[3];
        resumes[0] = new Resume(UUID_1);
        resumes[1] = new Resume(UUID_2);
        resumes[2] = new Resume(UUID_3);
        assertArrayEquals(resumes, storage.getAll());
        assertEquals(3, storage.getAll().length);
    }

    @Test
    public void delete() {
        Resume[] resumes = new Resume[2];
        resumes[0] = new Resume(UUID_1);
        resumes[1] = new Resume(UUID_3);
        storage.delete(UUID_2);
        assertArrayEquals(resumes, storage.getAll());
        assertEquals(2, storage.size());
    }

    @Test
    public void update() {
    }

    @Test
    public void save() {
        Resume[] resumes = new Resume[4];
        resumes[0] = new Resume(UUID_1);
        resumes[1] = new Resume(UUID_2);
        resumes[2] = new Resume(UUID_3);
        resumes[3] = new Resume(UUID_6);
        storage.save(new Resume(UUID_6));
        assertArrayEquals(resumes, storage.getAll());
        assertEquals(4, storage.size());

    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
        storage.delete("dummy");
        storage.update(new Resume(UUID_6));
    }

    @Test(expected = ExistStorageException.class)
    public void getExist() {
        storage.save(new Resume(UUID_1));
    }

    @Test(expected = StorageException.class)
    public void getOverflow() {
       try {
           storage.clear();
           StringBuilder builder = new StringBuilder();
           for (int i = 0; i < 10000; i++) {
               builder.append("uuid").append(i);
               storage.save(new Resume(builder.toString()));
               builder.setLength(0);

           }
       } catch (StorageException e) {
           Assert.fail();
       }
       storage.save(new Resume("uuid10000"));
    }

}