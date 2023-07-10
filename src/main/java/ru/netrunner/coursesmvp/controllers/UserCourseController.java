package ru.netrunner.coursesmvp.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.netrunner.coursesmvp.dto.objects.CourseDto;
import ru.netrunner.coursesmvp.dto.objects.GetCourseRequestDto;
import ru.netrunner.coursesmvp.dto.rules.CourseRequestValidationRules;
import ru.netrunner.coursesmvp.dto.rules.CourseValidationRules;
import ru.netrunner.coursesmvp.services.UserService;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/courses")
public class UserCourseController {

    UserService userService;

    @PostMapping(value = "/get/all", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCourses(@AuthenticationPrincipal Jwt jwt) {
        return userService.getUserCourses(jwt.getSubject());
    }

    @PostMapping(value = "/get", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCourse(@AuthenticationPrincipal Jwt jwt, @Validated(CourseValidationRules.Get.class) @RequestBody CourseDto courseDto) {
        return userService.getCourse(jwt.getSubject(), courseDto);
    }

    @PostMapping(value = "/get/articles", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getArticles(@AuthenticationPrincipal Jwt jwt, @Validated(CourseValidationRules.Get.class) @RequestBody CourseDto courseDto) {
        return userService.getArticles(jwt.getSubject(), courseDto);
    }

}
