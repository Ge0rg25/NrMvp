package ru.netrunner.coursesmvp.services;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.netrunner.coursesmvp.dto.objects.ArticleDto;
import ru.netrunner.coursesmvp.dto.objects.CourseDto;
import ru.netrunner.coursesmvp.dto.objects.GetCourseRequestDto;
import ru.netrunner.coursesmvp.errors.common.UserNotExistsException;
import ru.netrunner.coursesmvp.models.CourseEntity;
import ru.netrunner.coursesmvp.models.UserEntity;
import ru.netrunner.coursesmvp.repositories.UserRepository;

import javax.ws.rs.ForbiddenException;
import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserService {

    UserRepository userRepository;
    ModelMapper modelMapper;

    public ResponseEntity<?> getUserCourses(String userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(UserNotExistsException::new);
        List<CourseEntity> courseList = user.getCourses();
        List<CourseDto> response = new ArrayList<>();
        for (CourseEntity courseEntity : courseList)
            response.add(modelMapper.map(courseEntity, CourseDto.class));
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> getCourse(String userId ,CourseDto courseDto) {
        UserEntity user = userRepository.findById(userId).orElseThrow(UserNotExistsException::new);
        List<CourseEntity> courseList = user.getCourses();
        CourseDto response = modelMapper.map(courseList.stream().filter(
                        courseEntity -> courseEntity.getId().equals(courseDto.getId())
                ).findFirst()
                .orElseThrow(ForbiddenException::new), CourseDto.class);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> getArticles(String userId ,CourseDto courseDto) {
        UserEntity user = userRepository.findById(userId).orElseThrow(UserNotExistsException::new);
        List<CourseEntity> courseList = user.getCourses();
        CourseEntity course = courseList.stream().filter(
                        courseEntity -> courseEntity.getId().equals(courseDto.getId())
                ).findFirst()
                .orElseThrow(ForbiddenException::new);
        ArticleDto response = modelMapper.map(course.getArticles(), ArticleDto.class);
        return ResponseEntity.ok(response);
    }



    @Transactional
    public ResponseEntity<?> userSetup(String id){
        if(!userRepository.existsById(id)){
            UserEntity userEntity = new UserEntity();
            userEntity.setId(id);
            userRepository.save(userEntity);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
