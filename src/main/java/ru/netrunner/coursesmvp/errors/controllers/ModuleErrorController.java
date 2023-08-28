package ru.netrunner.coursesmvp.errors.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import ru.netrunner.coursesmvp.errors.common.CourseAlreadyExistsError;
import ru.netrunner.coursesmvp.errors.common.CourseNotExistsError;
import ru.netrunner.coursesmvp.errors.common.ModuleAlreadyExistsError;
import ru.netrunner.coursesmvp.errors.common.ModuleNotExistsError;
import ru.netrunner.coursesmvp.utils.ErrorUtils;

@RestControllerAdvice
public class ModuleErrorController {

    @ExceptionHandler(value = {ModuleNotExistsError.class})
    public ResponseEntity<?> moduleNotExistsHandler(RuntimeException exception, WebRequest webRequest){
        return ErrorUtils.genereateErrorResponse("module not exits", HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler(value = {ModuleAlreadyExistsError.class})
    public ResponseEntity<?> moduleAlreadyExists(RuntimeException exception, WebRequest webRequest){
        return ErrorUtils.genereateErrorResponse("module already exists", HttpStatus.CONFLICT);
    }

}
