package ru.platiza.service.calendar.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.platiza.service.calendar.dao.HolidayDao;
import ru.platiza.service.calendar.dto.AnswerDto;
import ru.platiza.service.calendar.dto.HolidayDto;
import ru.platiza.service.calendar.entity.Holiday;
import ru.platiza.service.calendar.enums.TypeDay;
import ru.platiza.service.calendar.error.CalendarError;
import ru.platiza.service.calendar.error.CalendarErrorEnum;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.web.util.UriComponentsBuilder.fromHttpUrl;

@Service
@RequiredArgsConstructor
@Slf4j
public class DataGovServiceImp implements DataGovService {

    private final RestTemplate template;

    private final HolidayDao holidayDao;

    @Value("${app.data.url}")
    private String url;

    @Value("${app.data.token}")
    private String token;


    @Transactional
    @CacheEvict(value = {"calendarByDayBetween", "checkDay"}, allEntries = true)
    @Override
    public void updateCalendarByYear(Integer year) {
        UriComponentsBuilder builder = fromHttpUrl(url).queryParam("access_token", token).queryParam("search", year);

        AnswerDto[] dtos = template.getForObject(builder.toUriString(), AnswerDto[].class);


        if (dtos.length == 0) throw new CalendarError(CalendarErrorEnum.GOV_DATA_NOT_FOUND);

        AnswerDto dto = dtos[0];

        List<HolidayDto> newHolidayDto = new ArrayList<>();

        newHolidayDto.addAll(getMonthHoliday(Arrays.stream(dto.getMonth1().split(",")), year, 1));
        newHolidayDto.addAll(getMonthHoliday(Arrays.stream(dto.getMonth2().split(",")), year, 2));
        newHolidayDto.addAll(getMonthHoliday(Arrays.stream(dto.getMonth3().split(",")), year, 3));
        newHolidayDto.addAll(getMonthHoliday(Arrays.stream(dto.getMonth4().split(",")), year, 4));
        newHolidayDto.addAll(getMonthHoliday(Arrays.stream(dto.getMonth5().split(",")), year, 5));
        newHolidayDto.addAll(getMonthHoliday(Arrays.stream(dto.getMonth6().split(",")), year, 6));
        newHolidayDto.addAll(getMonthHoliday(Arrays.stream(dto.getMonth7().split(",")), year, 7));
        newHolidayDto.addAll(getMonthHoliday(Arrays.stream(dto.getMonth8().split(",")), year, 8));
        newHolidayDto.addAll(getMonthHoliday(Arrays.stream(dto.getMonth9().split(",")), year, 9));
        newHolidayDto.addAll(getMonthHoliday(Arrays.stream(dto.getMonth10().split(",")), year, 10));
        newHolidayDto.addAll(getMonthHoliday(Arrays.stream(dto.getMonth11().split(",")), year, 11));
        newHolidayDto.addAll(getMonthHoliday(Arrays.stream(dto.getMonth12().split(",")), year, 12));


        List<Holiday> oldHoliday = holidayDao.getAllByYear(year);

        Map<LocalDate, Holiday> holidayMap = oldHoliday.stream().collect(Collectors.toMap(Holiday::getDay, holiday -> holiday));

        Set<LocalDate> dates = newHolidayDto.stream().map(HolidayDto::getDay).collect(Collectors.toSet());

        List<Holiday> delHoliday = oldHoliday.stream().filter(item -> !dates.contains(item.getDay())).collect(Collectors.toList());

        List<Holiday> saveHoliday =
                newHolidayDto.stream().map(holidayDto -> {
                    Holiday holiday = holidayMap.get(holidayDto.getDay());
                    if (holiday == null) holiday = new Holiday();
                    holiday.refresh(holidayDto);
                    return holiday;
                }).collect(Collectors.toList());

        holidayDao.deleteInBatch(delHoliday);

        holidayDao.saveAll(saveHoliday);
    }

    private List<HolidayDto> getMonthHoliday(Stream<String> days, Integer year, Integer month) {
        return days.map(item -> createHoliday(year, month, item)).collect(Collectors.toList());
    }



    private HolidayDto createHoliday(Integer year, Integer month, String day) {

        TypeDay type = TypeDay.typeDaysWithoutHoliday().stream()
                .filter(item -> day.contains(item.getSign()))
                .findFirst().orElse(TypeDay.HOLIDAY);

        return new HolidayDto(LocalDate.of(year, month, Integer.valueOf(day.replace(type.getSign(), ""))), type);
    }

}
