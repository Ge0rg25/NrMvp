package ru.netrunner.coursesmvp.errors.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import ru.netrunner.coursesmvp.errors.common.ArticleAlreadyExistsError;
import ru.netrunner.coursesmvp.errors.common.ArticleNotExistsError;
import ru.netrunner.coursesmvp.utils.ErrorUtils;

@RestControllerAdvice
public class ArrticleErrorController {


    @ExceptionHandler(value = {ArticleNotExistsError.class})
    public ResponseEntity<?> courseNotExistsHandler(RuntimeException exception, WebRequest webRequest){
        return ErrorUtils.genereateErrorResponse("article not exits", HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler(value = {ArticleAlreadyExistsError.class})
    public ResponseEntity<?> courseAlreadyExists(RuntimeException exception, WebRequest webRequest){
        return ErrorUtils.genereateErrorResponse("article already exists", HttpStatus.CONFLICT);
    }

}
