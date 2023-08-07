package ru.netrunner.coursesmvp.services;


import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.netrunner.coursesmvp.dto.CourseDto;
import ru.netrunner.coursesmvp.errors.common.CourseAlreadyExistsError;
import ru.netrunner.coursesmvp.errors.common.CourseNotExistsError;
import ru.netrunner.coursesmvp.models.CourseEntity;
import ru.netrunner.coursesmvp.repositories.CourseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CourseService {

    CourseRepository courseRepository;

    @Transactional
    public ResponseEntity<?> createCourse(CourseDto.Request.Create courseDto) {
        if (courseRepository.existsByTitle(courseDto.title())) {
            throw new CourseAlreadyExistsError();
        }
        CourseEntity courseEntity = new CourseEntity();
        courseRepository.save(courseEntity);
        CourseDto.Response.BaseResponse response = new CourseDto.Response.BaseResponse(
                courseEntity.getId(),
                courseEntity.getTitle(),
                courseEntity.getDescription()
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @Transactional
    public ResponseEntity<?> updateCourse(CourseDto.Request.Update courseDto) {
        CourseEntity courseEntity = courseRepository.findById(courseDto.id()).orElseThrow(CourseNotExistsError::new);
        courseEntity.setTitle(courseDto.title());
        courseEntity.setDescription(courseDto.description());
        courseRepository.save(courseEntity);
        CourseDto.Response.BaseResponse response = CourseDto.Response.BaseResponse.builder()
                .id(courseEntity.getId())
                .title(courseEntity.getTitle())
                .description(courseEntity.getDescription())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<?> deleteCourse(CourseDto.Request.Delete courseDto) {
        CourseEntity courseEntity = courseRepository.findById(courseDto.id()).orElseThrow(CourseNotExistsError::new);
        courseRepository.delete(courseEntity);
        return ResponseEntity.ok(Map.of("status", "success"));
    }


    public ResponseEntity<?> getAllCourses() {
        List<CourseEntity> courseEntities = courseRepository.findAll();
        List<CourseDto.Response.BaseResponse> courseDtos = new ArrayList<>();
        for (CourseEntity courseEntity : courseEntities) {
            courseDtos.add(
                    CourseDto.Response.BaseResponse.builder()
                            .id(courseEntity.getId())
                            .title(courseEntity.getTitle())
                            .description(courseEntity.getDescription())
                            .build()
            );
        }
        return new ResponseEntity<>(courseDtos, HttpStatus.OK);
    }
}
