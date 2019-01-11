package ru.platiza.service.calendar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.platiza.service.calendar.enums.TypeDay;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HolidayDto {

    private LocalDate day;

    private TypeDay type;

}