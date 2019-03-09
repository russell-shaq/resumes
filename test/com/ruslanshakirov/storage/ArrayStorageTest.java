package com.ruslanshakirov.storage;

import org.junit.Before;

import static org.junit.Assert.*;

public class ArrayStorageTest extends AbstractArrayStorageTest{

    @Override
    @Before
    public void setUp() throws Exception {
        storage = new ArrayStorage();
        super.setUp();
    }
}