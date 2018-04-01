package com.personal.dominykasviecas.popularmovies.util;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class DateUtilsTest {

    @Test
    public void getYearFromDate() {
        assertEquals("1970", DateUtils.getYearFromDate(new Date(0)));
        assertEquals("2015", DateUtils.getYearFromDate(new Date(1420070400000L)));
    }
}