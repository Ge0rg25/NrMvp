package ru.netrunner.coursesmvp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import ru.netrunner.coursesmvp.dto.ArticleDto;
import ru.netrunner.coursesmvp.dto.CourseDto;
import ru.netrunner.coursesmvp.services.UserService;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/courses")
@Tag(name = "User Requests", description = "Запросы для пользователей")
public class UserCourseController {

    UserService userService;

    @Operation(summary = "Получение всех курсов пользователя")
    @ApiResponse(responseCode = "200", description = "courses",
            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = CourseDto.Response.BaseResponse.class)))})
    @PostMapping(value = "/get/all", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCourses(@AuthenticationPrincipal Jwt jwt) {
        return userService.getUserCourses(jwt.getSubject());
    }

    @Operation(summary = "Получение курса пользователя по id")
    @ApiResponse(responseCode = "200", description = "course",
            content = {@Content(schema = @Schema(implementation = CourseDto.Response.BaseResponse.class))})
    @PostMapping(value = "/get", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCourse(@AuthenticationPrincipal Jwt jwt, @RequestBody CourseDto.Request.Get courseDto) {
        return userService.getCourse(jwt.getSubject(), courseDto);
    }


    @Operation(summary = "Получение всех статей из курса по id")
    @ApiResponse(responseCode = "200", description = "articles",
            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = ArticleDto.Response.BaseResponse.class)))})
    @PostMapping(value = "/get/articles", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getArticles(@RequestBody CourseDto.Request.Get courseDto) {
        return userService.getArticles(courseDto);
    }

}
