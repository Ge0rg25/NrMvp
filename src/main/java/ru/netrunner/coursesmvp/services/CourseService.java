package ru.netrunner.coursesmvp.services;


import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.netrunner.coursesmvp.dto.objects.CourseDto;
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
    ModelMapper modelMapper;

    @Transactional
    public ResponseEntity<?> createCourse(CourseDto courseDto){
        if(courseRepository.existsByTitle(courseDto.getTitle())){
            throw new CourseAlreadyExistsError();
        }
        CourseEntity courseEntity = modelMapper.map(courseDto, CourseEntity.class);
        courseRepository.save(courseEntity);
        return new ResponseEntity<>(modelMapper.map(courseEntity, CourseDto.class), HttpStatus.CREATED);
    }


    @Transactional
    public ResponseEntity<?> updateCourse(CourseDto courseDto){
        CourseEntity courseEntity = courseRepository.findById(courseDto.getId()).orElseThrow(CourseNotExistsError::new);
        courseEntity.setTitle(courseDto.getTitle());
        courseEntity.setDescription(courseDto.getDescription());
        courseRepository.save(courseEntity);
        return new ResponseEntity<>(modelMapper.map(courseEntity, CourseDto.class), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<?> deleteCourse(CourseDto courseDto){
        CourseEntity courseEntity = courseRepository.findById(courseDto.getId()).orElseThrow(CourseNotExistsError::new);
        courseRepository.delete(courseEntity);
        return ResponseEntity.ok(Map.of("status", "success"));
    }


    public ResponseEntity<?> getAllCourses(){
        List<CourseEntity> courseEntities = courseRepository.findAll();
        List<CourseDto> courseDtos = new ArrayList<>();
        for(CourseEntity courseEntity: courseEntities){
            courseDtos.add(modelMapper.map(courseEntity, CourseDto.class));
        }
        return new ResponseEntity<>(courseDtos, HttpStatus.OK);
    }
}
