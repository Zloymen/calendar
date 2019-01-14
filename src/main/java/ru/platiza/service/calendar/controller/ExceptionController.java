package ru.platiza.service.calendar.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.platiza.service.calendar.dto.ErrorDetails;
import ru.platiza.service.calendar.error.CalendarError;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@Slf4j(topic = "ERRORS")
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) {

        ErrorDetails errorDetails;
        if(ex instanceof CalendarError) {
            errorDetails = new ErrorDetails( "KNOWN", ((CalendarError) ex).getErrorEnum().getDesc());
        }else{
            errorDetails = new ErrorDetails( "UNKNOWN", request.getDescription(false));
        }

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
