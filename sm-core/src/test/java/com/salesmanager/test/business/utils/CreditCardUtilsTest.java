package com.salesmanager.test.business.utils;

import com.salesmanager.core.business.utils.CreditCardUtils;

import org.junit.Test;
import static org.junit.Assert.*;

public class CreditCardUtilsTest {

    @Test
    public void testMaskCardNumber_standard16DigitCard() throws Exception {
        String masked = CreditCardUtils.maskCardNumber("4111111111111111");
        assertEquals("4111XXXXXXXXXX1111", masked);
    }

    @Test
    public void testMaskCardNumber_preservesPrefixAndSuffix() throws Exception {
        String masked = CreditCardUtils.maskCardNumber("1234000000005678");
        assertTrue("Should start with first 4 digits", masked.startsWith("1234"));
        assertTrue("Should end with last 4 digits", masked.endsWith("5678"));
        assertTrue("Should contain XXXXXXXXXX in middle", masked.contains("XXXXXXXXXX"));
    }

    @Test
    public void testMaskCardNumber_exactlyTenDigits() throws Exception {
        String masked = CreditCardUtils.maskCardNumber("1234567890");
        assertEquals("1234XXXXXXXXXX7890", masked);
    }

    @Test(expected = Exception.class)
    public void testMaskCardNumber_tooShort_throwsException() throws Exception {
        CreditCardUtils.maskCardNumber("123456789");
    }

    @Test(expected = Exception.class)
    public void testMaskCardNumber_emptyString_throwsException() throws Exception {
        CreditCardUtils.maskCardNumber("");
    }
}
