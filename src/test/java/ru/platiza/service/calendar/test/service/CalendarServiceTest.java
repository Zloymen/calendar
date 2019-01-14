package ru.platiza.service.calendar.test.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ru.platiza.service.calendar.dao.HolidayDao;
import ru.platiza.service.calendar.service.CalendarService;
import ru.platiza.service.calendar.service.CalendarServiceImp;
import ru.platiza.service.calendar.service.ConverterService;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static ru.platiza.service.calendar.test.data.TestData.*;


public class CalendarServiceTest {

    private CalendarService service;

    @Mock
    private HolidayDao holidayDao;

    @Mock
    private ConverterService converterService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        Mockito.when(holidayDao.isHoliday(FIRST_DAY_JANUARY)).thenReturn(Optional.of(HOLIDAY));

        Mockito.when(holidayDao.getAllByDayBetween(FIRST_DAY_JANUARY, LAST_DAY_JANUARY))
                .thenReturn(Arrays.asList(HOLIDAY));

        Mockito.when(converterService.convertHolidayEntityToDto(HOLIDAY))
                .thenReturn(HOLIDAY_DTO);

        service = new CalendarServiceImp(holidayDao,  converterService);

    }

    @Test
    public void checkHolidayTest(){
        assertThat(service.checkHoliday(FIRST_DAY_JANUARY), equalTo(true));
    }


    @Test
    public void getHolidayDtoBetweenTest(){
        assertThat(service.getHolidayDtoBetween(FIRST_DAY_JANUARY, LAST_DAY_JANUARY), hasSize(1));
    }
}
