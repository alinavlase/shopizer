package com.salesmanager.test.shop.util;

import com.salesmanager.shop.utils.GeoLocationUtils;

import org.junit.Test;
import javax.servlet.http.HttpServletRequest;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GeoLocationUtilsTest {

    @Test
    public void testGetClientIpAddress_xForwardedForPresent_returnsForwardedIp() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getHeader("X-Forwarded-For")).thenReturn("192.168.1.100");

        String result = GeoLocationUtils.getClientIpAddress(request);

        assertEquals("192.168.1.100", result);
    }

    @Test
    public void testGetClientIpAddress_xForwardedForAbsent_fallsBackToNextHeader() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getHeader("X-Forwarded-For")).thenReturn(null);
        when(request.getHeader("Proxy-Client-IP")).thenReturn("10.0.0.5");

        String result = GeoLocationUtils.getClientIpAddress(request);

        assertEquals("10.0.0.5", result);
    }

    @Test
    public void testGetClientIpAddress_xForwardedForUnknown_fallsBackToNextHeader() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getHeader("X-Forwarded-For")).thenReturn("unknown");
        when(request.getHeader("Proxy-Client-IP")).thenReturn("10.0.0.9");

        String result = GeoLocationUtils.getClientIpAddress(request);

        assertEquals("10.0.0.9", result);
    }

    @Test
    public void testGetClientIpAddress_allHeadersAbsent_returnsRemoteAddr() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getHeader(anyString())).thenReturn(null);
        when(request.getRemoteAddr()).thenReturn("127.0.0.1");

        String result = GeoLocationUtils.getClientIpAddress(request);

        assertEquals("127.0.0.1", result);
    }

    @Test
    public void testGetClientIpAddress_xForwardedForEmpty_skipsToNextHeader() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getHeader("X-Forwarded-For")).thenReturn("");
        when(request.getHeader("Proxy-Client-IP")).thenReturn(null);
        when(request.getHeader("WL-Proxy-Client-IP")).thenReturn(null);
        when(request.getHeader("HTTP_X_FORWARDED_FOR")).thenReturn(null);
        when(request.getHeader("HTTP_X_FORWARDED")).thenReturn(null);
        when(request.getHeader("HTTP_X_CLUSTER_CLIENT_IP")).thenReturn(null);
        when(request.getHeader("HTTP_CLIENT_IP")).thenReturn(null);
        when(request.getHeader("HTTP_FORWARDED_FOR")).thenReturn(null);
        when(request.getHeader("HTTP_FORWARDED")).thenReturn(null);
        when(request.getHeader("HTTP_VIA")).thenReturn(null);
        when(request.getHeader("REMOTE_ADDR")).thenReturn(null);
        when(request.getRemoteAddr()).thenReturn("172.16.0.1");

        String result = GeoLocationUtils.getClientIpAddress(request);

        assertEquals("172.16.0.1", result);
    }
}
