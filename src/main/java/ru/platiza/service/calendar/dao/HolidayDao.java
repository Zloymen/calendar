package ru.platiza.service.calendar.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.platiza.service.calendar.entity.Holiday;
import ru.platiza.service.calendar.enums.TypeDay;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface HolidayDao extends JpaRepository<Holiday, Long> {
    List<Holiday> getAllByDayBetween(LocalDate first, LocalDate last);
    Optional<Holiday> getAllByDayAndTypeIsNot(LocalDate day, TypeDay typeDay);

    @Query(value="select h from Holiday h where h.day=:day and not h.type = ru.platiza.service.calendar.enums.TypeDay.PREHOLIDAY")
    Optional<Holiday> isHoliday(@Param("day") LocalDate day);

    @Query(value = "select * from holiday_calendar h where Extract(YEAR from h.day)=:myYear", nativeQuery = true)
    List<Holiday> getAllByYear(@Param("myYear") Integer year);
}
