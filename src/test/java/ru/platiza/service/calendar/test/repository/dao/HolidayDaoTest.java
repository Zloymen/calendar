package ru.platiza.service.calendar.test.repository.dao;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.platiza.service.calendar.dao.HolidayDao;
import ru.platiza.service.calendar.entity.Holiday;
import ru.platiza.service.calendar.test.repository.AbstractRepositoryTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

@DatabaseSetup("classpath:dataset/holiday_calendar_table.xml")
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:dataset/holiday_calendar_table.xml")
public class HolidayDaoTest extends AbstractRepositoryTest {

    @Autowired
    private HolidayDao holidayDao;

    @Test
    public void findExisting(){
        List<Holiday> holidays = holidayDao.getAllByYear(2018);

        assertThat(holidays, hasSize(6));
    }

    @Test
    public void findNotExisting(){
        List<Holiday> holidays = holidayDao.getAllByYear(2019);

        assertThat(holidays, hasSize(0));
    }

    @Test
    public void isHolidayExisting(){
        Optional<Holiday> holidayOptional = holidayDao.isHoliday(LocalDate.of(2018,1,1));

        assertThat(holidayOptional.isPresent(), equalTo(true));
    }

    @Test
    public void isNotHolidayExisting(){
        Optional<Holiday> holidayOptional = holidayDao.isHoliday(LocalDate.of(2018,2,22));

        assertThat(holidayOptional.isPresent(), equalTo(false));
    }

    @Test
    public void getBetweenHolidays(){
        List<Holiday> holidays = holidayDao.getAllByDayBetween(LocalDate.of(2018, 1, 1), LocalDate.of(2018, 2, 1));

        assertThat(holidays, hasSize(5));
    }

    @Test
    public void getNotBetweenHolidays(){
        List<Holiday> holidays = holidayDao.getAllByDayBetween(LocalDate.of(2018, 3, 1), LocalDate.of(2018, 4, 1));

        assertThat(holidays, hasSize(0));
    }
}
