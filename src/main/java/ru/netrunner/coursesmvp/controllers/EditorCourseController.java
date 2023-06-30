package ru.netrunner.coursesmvp.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.netrunner.coursesmvp.dto.objects.CourseDto;
import ru.netrunner.coursesmvp.dto.rules.CourseValidationRules;
import ru.netrunner.coursesmvp.services.CourseService;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/editor/courses")
public class EditorCourseController {

    CourseService courseService;

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createCourse(@Validated(CourseValidationRules.Create.class) @RequestBody CourseDto courseDto) {
        return courseService.createCourse(courseDto);
    }

    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateCourse(@Validated(CourseValidationRules.Update.class) @RequestBody CourseDto courseDto) {
        return courseService.updateCourse(courseDto);
    }

    @PostMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteCourse(@Validated(CourseValidationRules.Delete.class) @RequestBody CourseDto courseDto) {
        return courseService.deleteCourse(courseDto);
    }

    @PostMapping(value = "/get/all", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllCourses(){
        return courseService.getAllCourses();
    }

}
