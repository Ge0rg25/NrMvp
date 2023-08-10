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
import ru.netrunner.coursesmvp.dto.LessonDto;
import ru.netrunner.coursesmvp.services.EditorLessonService;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/editor/lessons")
@Tag(name = "Requesrs for edit lessons", description = "Запросы для управления статьями которые используются в оффлайн проекте")
public class EditorLessonController {
    
    EditorLessonService lessonService;

    @Operation(summary = "Создать урок")
    @ApiResponse(responseCode = "200", description = "lesson successfully created",
            content = {@Content(schema = @Schema(implementation = LessonDto.Response.BaseResponse.class))})
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createLesson(@RequestBody LessonDto.Request.Create LessonDto) {
        return lessonService.createLesson(LessonDto);
    }

    @Operation(summary = "обновить урок")
    @ApiResponse(responseCode = "200", description = "lesson successfully updated",
            content = {@Content(schema = @Schema(implementation = LessonDto.Response.BaseResponse.class))})
    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateLesson(@RequestBody LessonDto.Request.Update LessonDto) {
        return lessonService.updateLesson(LessonDto);
    }

    @Operation(summary = "удалить урок")
    @ApiResponse(responseCode = "200", description = "lesson successfully deleted",
            content = {@Content(schema = @Schema(implementation = LessonDto.Response.BaseResponse.class))})
    @PostMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteLesson(@RequestBody LessonDto.Request.Delete LessonDto) {
        return lessonService.deleteLesson(LessonDto);
    }

    @Operation(summary = "получение всех уроков")
    @ApiResponse(responseCode = "200", description = "list of lessons",
            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = LessonDto.Response.BaseResponse.class)))})
    @PostMapping(value = "/get/all", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllLessons(){
        return lessonService.getAllLessons();
    }
}
