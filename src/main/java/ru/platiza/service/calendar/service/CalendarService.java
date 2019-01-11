package ru.platiza.service.calendar.service;

import ru.platiza.service.calendar.dto.HolidayDto;

import java.time.LocalDate;
import java.util.List;

public interface CalendarService {

    List<HolidayDto> getHolidayDtoBetween(LocalDate first, LocalDate last);

    Boolean checkHoliday(LocalDate day);
}
