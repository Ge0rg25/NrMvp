package ru.netrunner.coursesmvp.services;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.netrunner.coursesmvp.dto.ArticleDto;
import ru.netrunner.coursesmvp.dto.CourseDto;
import ru.netrunner.coursesmvp.errors.common.CourseNotExistsError;
import ru.netrunner.coursesmvp.errors.common.UserNotExistsException;
import ru.netrunner.coursesmvp.models.ArticleEntity;
import ru.netrunner.coursesmvp.models.CourseEntity;
import ru.netrunner.coursesmvp.models.UserEntity;
import ru.netrunner.coursesmvp.repositories.CourseRepository;
import ru.netrunner.coursesmvp.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserService {

    UserRepository userRepository;
    CourseRepository courseRepository;

    public ResponseEntity<?> getUserCourses(String userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(UserNotExistsException::new);
        List<CourseEntity> courseList = user.getCourses();
        List<CourseDto.Response.BaseResponse> response = new ArrayList<>();
        for (CourseEntity courseEntity : courseList)
            response.add(
                    CourseDto.Response.BaseResponse.builder()
                            .id(courseEntity.getId())
                            .title(courseEntity.getTitle())
                            .description(courseEntity.getDescription())
                            .build()
            );
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> getCourse(String userId, CourseDto.Request.Get courseDto) {
        UserEntity user = userRepository.findById(userId).orElseThrow(UserNotExistsException::new);
        List<CourseEntity> courseList = user.getCourses();
        CourseEntity findedCourse = courseList.stream().filter(
                        courseEntity -> courseEntity.getId().equals(courseDto.id())
                ).findFirst()
                .orElseThrow(CourseNotExistsError::new);
        CourseDto.Response.BaseResponse response = CourseDto.Response.BaseResponse.builder()
                .id(findedCourse.getId())
                .title(findedCourse.getTitle())
                .description(findedCourse.getDescription())
                .build();
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> getArticles(CourseDto.Request.Get courseDto) {
        CourseEntity course = courseRepository.findById(courseDto.id()).orElseThrow(CourseNotExistsError::new);
        List<ArticleDto.Response.SingleArticle> response = new ArrayList<>();
        for (ArticleEntity articleEntity : course.getArticles()) {
            response.add(
                    ArticleDto.Response.SingleArticle.builder()
                            .id(articleEntity.getId())
                            .description(articleEntity.getDescription())
                            .body(articleEntity.getBody())
                            .title(articleEntity.getTitle())
                            .build()
            );
        }
        return ResponseEntity.ok(response);
    }


    @Transactional
    public ResponseEntity<?> userSetup(String id) {
        if (!userRepository.existsById(id)) {
            UserEntity userEntity = new UserEntity();
            userEntity.setId(id);
            userRepository.save(userEntity);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
