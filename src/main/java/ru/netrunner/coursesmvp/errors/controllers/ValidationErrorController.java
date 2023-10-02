package ru.netrunner.coursesmvp.errors.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.netrunner.coursesmvp.dto.ErrorDto;

import java.util.Objects;


@RestControllerAdvice
public class ValidationErrorController {


    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<?> handleException(MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest().body(new ErrorDto.Response.ValidationErrorResponse(Objects.requireNonNull(e.getBindingResult().getFieldError()).getField() + " " + Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage()));
    }
}
