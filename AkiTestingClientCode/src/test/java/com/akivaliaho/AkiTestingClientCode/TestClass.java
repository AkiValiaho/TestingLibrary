package com.akivaliaho.AkiTestingClientCode;

import AkiTest.assertz.Assertion;
import com.akivaliaho.AkiTest.Test;
import org.slf4j.LoggerFactory;

public class TestClass {
    private org.slf4j.Logger LOG = LoggerFactory.getLogger(TestClass.class);

    @Test(expected = AssertionError.class)
    public void initializationTest() {
        LOG.info("Found the test method!");
        Assertion.assertTrue(false);
    }
}
