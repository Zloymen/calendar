package ru.platiza.service.calendar.test.repository;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import ru.platiza.service.calendar.CalendarApp;

@TestPropertySource(locations = {"classpath:application-test.yml"})
@TestExecutionListeners(DbUnitTestExecutionListener.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {CalendarApp.class})
@DirtiesContext
public class AbstractRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
}
