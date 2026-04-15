package com.salesmanager.test.shop.util;

import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.utils.LocaleUtils;

import org.junit.Test;
import java.util.Locale;
import static org.junit.Assert.*;

public class LocaleUtilsTest {

    @Test
    public void testGetLocale_englishLanguageCode_returnsEnglishLocale() {
        Language language = new Language("en");
        Locale locale = LocaleUtils.getLocale(language);
        assertNotNull(locale);
        assertEquals("en", locale.getLanguage());
    }

    @Test
    public void testGetLocale_frenchLanguageCode_returnsFrenchLocale() {
        Language language = new Language("fr");
        Locale locale = LocaleUtils.getLocale(language);
        assertNotNull(locale);
        assertEquals("fr", locale.getLanguage());
    }

    @Test
    public void testGetLocale_germanLanguageCode_returnsGermanLocale() {
        Language language = new Language("de");
        Locale locale = LocaleUtils.getLocale(language);
        assertNotNull(locale);
        assertEquals("de", locale.getLanguage());
    }

    @Test
    public void testGetLocale_spanishLanguageCode_returnsSpanishLocale() {
        Language language = new Language("es");
        Locale locale = LocaleUtils.getLocale(language);
        assertNotNull(locale);
        assertEquals("es", locale.getLanguage());
    }
}
