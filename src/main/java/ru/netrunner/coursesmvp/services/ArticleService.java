package ru.netrunner.coursesmvp.services;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.netrunner.coursesmvp.dto.ArticleDto;
import ru.netrunner.coursesmvp.errors.common.ArticleAlreadyExistsError;
import ru.netrunner.coursesmvp.errors.common.ArticleNotExistsError;
import ru.netrunner.coursesmvp.errors.common.CourseNotExistsError;
import ru.netrunner.coursesmvp.models.ArticleEntity;
import ru.netrunner.coursesmvp.models.CourseEntity;
import ru.netrunner.coursesmvp.models.ModuleEntity;
import ru.netrunner.coursesmvp.repositories.ArticleRepository;
import ru.netrunner.coursesmvp.repositories.CourseRepository;
import ru.netrunner.coursesmvp.repositories.ModuleRepository;

import java.util.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ArticleService {

    ArticleRepository articleRepository;
    ModuleRepository moduleRepository;

    @Transactional
    public ResponseEntity<?> createArticle(ArticleDto.Request.Create articleDto) {
        ModuleEntity moduleEntity = moduleRepository.findById(articleDto.moduleId()).orElseThrow(CourseNotExistsError::new);
        if (articleRepository.existsByModuleAndTitle(moduleEntity, articleDto.title())) {
            throw new ArticleAlreadyExistsError();
        }
        ArticleEntity articleEntity = ArticleEntity.builder()
                .title(articleDto.title())
                .description(articleDto.description())
                .body(articleDto.body())
                .build();
        articleEntity.setModule(moduleEntity);
        articleRepository.save(articleEntity);
        ArticleDto.Response.BaseResponse responseDto = new ArticleDto.Response.BaseResponse(
                articleEntity.getTitle(),
                articleEntity.getDescription(),
                articleEntity.getBody(),
                articleEntity.getId()

        );
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }


    @Transactional
    public ResponseEntity<?> updateArticle(ArticleDto.Request.Update articleDto) {
        ArticleEntity articleEntity = articleRepository.findById(articleDto.id()).orElseThrow(ArticleNotExistsError::new);
        articleEntity.setTitle(articleDto.title());
        articleEntity.setDescription(articleDto.description());
        articleEntity.setBody(articleDto.body());
        articleRepository.save(articleEntity);
        ArticleDto.Response.BaseResponse responseDto = new ArticleDto.Response.BaseResponse(
                articleEntity.getTitle(),
                articleEntity.getDescription(),
                articleEntity.getBody(),
                articleEntity.getId()

        );
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }


    @Transactional
    public ResponseEntity<?> deleteArticle(ArticleDto.Request.Delete articleDto) {
        ArticleEntity articleEntity = articleRepository.findById(articleDto.id()).orElseThrow(ArticleNotExistsError::new);
        articleRepository.delete(articleEntity);
        return new ResponseEntity<>(Map.of("status", "success"), HttpStatus.OK);
    }

    public ResponseEntity<?> getAllByModuleId(ArticleDto.Request.FindAll articleDto) {
        ModuleEntity moduleEntity = moduleRepository.findById(articleDto.moduleId()).orElseThrow(CourseNotExistsError::new);
        List<ArticleEntity> articleEntities = moduleEntity.getArticles();
        List<ArticleDto.Response.BaseResponse> response = new ArrayList<>();

        for (ArticleEntity articleEntity : articleEntities) {
            response.add(new ArticleDto.Response.BaseResponse(
                    articleEntity.getTitle(),
                    articleEntity.getDescription(),
                    articleEntity.getBody(),
                    articleEntity.getId()

            ));
        }
        return new ResponseEntity<>(response.toArray(), HttpStatus.OK);
    }
}

