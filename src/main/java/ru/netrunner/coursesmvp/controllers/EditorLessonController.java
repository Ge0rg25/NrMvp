package ru.netrunner.Lessonsmvp.controllers;

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
import ru.netrunner.coursesmvp.dto.objects.LessonDto;
import ru.netrunner.coursesmvp.dto.rules.LessonValidationRules;
import ru.netrunner.coursesmvp.services.EditorLessonService;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/lessons")
public class EditorLessonController {
    
    EditorLessonService lessonService;

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createLesson(@Validated(LessonValidationRules.Create.class) @RequestBody LessonDto LessonDto) {
        return lessonService.createLesson(LessonDto);
    }

    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateLesson(@Validated(LessonValidationRules.Update.class) @RequestBody LessonDto LessonDto) {
        return lessonService.updateLesson(LessonDto);
    }

    @PostMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteLesson(@Validated(LessonValidationRules.Delete.class) @RequestBody LessonDto LessonDto) {
        return lessonService.deleteLesson(LessonDto);
    }

    @PostMapping(value = "/get/all", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllLessons(){
        return lessonService.getAllLessons();
    }
}
