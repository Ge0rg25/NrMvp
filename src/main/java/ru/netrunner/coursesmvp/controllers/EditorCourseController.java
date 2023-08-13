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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.netrunner.coursesmvp.dto.CourseDto;
import ru.netrunner.coursesmvp.services.CourseService;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/editor/courses")
@Tag(name = "Requests For Edit Courses")
public class EditorCourseController {

    CourseService courseService;

    @Operation(summary = "Создание курса")
    @ApiResponse(responseCode = "200", description = "course successfully created",
            content = {@Content(schema = @Schema(implementation = CourseDto.Response.BaseResponse.class))})
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createCourse(@RequestBody CourseDto.Request.Create courseDto) {
        return courseService.createCourse(courseDto);
    }

    @Operation(summary = "Обновление курса")
    @ApiResponse(responseCode = "200", description = "course successfully updated",
            content = {@Content(schema = @Schema(implementation = CourseDto.Response.BaseResponse.class))})
    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateCourse(@RequestBody CourseDto.Request.Update courseDto) {
        return courseService.updateCourse(courseDto);
    }

    @Operation(summary = "Удаление курса")
    @ApiResponse(responseCode = "200", description = "course successfully deleted")
    @PostMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteCourse(@RequestBody CourseDto.Request.Delete courseDto) {
        return courseService.deleteCourse(courseDto);
    }

    @Operation(summary = "получение всех курсов")
    @ApiResponse(responseCode = "200", description = "list of courses",
            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = CourseDto.Response.BaseResponse.class)))})
    @PostMapping(value = "/get/all", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllCourses(){
        return courseService.getAllCourses();
    }

}
