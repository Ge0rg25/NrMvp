package ru.netrunner.coursesmvp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import ru.netrunner.coursesmvp.dto.CourseDto;
import ru.netrunner.coursesmvp.services.UserService;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/courses")
@Tag(name = "User requests", description = "Запросы для пользователей")
public class UserCourseController {

    UserService userService;

    @Operation(summary = "Получение всех курсов пользователя")
    @PostMapping(value = "/get/all", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCourses(@AuthenticationPrincipal Jwt jwt) {
        return userService.getUserCourses(jwt.getSubject());
    }

    @Operation(summary = "Получение курса пользователя по id")
    @PostMapping(value = "/get", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCourse(@AuthenticationPrincipal Jwt jwt, @RequestBody CourseDto.Request.Get courseDto) {
        return userService.getCourse(jwt.getSubject(), courseDto);
    }


    @Operation(summary = "Получение всех статей из курса по id")
    @PostMapping(value = "/get/articles", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getArticles(@RequestBody CourseDto.Request.Get courseDto) {
        return userService.getArticles(courseDto);
    }

}
