package ru.netrunner.coursesmvp.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.netrunner.coursesmvp.dto.objects.GetCourseRequestDto;
import ru.netrunner.coursesmvp.dto.objects.UserDto;
import ru.netrunner.coursesmvp.dto.rules.CourseRequestValidationRules;
import ru.netrunner.coursesmvp.dto.rules.UserValidationRules;
import ru.netrunner.coursesmvp.services.UserService;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/user/courses")
public class UserCourseController {

    UserService userService;

    @PostMapping(value = "/getall", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCourses(@Validated(CourseRequestValidationRules.GetAllCourses.class) @RequestBody GetCourseRequestDto getCoursesRequestDto){
        return userService.getUserCourses(getCoursesRequestDto);
    }

    @PostMapping(value = "/get", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCourse(@Validated(CourseRequestValidationRules.GetAllCourses.class) @RequestBody GetCourseRequestDto getCoursesRequestDto){
        return userService.getCourse(getCoursesRequestDto);
    }

    @PostMapping(value = "/articles/get", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getArticle(@Validated(CourseRequestValidationRules.GetArticle.class) @RequestBody GetCourseRequestDto getCoursesRequestDto){
        return userService.getArticle(getCoursesRequestDto);
    }

}
