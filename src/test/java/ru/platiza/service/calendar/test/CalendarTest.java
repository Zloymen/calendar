package ru.platiza.service.calendar.test;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class CalendarTest {

    @Test
    public void testContains(){

        log.info(String.valueOf("22*".contains("*")));
    }

    @Test
    public void testConvertDate(){

        log.info(String.valueOf("01-01-2018".contains("*")));
    }
}
