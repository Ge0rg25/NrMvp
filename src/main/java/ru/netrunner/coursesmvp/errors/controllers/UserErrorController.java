package ru.netrunner.coursesmvp.errors.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import ru.netrunner.coursesmvp.errors.common.LessonAlreadyExistsError;
import ru.netrunner.coursesmvp.errors.common.LessonNotExistsError;
import ru.netrunner.coursesmvp.errors.common.UserNotExistsException;
import ru.netrunner.coursesmvp.utils.ErrorUtils;

@RestControllerAdvice
public class UserErrorController {



    @ExceptionHandler(value = {UserNotExistsException.class})
    public ResponseEntity<?> courseAlreadyExists(RuntimeException exception, WebRequest webRequest){
        return ErrorUtils.genereateErrorResponse("user not exists", HttpStatus.NOT_FOUND);
    }

}
