package ru.platiza.service.calendar.test.data;

import ru.platiza.service.calendar.dto.HolidayDto;
import ru.platiza.service.calendar.entity.Holiday;
import ru.platiza.service.calendar.enums.TypeDay;

import java.time.LocalDate;

public class TestData {

    public final static LocalDate FIRST_DAY_JANUARY = LocalDate.of(2018, 1, 1);
    public final static LocalDate LAST_DAY_JANUARY = LocalDate.of(2018, 1, 31);

    public final static Holiday HOLIDAY = new Holiday(100500L, FIRST_DAY_JANUARY, TypeDay.HOLIDAY);

    public final static HolidayDto HOLIDAY_DTO = new HolidayDto(FIRST_DAY_JANUARY, TypeDay.HOLIDAY);

    public final static int TEST_YEAR = 2018;
}
