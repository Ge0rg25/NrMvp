package ru.netrunner.coursesmvp.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.netrunner.coursesmvp.dto.objects.ArticleDto;
import ru.netrunner.coursesmvp.dto.objects.CourseDto;
import ru.netrunner.coursesmvp.dto.objects.GetCourseRequestDto;
import ru.netrunner.coursesmvp.errors.common.UserNotExistsException;
import ru.netrunner.coursesmvp.models.ArticleEntity;
import ru.netrunner.coursesmvp.models.CourseEntity;
import ru.netrunner.coursesmvp.models.UserEntity;
import ru.netrunner.coursesmvp.repositories.ArticleRepository;
import ru.netrunner.coursesmvp.repositories.UserRepository;

import javax.ws.rs.ForbiddenException;
import java.util.ArrayList;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserService {

    UserRepository userRepository;
    ArticleRepository articleRepository;
    ModelMapper modelMapper;

    public ResponseEntity<?> getUserCourses(GetCourseRequestDto getCourseRequestDto) {
        UserEntity user = userRepository.findById(getCourseRequestDto.getId()).orElseThrow(UserNotExistsException::new);
        List<CourseEntity> courseList = user.getCourses();
        List<CourseDto> response = new ArrayList<>();
        for (CourseEntity courseEntity: courseList)
            response.add(modelMapper.map(courseEntity, CourseDto.class));
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> getCourse(GetCourseRequestDto getCourseRequestDto){
        UserEntity user = userRepository.findById(getCourseRequestDto.getId()).orElseThrow(UserNotExistsException::new);
        List<CourseEntity> courseList = user.getCourses();
        CourseDto response = modelMapper.map(courseList.stream().filter(
                courseEntity -> courseEntity.getId().equals(getCourseRequestDto.getCourseId())
        ).findFirst()
                .orElseThrow(ForbiddenException::new), CourseDto.class);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> getArticle(GetCourseRequestDto getCourseRequestDto){
        UserEntity user = userRepository.findById(getCourseRequestDto.getId()).orElseThrow(UserNotExistsException::new);
        ArticleEntity article = articleRepository.findById(getCourseRequestDto.getArticleId()).orElseThrow(ForbiddenException::new);
        modelMapper.map(user.getCourses().stream().filter(
                        courseEntity -> courseEntity.getId().equals(getCourseRequestDto.getCourseId())
                ).findFirst()
                .orElseThrow(ForbiddenException::new), CourseDto.class);
        ArticleDto response = modelMapper.map(article, ArticleDto.class);
        return ResponseEntity.ok(response);
    }
}
