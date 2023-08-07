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
import ru.netrunner.coursesmvp.repositories.ArticleRepository;
import ru.netrunner.coursesmvp.repositories.CourseRepository;

import java.util.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ArticleService {

    ArticleRepository articleRepository;
    CourseRepository courseRepository;


    @Transactional
    public ResponseEntity<?> createArticle(ArticleDto.Request.Create articleDto) {
        CourseEntity courseEntity = courseRepository.findById(articleDto.courseId()).orElseThrow(CourseNotExistsError::new);
        if (articleRepository.existsByCourseAndTitle(courseEntity, articleDto.title())) {
            throw new ArticleAlreadyExistsError();
        }
        ArticleEntity articleEntity = ArticleEntity.builder()
                .title(articleDto.title())
                .description(articleDto.description())
                .body(articleDto.body())
                .build();
        articleEntity.setCourse(courseEntity);
        articleRepository.save(articleEntity);
        ArticleDto.Response.SingleArticle responseDto = ArticleDto.Response.SingleArticle.builder()
                .id(articleEntity.getId())
                .title(articleEntity.getTitle())
                .description(articleEntity.getDescription())
                .body(articleEntity.getBody())
                .build();
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }


    @Transactional
    public ResponseEntity<?> updateArticle(ArticleDto.Request.Update articleDto) {
        ArticleEntity articleEntity = articleRepository.findById(articleDto.id()).orElseThrow(ArticleNotExistsError::new);
        articleEntity.setTitle(articleDto.title());
        articleEntity.setDescription(articleDto.description());
        articleEntity.setBody(articleDto.body());
        articleRepository.save(articleEntity);
        ArticleDto.Response.SingleArticle responseDto = ArticleDto.Response.SingleArticle.builder()
                .id(articleEntity.getId())
                .title(articleEntity.getTitle())
                .description(articleEntity.getDescription())
                .body(articleEntity.getBody())
                .build();
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }


    @Transactional
    public ResponseEntity<?> deleteArticle(ArticleDto.Request.Delete articleDto) {
        ArticleEntity articleEntity = articleRepository.findById(articleDto.id()).orElseThrow(ArticleNotExistsError::new);
        articleRepository.delete(articleEntity);
        return new ResponseEntity<>(Map.of("status", "success"), HttpStatus.OK);
    }

    public ResponseEntity<?> getAllByCourseId(ArticleDto.Request.FindAll articleDto) {
        CourseEntity courseEntity = courseRepository.findById(articleDto.courseId()).orElseThrow(CourseNotExistsError::new);
        List<ArticleEntity> articleEntities = courseEntity.getArticles();
        List<ArticleDto.Response.SingleArticle> response = new ArrayList<>();

        for (ArticleEntity articleEntity : articleEntities) {
            response.add(ArticleDto.Response.SingleArticle.builder()
                    .id(articleEntity.getId())
                    .title(articleEntity.getTitle())
                    .description(articleEntity.getDescription())
                    .body(articleEntity.getBody())
                    .build());
        }
        return new ResponseEntity<>(response.toArray(), HttpStatus.OK);
    }
}

