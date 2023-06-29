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
import ru.netrunner.coursesmvp.dto.rules.ValidationRules;
import ru.netrunner.coursesmvp.services.ArticleService;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/editor/articles")
public class EditorArticleController {

    ArticleService articleService;

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createArticle(@Validated(ValidationRules.Create.class) @RequestBody ArticleDto articleDto){
        return articleService.createArticle(articleDto);
    }


    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateArticle(@Validated(ValidationRules.Update.class) @RequestBody ArticleDto articleDto){
        return articleService.updateArticle(articleDto);
    }

    @PostMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteArticle(@Validated(ValidationRules.Delete.class) @RequestBody ArticleDto articleDto){
        return articleService.deleteArticle(articleDto);
    }

    @PostMapping(value = "/find/by/course", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAllByCourse(@Validated(ValidationRules.FindAll.class) @RequestBody ArticleDto articleDto){
        return articleService.getAllByCourseId(articleDto);
    }

}
