package com.anddev.pm.pizzame;

import junit.framework.Assert;

import org.junit.Test;

import java.io.IOException;

public class UtilsTest {

    @Test
    public void getCurrentZipCode_nullContext() {
        try {
            Assert.assertNotNull("Context cannot be null", Utils.getCurrentZipCode(null));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}