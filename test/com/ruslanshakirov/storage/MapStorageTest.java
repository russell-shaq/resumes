package com.ruslanshakirov.storage;

import com.ruslanshakirov.model.Resume;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MapStorageTest extends AbstractStorageTest {
    public MapStorageTest() {
        super(new MapStorage());
    }

    @Test
    public void getAll() {
        Resume[] resumes = storage.getAll();
        assertEquals(4, resumes.length);
    }
}
