package com.alexhwang;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

/**
 * @author jasmine.schladen
 * @since Oct-2016
 */
public class SampleTest {

    @Test
    public static void testSomething() {
        assertEquals("thisString", "thisString");
        assertNotEquals("oneString", "twoString");
    }
}
