package ru.platiza.service.calendar.test.data;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import ru.platiza.service.calendar.dto.AnswerDto;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

@Slf4j
public class DataTest {

    private String data2019 = "[\n" +
            "  {\n" +
            "    \"Год/Месяц\": \"2019\",\n" +
            "    \"Январь\": \"1,2,3,4,5,6,7,8,12,13,19,20,26,27\",\n" +
            "    \"Февраль\": \"2,3,9,10,16,17,22*,23,24\",\n" +
            "    \"Март\": \"2,3,7*,8,9,10,16,17,23,24,30,31\",\n" +
            "    \"Апрель\": \"6,7,13,14,20,21,27,28,30*\",\n" +
            "    \"Май\": \"1,2,3,4,5,8*,9,10,11,12,18,19,25,26\",\n" +
            "    \"Июнь\": \"1,2,8,9,11*,12,15,16,22,23,29,30\",\n" +
            "    \"Июль\": \"6,7,13,14,20,21,27,28\",\n" +
            "    \"Август\": \"3,4,10,11,17,18,24,25,31\",\n" +
            "    \"Сентябрь\": \"1,7,8,14,15,21,22,28,29\",\n" +
            "    \"Октябрь\": \"5,6,12,13,19,20,26,27\",\n" +
            "    \"Ноябрь\": \"2,3,4,9,10,16,17,23,24,30\",\n" +
            "    \"Декабрь\": \"1,7,8,14,15,21,22,28,29,31*\",\n" +
            "    \"Всего рабочих дней\": \"247\",\n" +
            "    \"Всего праздничных и выходных дней\": \"118\",\n" +
            "    \"Количество рабочих часов при 40-часовой рабочей неделе\": \"1970\",\n" +
            "    \"Количество рабочих часов при 36-часовой рабочей неделе\": \"1772.4\",\n" +
            "    \"Количество рабочих часов при 24-часовой рабочей неделе\": \"1179.6\"\n" +
            "  }\n" +
            "]";

    private String data2018 = "[\n" +
            "  {\n" +
            "    \"Год/Месяц\": \"2018\",\n" +
            "    \"Январь\": \"1,2,3,4,5,6,7,8,13,14,20,21,27,28\",\n" +
            "    \"Февраль\": \"3,4,10,11,17,18,22*,23,24,25\",\n" +
            "    \"Март\": \"3,4,7*,8,9,10,11,17,18,24,25,31\",\n" +
            "    \"Апрель\": \"1,7,8,14,15,21,22,28*,29,30\",\n" +
            "    \"Май\": \"1,2,5,6,8*,9,12,13,19,20,26,27\",\n" +
            "    \"Июнь\": \"2,3,9*,10,11,12,16,17,23,24,30\",\n" +
            "    \"Июль\": \"1,7,8,14,15,21,22,28,29\",\n" +
            "    \"Август\": \"4,5,11,12,18,19,25,26\",\n" +
            "    \"Сентябрь\": \"1,2,8,9,15,16,22,23,29,30\",\n" +
            "    \"Октябрь\": \"6,7,13,14,20,21,27,28\",\n" +
            "    \"Ноябрь\": \"3,4,5,10,11,17,18,24,25\",\n" +
            "    \"Декабрь\": \"1,2,8,9,15,16,22,23,29*,30,31\",\n" +
            "    \"Всего рабочих дней\": \"247\",\n" +
            "    \"Всего праздничных и выходных дней\": \"118\",\n" +
            "    \"Количество рабочих часов при 40-часовой рабочей неделе\": \"1970\",\n" +
            "    \"Количество рабочих часов при 36-часовой рабочей неделе\": \"1772.4\",\n" +
            "    \"Количество рабочих часов при 24-часовой рабочей неделе\": \"1179.6\"\n" +
            "  }\n" +
            "]";

    private ObjectMapper objectMapper;

    @Before
    public void init(){
        objectMapper = new ObjectMapper();

        JavaTimeModule javaTimeModule = new JavaTimeModule();
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        objectMapper.registerModule(javaTimeModule);
    }

    @Test
    public void dataYear2019test() throws IOException {

        AnswerDto dto = new AnswerDto();
        dto.setMonth1("1,2,3,4,5,6,7,8,12,13,19,20,26,27");
        dto.setMonth2("2,3,9,10,16,17,22*,23,24");
        dto.setMonth3("2,3,7*,8,9,10,16,17,23,24,30,31");
        dto.setMonth4("6,7,13,14,20,21,27,28,30*");
        dto.setMonth5("1,2,3,4,5,8*,9,10,11,12,18,19,25,26");
        dto.setMonth6("1,2,8,9,11*,12,15,16,22,23,29,30");
        dto.setMonth7("6,7,13,14,20,21,27,28");
        dto.setMonth8("3,4,10,11,17,18,24,25,31");
        dto.setMonth9("1,7,8,14,15,21,22,28,29");
        dto.setMonth10("5,6,12,13,19,20,26,27");
        dto.setMonth11("2,3,4,9,10,16,17,23,24,30");
        dto.setMonth12("1,7,8,14,15,21,22,28,29,31*");

        AnswerDto[] answerDtos = objectMapper.readValue(data2019, AnswerDto[].class);

        assertEquals(answerDtos.length, 1);
        assertEquals(answerDtos[0], dto);
    }
}
