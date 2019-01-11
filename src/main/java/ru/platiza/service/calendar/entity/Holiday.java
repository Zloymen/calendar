package ru.platiza.service.calendar.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.platiza.service.calendar.dto.HolidayDto;
import ru.platiza.service.calendar.enums.TypeDay;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "holiday_calendar")
@NoArgsConstructor
@AllArgsConstructor
public class Holiday {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDate day;

    @Enumerated(EnumType.STRING)
    private TypeDay type;

    public void refresh(HolidayDto dto){
        this.day = dto.getDay();
        this.type = dto.getType();
    }

}
