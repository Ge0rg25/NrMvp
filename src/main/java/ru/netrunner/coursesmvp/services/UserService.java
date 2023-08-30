package ru.netrunner.coursesmvp.services;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.netrunner.coursesmvp.dto.ArticleDto;
import ru.netrunner.coursesmvp.dto.ModuleDto;
import ru.netrunner.coursesmvp.dto.CourseDto;
import ru.netrunner.coursesmvp.errors.common.ArticleNotExistsError;
import ru.netrunner.coursesmvp.errors.common.CourseNotExistsError;
import ru.netrunner.coursesmvp.errors.common.ModuleNotExistsError;
import ru.netrunner.coursesmvp.errors.common.UserNotExistsException;
import ru.netrunner.coursesmvp.models.ArticleEntity;
import ru.netrunner.coursesmvp.models.CourseEntity;
import ru.netrunner.coursesmvp.models.ModuleEntity;
import ru.netrunner.coursesmvp.models.UserEntity;
import ru.netrunner.coursesmvp.repositories.ArticleRepository;
import ru.netrunner.coursesmvp.repositories.CourseRepository;
import ru.netrunner.coursesmvp.repositories.ModuleRepository;
import ru.netrunner.coursesmvp.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserService {

    UserRepository userRepository;
    CourseRepository courseRepository;
    ModuleRepository moduleRepository;
    ArticleRepository articleRepository;

    public ResponseEntity<?> getUserCourses(String userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(UserNotExistsException::new);
        List<CourseEntity> courseList = user.getCourses();
        List<ModuleDto.Response.BaseResponse> response = new ArrayList<>();
        for (CourseEntity courseEntity : courseList)
            response.add(
                    new ModuleDto.Response.BaseResponse(
                            courseEntity.getId(),
                            courseEntity.getTitle(),
                            courseEntity.getDescription()
                    )
            );
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> getCourse(String userId, ModuleDto.Request.Get courseDto) {
        UserEntity user = userRepository.findById(userId).orElseThrow(UserNotExistsException::new);
        List<CourseEntity> courseList = user.getCourses();
        CourseEntity findedCourse = courseList.stream().filter(
                        courseEntity -> courseEntity.getId().equals(courseDto.id())
                ).findFirst()
                .orElseThrow(CourseNotExistsError::new);
        ModuleDto.Response.BaseResponse response = new ModuleDto.Response.BaseResponse(
                findedCourse.getId(),
                findedCourse.getTitle(),
                findedCourse.getDescription()
        );
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> getModules(CourseDto.Request.Get courseDto){
        CourseEntity course = courseRepository.findById(courseDto.id()).orElseThrow(CourseNotExistsError::new);
        List<CourseDto.Response.BaseResponse> response = new ArrayList<>();
        for (ModuleEntity moduleEntity : course.getModules()) {
            response.add(
                    new CourseDto.Response.BaseResponse(
                            moduleEntity.getId(),
                            moduleEntity.getTitle(),
                            moduleEntity.getDescription()
                    )
            );
        }
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> getArticles(CourseDto.Request.Get courseDto) {
        ModuleEntity moduleEntity = moduleRepository.findById(courseDto.id()).orElseThrow(ModuleNotExistsError::new);
        List<ArticleDto.Response.BaseResponse> response = new ArrayList<>();
        for (ArticleEntity articleEntity : moduleEntity.getArticles()) {
            response.add(
                    new ArticleDto.Response.BaseResponse(
                            articleEntity.getTitle(),
                            articleEntity.getDescription(),
                            articleEntity.getBody(),
                            articleEntity.getId()

                    )
            );
        }
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> getArticleById(ArticleDto.Request.GetByModule dto){
        ArticleEntity articleEntity = articleRepository.findById(dto.id()).orElseThrow(ArticleNotExistsError::new);
        ArticleDto.Response.BaseResponse response = new ArticleDto.Response.BaseResponse(
                articleEntity.getTitle(),
                articleEntity.getDescription(),
                articleEntity.getBody(),
                articleEntity.getId()
        );
        return ResponseEntity.ok(response);
    }

    @Transactional
    public ResponseEntity<?> userSetup(String id) {
        if (!userRepository.existsById(id)) {
            UserEntity userEntity = new UserEntity();
            userEntity.setId(id);
            userRepository.save(userEntity);
        }
        return ResponseEntity.ok("Ассинхронщина для лохов");
    }
}
