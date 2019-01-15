package ru.platiza.service.calendar.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.platiza.service.calendar.CalendarApp;

@TestPropertySource(locations = {"classpath:application-test.yml"})
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {CalendarApp.class})
@AutoConfigureMockMvc
public class CalendarTest {

    @Test
    public void contextLoads() {
    }
}
