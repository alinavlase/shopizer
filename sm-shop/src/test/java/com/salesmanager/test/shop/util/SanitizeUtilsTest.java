package com.salesmanager.test.shop.util;

import com.salesmanager.shop.utils.SanitizeUtils;

import org.junit.Test;
import static org.junit.Assert.*;

public class SanitizeUtilsTest {

    @Test
    public void testGetSafeRequestParamString_cleanString_returnsSameContent() {
        String result = SanitizeUtils.getSafeRequestParamString("hello world");
        // plain letters and space are allowed through the character filter
        assertTrue("Clean string should pass through", result.contains("hello"));
        assertTrue("Clean string should pass through", result.contains("world"));
    }

    @Test
    public void testGetSafeRequestParamString_removesBlacklistedChars() {
        String result = SanitizeUtils.getSafeRequestParamString("test;script");
        assertFalse("Semicolon should be removed", result.contains(";"));
        assertTrue("Non-blacklisted chars should remain", result.contains("testscript"));
    }

    @Test
    public void testGetSafeRequestParamString_removesPercent() {
        String result = SanitizeUtils.getSafeRequestParamString("hello%world");
        assertFalse("Percent sign should be removed", result.contains("%"));
    }

    @Test
    public void testGetSafeRequestParamString_removesAmpersand() {
        String result = SanitizeUtils.getSafeRequestParamString("a&b");
        assertFalse("Ampersand should be removed", result.contains("&amp;"));
        assertFalse("Ampersand should be removed", result.contains("&"));
    }

    @Test
    public void testGetSafeRequestParamString_removesCaret() {
        String result = SanitizeUtils.getSafeRequestParamString("a^b");
        assertFalse("Caret should be removed", result.contains("^"));
    }

    @Test
    public void testGetSafeRequestParamString_removesAngleBrackets() {
        String result = SanitizeUtils.getSafeRequestParamString("<script>alert('xss')</script>");
        assertFalse("< should be removed", result.contains("<"));
        assertFalse("> should be removed", result.contains(">"));
    }

    @Test
    public void testGetSafeRequestParamString_emptyString_returnsEmpty() {
        String result = SanitizeUtils.getSafeRequestParamString("");
        assertNotNull(result);
        assertTrue("Empty input should produce empty output", result.isEmpty());
    }

    @Test
    public void testGetSafeRequestParamString_nullInput_returnsEmpty() {
        String result = SanitizeUtils.getSafeRequestParamString(null);
        assertNotNull(result);
        assertTrue("Null input should produce empty output", result.isEmpty());
    }

    @Test
    public void testGetSafeRequestParamString_alphanumeric_fullyPreserved() {
        String result = SanitizeUtils.getSafeRequestParamString("abc123XYZ");
        assertEquals("abc123XYZ", result);
    }
}
