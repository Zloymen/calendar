package ru.platiza.service.calendar.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.platiza.service.calendar.service.DataGovService;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
@Slf4j
public class ScheduledService {

    private final DataGovService dataGovService;

    //@Scheduled(cron = "${app.data.cron-update-task}")
    public void autoUpdateCalendar() {
        dataGovService.updateCalendarByYear(LocalDate.now().getYear());
        dataGovService.updateCalendarByYear(LocalDate.now().getYear() + 1);
    }
}
