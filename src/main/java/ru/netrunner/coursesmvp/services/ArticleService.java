package ru.netrunner.coursesmvp.services;

import jakarta.persistence.ManyToMany;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.netrunner.coursesmvp.dto.objects.ArticleDto;
import ru.netrunner.coursesmvp.errors.ArticleAlreadyExistsError;
import ru.netrunner.coursesmvp.errors.ArticleNotExistsError;
import ru.netrunner.coursesmvp.errors.CourseNotExistsError;
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
    ModelMapper modelMapper;


    @Transactional
    public ResponseEntity<?> createArticle(ArticleDto articleDto) {
        CourseEntity courseEntity = courseRepository.findById(articleDto.getCourseId()).orElseThrow(CourseNotExistsError::new);
        if (articleRepository.existsByCourseAndTitle(courseEntity, articleDto.getTitle())) {
            throw new ArticleAlreadyExistsError();
        }
        ArticleEntity articleEntity = modelMapper.map(articleDto, ArticleEntity.class);
        articleEntity.setCourse(courseEntity);
        articleRepository.save(articleEntity);
        ArticleDto responseDto = modelMapper.map(articleEntity, ArticleDto.class);
        responseDto.setId(articleEntity.getCourse().getId());
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }


    @Transactional
    public ResponseEntity<?> updateArticle(ArticleDto articleDto) {
        ArticleEntity articleEntity = articleRepository.findById(articleDto.getId()).orElseThrow(ArticleNotExistsError::new);
        articleEntity.setTitle(articleEntity.getTitle());
        articleEntity.setDescription(articleDto.getDescription());
        articleEntity.setBody(articleEntity.getBody());
        articleRepository.save(articleEntity);
        ArticleDto responseDto = modelMapper.map(articleEntity, ArticleDto.class);
        responseDto.setCourseId(articleEntity.getCourse().getId());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }


    @Transactional
    public ResponseEntity<?> deleteArticle(ArticleDto articleDto) {
        ArticleEntity articleEntity = articleRepository.findById(articleDto.getId()).orElseThrow(ArticleNotExistsError::new);
        articleRepository.delete(articleEntity);
        return new ResponseEntity<>(Map.of("status", "success"), HttpStatus.OK);
    }

    public ResponseEntity<?> getAllByCourseId(ArticleDto articleDto) {
        CourseEntity courseEntity = courseRepository.findById(articleDto.getCourseId()).orElseThrow(CourseNotExistsError::new);
        List<ArticleEntity> articleEntities = courseEntity.getArticles();
        List<ArticleDto> articleDtos = new ArrayList<>();
        for (ArticleEntity articleEntity : articleEntities) {
            articleDtos.add(modelMapper.map(articleEntity, ArticleDto.class));
        }
        return new ResponseEntity<>(articleDtos, HttpStatus.OK);
    }
}

