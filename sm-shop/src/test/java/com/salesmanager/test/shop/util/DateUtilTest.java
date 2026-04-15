package com.salesmanager.test.shop.util;

import com.salesmanager.shop.utils.DateUtil;

import org.junit.Test;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import static org.junit.Assert.*;

public class DateUtilTest {

    @Test
    public void testGenerateTimeStamp_notNullAndMatchesPattern() {
        String ts = DateUtil.generateTimeStamp();
        assertNotNull(ts);
        assertTrue("Timestamp must be 14 chars (yyyyMMddHHmmSS)", ts.length() == 14);
    }

    @Test
    public void testFormatDate_specificDate() throws Exception {
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2023-07-15");
        String result = DateUtil.formatDate(date);
        assertEquals("2023-07-15", result);
    }

    @Test
    public void testFormatDate_nullDefaultsToToday() {
        String result = DateUtil.formatDate(null);
        assertNotNull(result);
        String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        assertEquals(today, result);
    }

    @Test
    public void testFormatYear_specificDate() throws Exception {
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-01");
        String result = DateUtil.formatYear(date);
        assertEquals("2020", result);
    }

    @Test
    public void testFormatYear_null_returnsNull() {
        assertNull(DateUtil.formatYear(null));
    }

    @Test
    public void testFormatLongDate_notNull() throws Exception {
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2023-12-25");
        String result = DateUtil.formatLongDate(date);
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    public void testFormatLongDate_null_returnsNull() {
        assertNull(DateUtil.formatLongDate(null));
    }

    @Test
    public void testFormatDateMonthString_null_returnsNull() {
        assertNull(DateUtil.formatDateMonthString(null));
    }

    @Test
    public void testGetDate_fromString() throws Exception {
        Date result = DateUtil.getDate("2023-05-10");
        Calendar cal = Calendar.getInstance();
        cal.setTime(result);
        assertEquals(2023, cal.get(Calendar.YEAR));
        assertEquals(4, cal.get(Calendar.MONTH)); // May is 4 (0-based)
        assertEquals(10, cal.get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void testGetDate_returnsCurrentTime() {
        Date before = new Date(System.currentTimeMillis() - 1000);
        Date result = DateUtil.getDate();
        Date after = new Date(System.currentTimeMillis() + 1000);
        assertTrue("Returned date should be after 'before'", result.after(before) || result.equals(before));
        assertTrue("Returned date should be before 'after'", result.before(after) || result.equals(after));
    }

    @Test
    public void testAddDaysToCurrentDate_positive() {
        Date tomorrow = DateUtil.addDaysToCurrentDate(1);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        // Allow 1-second tolerance
        long diff = Math.abs(tomorrow.getTime() - cal.getTime().getTime());
        assertTrue("Date should be approximately tomorrow", diff < 1000);
    }

    @Test
    public void testAddDaysToCurrentDate_negative() {
        Date yesterday = DateUtil.addDaysToCurrentDate(-1);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        long diff = Math.abs(yesterday.getTime() - cal.getTime().getTime());
        assertTrue("Date should be approximately yesterday", diff < 1000);
    }

    @Test
    public void testGetPresentDate_matchesToday() {
        String result = DateUtil.getPresentDate();
        String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        assertEquals(today, result);
    }

    @Test
    public void testGetPresentYear_matchesCurrentYear() {
        String result = DateUtil.getPresentYear();
        String year = new SimpleDateFormat("yyyy").format(new Date());
        assertEquals(year, result);
    }

    @Test
    public void testDateBeforeEqualsDate_firstBeforeSecond_returnsTrue() throws Exception {
        Date first = new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-01");
        Date second = new SimpleDateFormat("yyyy-MM-dd").parse("2023-01-01");
        assertTrue(DateUtil.dateBeforeEqualsDate(first, second));
    }

    @Test
    public void testDateBeforeEqualsDate_firstAfterSecond_returnsFalse() throws Exception {
        Date first = new SimpleDateFormat("yyyy-MM-dd").parse("2025-01-01");
        Date second = new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-01");
        assertFalse(DateUtil.dateBeforeEqualsDate(first, second));
    }

    @Test
    public void testDateBeforeEqualsDate_equalDates_returnsTrue() throws Exception {
        Date first = new SimpleDateFormat("yyyy-MM-dd").parse("2022-06-15");
        Date second = new SimpleDateFormat("yyyy-MM-dd").parse("2022-06-15");
        assertTrue(DateUtil.dateBeforeEqualsDate(first, second));
    }

    @Test
    public void testDateBeforeEqualsDate_firstNull_returnsTrue() throws Exception {
        Date second = new SimpleDateFormat("yyyy-MM-dd").parse("2022-06-15");
        assertTrue(DateUtil.dateBeforeEqualsDate(null, second));
    }

    @Test
    public void testDateBeforeEqualsDate_secondNull_returnsTrue() throws Exception {
        Date first = new SimpleDateFormat("yyyy-MM-dd").parse("2022-06-15");
        assertTrue(DateUtil.dateBeforeEqualsDate(first, null));
    }

    @Test
    public void testDateBeforeEqualsDate_bothNull_returnsTrue() {
        assertTrue(DateUtil.dateBeforeEqualsDate(null, null));
    }
}
