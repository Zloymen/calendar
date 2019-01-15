package ru.platiza.service.calendar.test.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockReset;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import ru.platiza.service.calendar.CalendarApp;
import ru.platiza.service.calendar.controller.ApiController;
import ru.platiza.service.calendar.service.CalendarService;
import ru.platiza.service.calendar.service.DataGovService;

import java.time.format.DateTimeFormatter;
import java.util.Collections;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.platiza.service.calendar.test.data.TestData.*;

@TestPropertySource(locations = {"classpath:application-test.yml"})
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CalendarApp.class})
@WebAppConfiguration
@AutoConfigureMockMvc
public class ApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DateTimeFormatter formatter;

    @MockBean(reset = MockReset.BEFORE)
    private DataGovService govService;

    @MockBean(reset = MockReset.BEFORE)
    private CalendarService calendarService;

    @Autowired
    @InjectMocks
    private ApiController controller;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);

        given(calendarService.checkHoliday(FIRST_DAY_JANUARY)).willReturn(true);
        doNothing().when(govService).updateCalendarByYear(TEST_YEAR);

        Mockito.when(calendarService.checkHoliday(FIRST_DAY_JANUARY)).thenReturn(true);
        Mockito.when(calendarService.checkHoliday(NOT_HOLIDAY_DAY)).thenReturn(false);
        Mockito.when(calendarService.getHolidayDtoBetween(FIRST_DAY_JANUARY, LAST_DAY_JANUARY)).thenReturn(Collections.emptyList());

    }

    @Test
    public void updateYear() throws Exception {
        mockMvc.perform(post("/api/update/{year}", TEST_YEAR)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void isHoliday() throws Exception {
        mockMvc.perform(
                get("/api/isHoliday")
                .param("day", FIRST_DAY_JANUARY.format(formatter))
                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    public void isNotHoliday() throws Exception {
        mockMvc.perform(
                get("/api/isHoliday")
                        .param("day", NOT_HOLIDAY_DAY.format(formatter))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }

    @Test
    public void getBetweenHoliday() throws Exception {

        mockMvc.perform(get("/api/day/between")
                .param("first", FIRST_DAY_JANUARY.format(formatter))
                .param("last", LAST_DAY_JANUARY.format(formatter))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

    }
}
