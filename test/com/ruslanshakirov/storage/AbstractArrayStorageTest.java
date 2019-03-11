package com.ruslanshakirov.storage;

import com.ruslanshakirov.exceptions.StorageException;
import com.ruslanshakirov.model.Resume;
import org.junit.Assert;
import org.junit.Test;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void getOverflow() {
        try {
            for (int i = 4; i < 10000; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assert.fail();
        }
        storage.save(new Resume("uuid10000"));
    }


}