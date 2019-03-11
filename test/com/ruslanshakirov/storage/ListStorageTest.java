package com.ruslanshakirov.storage;

import com.ruslanshakirov.exceptions.ExistStorageException;
import com.ruslanshakirov.exceptions.NotExistStorageException;
import com.ruslanshakirov.exceptions.StorageException;
import com.ruslanshakirov.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ListStorageTest extends AbstractStorageTest {

    public ListStorageTest() {
        super(new ListStorage());
    }
}