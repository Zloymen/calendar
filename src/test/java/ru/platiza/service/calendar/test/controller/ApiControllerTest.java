package ru.platiza.service.calendar.test.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.platiza.service.calendar.CalendarApp;
import ru.platiza.service.calendar.controller.ApiController;
import ru.platiza.service.calendar.service.CalendarService;
import ru.platiza.service.calendar.service.DataGovService;

import java.time.format.DateTimeFormatter;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.platiza.service.calendar.test.data.TestData.FIRST_DAY_JANUARY;
import static ru.platiza.service.calendar.test.data.TestData.TEST_YEAR;

@TestPropertySource(locations = {"classpath:application-test.yml"})
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CalendarApp.class})
@WebAppConfiguration
@DirtiesContext
//@AutoConfigureMockMvc
public class ApiControllerTest {

    //@Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private DateTimeFormatter formatter;

    @Mock
    private DataGovService govService;

    @MockBean
    private CalendarService calendarService;

    @Autowired
    @InjectMocks
    private ApiController controller;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();

        given(this.calendarService.checkHoliday(FIRST_DAY_JANUARY)).willReturn(true);
        //Mockito.doNothing().when(govService).updateCalendarByYear(TEST_YEAR);

        //Mockito.when(govService.updateCalendarByYear(TEST_YEAR)).thenReturn(true);
        //Mockito.verify(govService).updateCalendarByYear(TEST_YEAR);
        Mockito.doAnswer((Answer<Void>) invocationOnMock -> null).when(govService).updateCalendarByYear(TEST_YEAR);

        //Mockito.when(calendarService.checkHoliday(FIRST_DAY_JANUARY)).thenReturn(true);

        //Mockito.when(govService).

        //Mockito.spy(govService).updateCalendarByYear(TEST_YEAR);
    }

    @Test
    public void updateYear() throws Exception {
        mockMvc.perform(post("/api/update/{year}", TEST_YEAR)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void isHoliday() throws Exception {
        mockMvc.perform(get("/api/isHoliday")
                .param("day", FIRST_DAY_JANUARY.format(formatter))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }
}
