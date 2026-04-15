package com.salesmanager.test.business.utils;

import com.salesmanager.core.business.utils.NumberUtils;

import org.junit.Test;
import static org.junit.Assert.*;

public class NumberUtilsTest {

    @Test
    public void testIsPositive_null_returnsFalse() {
        assertFalse(NumberUtils.isPositive(null));
    }

    @Test
    public void testIsPositive_zero_returnsFalse() {
        assertFalse(NumberUtils.isPositive(0L));
    }

    @Test
    public void testIsPositive_negative_returnsFalse() {
        assertFalse(NumberUtils.isPositive(-1L));
    }

    @Test
    public void testIsPositive_positiveOne_returnsTrue() {
        assertTrue(NumberUtils.isPositive(1L));
    }

    @Test
    public void testIsPositive_largePosive_returnsTrue() {
        assertTrue(NumberUtils.isPositive(Long.MAX_VALUE));
    }

    @Test
    public void testIsPositive_largeNegative_returnsFalse() {
        assertFalse(NumberUtils.isPositive(Long.MIN_VALUE));
    }
}
