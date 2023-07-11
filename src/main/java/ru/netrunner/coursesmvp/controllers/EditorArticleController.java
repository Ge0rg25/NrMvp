package ru.netrunner.coursesmvp.controllers;

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
public class EditorArticleController {

    EditorArticleService articleService;

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createArticle(@Validated(ArticleValidationRules.Create.class) @RequestBody ArticleDto articleDto){
        return articleService.createArticle(articleDto);
    }


    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateArticle(@Validated(ArticleValidationRules.Update.class) @RequestBody ArticleDto articleDto){
        return articleService.updateArticle(articleDto);
    }

    @PostMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteArticle(@Validated(ArticleValidationRules.Delete.class) @RequestBody ArticleDto articleDto){
        return articleService.deleteArticle(articleDto);
    }

    @PostMapping(value = "/find/by/course", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAllByCourse(@Validated(ArticleValidationRules.FindAll.class) @RequestBody ArticleDto articleDto){
        return articleService.getAllByCourseId(articleDto);
    }

}
