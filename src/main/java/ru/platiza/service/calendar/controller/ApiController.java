package ru.platiza.service.calendar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.platiza.service.calendar.dto.HolidayDto;
import ru.platiza.service.calendar.service.CalendarService;
import ru.platiza.service.calendar.service.DataGovService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApiController {

    private final DataGovService govService;

    private final CalendarService calendarService;

    @GetMapping("/update/{year}")
    public String getGovno(@PathVariable Integer year){

        govService.updateCalendarByYear(year);

        return "done";
    }

    @GetMapping("/day/between/")
    public List<HolidayDto> getDayBetween(@RequestParam LocalDate first, @RequestParam LocalDate last){
        return calendarService.getHolidayDtoBetween(first, last);
    }

    @GetMapping("/")
    public Boolean getDayBetween(@RequestParam LocalDate day){
        return calendarService.checkHoliday(day);
    }

}
