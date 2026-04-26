package com.habitjournal.habit_journal_api.controller.handler;

import com.habitjournal.habit_journal_api.service.exception.DuplicateHabitException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateHabitException.class)
    public ProblemDetail handleDuplicateHabitException(DuplicateHabitException ex) {
        log.warn("No se puede crear un habito con nombre ya existente");

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT,
                ex.getMessage());

        problemDetail.setTitle("Error en validacion");
        problemDetail.setProperty("Timestamp", Instant.now());
        return  problemDetail;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,
                "La validacion fallo en uno o mas campos"
        );

        problemDetail.setTitle("Error de validacion");
        problemDetail.setProperty("Timestamp", Instant.now());

        Map<String, String> errorMap = new HashMap<>();

        ex.getFieldErrors().forEach( error ->
                errorMap.put(error.getField(), error.getDefaultMessage())
        );

        problemDetail.setProperty("FieldErrors", errorMap);
        return problemDetail;
    }

}
