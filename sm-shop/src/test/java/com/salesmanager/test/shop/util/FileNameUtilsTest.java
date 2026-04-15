package com.salesmanager.test.shop.util;

import com.salesmanager.shop.utils.FileNameUtils;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class FileNameUtilsTest {

    private FileNameUtils fileNameUtils;

    @Before
    public void setUp() {
        fileNameUtils = new FileNameUtils();
    }

    @Test
    public void testValidFileName_nameWithExtension_returnsTrue() {
        assertTrue(fileNameUtils.validFileName("image.jpg"));
    }

    @Test
    public void testValidFileName_nameWithDotExtension_returnsTrue() {
        assertTrue(fileNameUtils.validFileName("document.pdf"));
    }

    @Test
    public void testValidFileName_nameWithMultipleDots_returnsTrue() {
        assertTrue(fileNameUtils.validFileName("my.file.name.txt"));
    }

    @Test
    public void testValidFileName_noExtension_returnsFalse() {
        assertFalse(fileNameUtils.validFileName("filename"));
    }

    @Test
    public void testValidFileName_dotOnly_returnsFalse() {
        assertFalse(fileNameUtils.validFileName("."));
    }

    @Test
    public void testValidFileName_dotPrefixWithExtension_returnsFalse() {
        // BaseName of ".hidden" is "" in Apache Commons, so this should fail
        assertFalse(fileNameUtils.validFileName(".hidden"));
    }

    @Test
    public void testValidFileName_emptyString_returnsFalse() {
        assertFalse(fileNameUtils.validFileName(""));
    }

    @Test
    public void testValidFileName_extensionOnly_returnsFalse() {
        // ".jpg" has no base name
        assertFalse(fileNameUtils.validFileName(".jpg"));
    }
}
