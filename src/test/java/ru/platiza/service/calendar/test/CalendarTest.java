package ru.platiza.service.calendar.test;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import ru.platiza.service.calendar.CalendarApp;

@TestPropertySource(locations = {"classpath:application-test.yml"})
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {CalendarApp.class})
//@WebAppConfiguration
@AutoConfigureMockMvc
public class CalendarTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testContains(){

        //log.info(String.valueOf("22*".contains("*")));
    }

    @Test
    public void testConvertDate(){

        //log.info(String.valueOf("01-01-2018".contains("*")));
    }
}
