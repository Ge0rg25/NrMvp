package ru.netrunner.coursesmvp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
import ru.netrunner.coursesmvp.dto.objects.ArticleDto;
import ru.netrunner.coursesmvp.dto.rules.ArticleValidationRules;
import ru.netrunner.coursesmvp.services.EditorArticleService;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/editor/articles")
@ApiResponses(value = {
        @ApiResponse(responseCode = "403", description = "Forbidden", content = {
                @Content(mediaType = "application/json", schema = @Schema(defaultValue = "{\"error\": \"Forbidden\"}"))
        }),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = {
                @Content(mediaType = "application/json", schema = @Schema(defaultValue = "{\"error\": \"Unauthorized\"}"))
        })
})
public class EditorArticleController {

    EditorArticleService articleService;

    @Operation(summary = "Create an article", description = "Creates an article")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ArticleDto.class))
            })
    })
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createArticle(@Validated(ArticleValidationRules.Create.class) @RequestBody ArticleDto articleDto){
        return articleService.createArticle(articleDto);
    }


    @Operation(summary = "Update an article", description = "Updates an article")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated")
    })
    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateArticle(@Validated(ArticleValidationRules.Update.class) @RequestBody ArticleDto articleDto){
        return articleService.updateArticle(articleDto);
    }

    @Operation(summary = "Delete an article", description = "Deletes an article")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted")
    })
    @PostMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteArticle(@Validated(ArticleValidationRules.Delete.class) @RequestBody ArticleDto articleDto){
        return articleService.deleteArticle(articleDto);
    }

    @Operation(summary = "Find all articles in course", description = "Finds all articles in course")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found")
    })
    @PostMapping(value = "/find/by/course", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAllByCourse(@Validated(ArticleValidationRules.FindAll.class) @RequestBody ArticleDto articleDto){
        return articleService.getAllByCourseId(articleDto);
    }

}
