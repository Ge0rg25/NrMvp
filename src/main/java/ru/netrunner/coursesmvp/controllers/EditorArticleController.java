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
import ru.netrunner.coursesmvp.dto.ArticleDto;
import ru.netrunner.coursesmvp.services.ArticleService;



@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/editor/articles")
@Tag(name = "Editor Requests For Edit Articles")
public class EditorArticleController {

    ArticleService articleService;

    @Operation(summary = "Создание новой статьи в курсе")
    @ApiResponse(responseCode = "200", description = "Article Created",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ArticleDto.Response.BaseResponse.class))})
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createArticle(@RequestBody ArticleDto.Request.Create articleDto) {
        return articleService.createArticle(articleDto);
    }

    @Operation(summary = "Обновление статьи в курсе")
    @ApiResponse(responseCode = "200", description = "Article Updated", content = {@Content(schema = @Schema(implementation = ArticleDto.Response.BaseResponse.class))})
    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateArticle(@RequestBody ArticleDto.Request.Update articleDto) {
        return articleService.updateArticle(articleDto);
    }

    @Operation(summary = "Удаление статьи из курса")
    @ApiResponse(responseCode = "200", description = "Article Deleted")
    @PostMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteArticle(@RequestBody ArticleDto.Request.Delete articleDto) {
        return articleService.deleteArticle(articleDto);
    }

    @Operation(summary = "Получение всех статей по id курса")
    @ApiResponse(responseCode = "200", description = "Article Updated", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = ArticleDto.Response.BaseResponse.class)))})
    @PostMapping(value = "/find/by/course", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAllByCourse(@RequestBody ArticleDto.Request.FindAll articleDto) {
        return articleService.getAllByCourseId(articleDto);
    }

}
