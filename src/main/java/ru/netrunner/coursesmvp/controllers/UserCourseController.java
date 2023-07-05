package ru.netrunner.coursesmvp.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.netrunner.coursesmvp.dto.objects.GetCourseRequestDto;
import ru.netrunner.coursesmvp.dto.rules.CourseRequestValidationRules;
import ru.netrunner.coursesmvp.services.UserService;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/courses")
public class UserCourseController {

    UserService userService;

    @PostMapping(value = "/get/all", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCourses(@Validated(CourseRequestValidationRules.GetAllCourses.class) @RequestBody GetCourseRequestDto getCoursesRequestDto){
        return userService.getUserCourses(getCoursesRequestDto);
    }

    @PostMapping(value = "/get", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCourse(@Validated(CourseRequestValidationRules.GetAllCourses.class) @RequestBody GetCourseRequestDto getCoursesRequestDto){
        return userService.getCourse(getCoursesRequestDto);
    }

    @PostMapping(value = "/get/articles", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getArticles(@Validated(CourseRequestValidationRules.GetArticles.class) @RequestBody GetCourseRequestDto getCoursesRequestDto){
        return userService.getArticles(getCoursesRequestDto);
    }

}
