package ru.platiza.service.calendar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.platiza.service.calendar.dao.HolidayDao;
import ru.platiza.service.calendar.dto.HolidayDto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CalendarServiceImp implements CalendarService {

    private final HolidayDao holidayDao;

    private final ConverterService converterService;

    @Override
    public List<HolidayDto> getHolidayDtoBetween(LocalDate first, LocalDate last) {
        return holidayDao.getAllByDayBetween(first, last).stream()
                .map(converterService::convertHolidayEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Boolean checkHoliday(LocalDate day) {
        return holidayDao.isHoliday(day).isPresent();
    }
}
