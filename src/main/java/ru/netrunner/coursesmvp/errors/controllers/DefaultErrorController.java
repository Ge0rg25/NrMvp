package ru.netrunner.coursesmvp.errors.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import ru.netrunner.coursesmvp.utils.ErrorUtils;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.ForbiddenException;

@RestControllerAdvice
public class DefaultErrorController {
    @ExceptionHandler(value = {ForbiddenException.class})
    public ResponseEntity<?> forbiddenHandler(RuntimeException exception, WebRequest webRequest){
        return ErrorUtils.genereateErrorResponse("Forbidden", HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<?> badRequestHandler(RuntimeException exception, WebRequest webRequest){
        return ErrorUtils.genereateErrorResponse("Bad request", HttpStatus.BAD_REQUEST);
    }
}
