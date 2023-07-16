package ru.netrunner.coursesmvp.errors.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import ru.netrunner.coursesmvp.errors.common.CourseAlreadyExistsError;
import ru.netrunner.coursesmvp.errors.common.CourseNotExistsError;
import ru.netrunner.coursesmvp.utils.ErrorUtils;

@RestControllerAdvice
public class CourseErrorController {

    @ExceptionHandler(value = {CourseNotExistsError.class})
    public ResponseEntity<?> courseNotExistsHandler(RuntimeException exception, WebRequest webRequest){
        return ErrorUtils.genereateErrorResponse("course not exits", HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler(value = {CourseAlreadyExistsError.class})
    public ResponseEntity<?> courseAlreadyExists(RuntimeException exception, WebRequest webRequest){
        return ErrorUtils.genereateErrorResponse("course already exists", HttpStatus.CONFLICT);
    }

}
