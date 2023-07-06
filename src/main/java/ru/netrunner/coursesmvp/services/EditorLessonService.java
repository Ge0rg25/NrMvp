package ru.netrunner.coursesmvp.services;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.netrunner.coursesmvp.dto.objects.LessonDto;
import ru.netrunner.coursesmvp.errors.common.LessonAlreadyExistsError;
import ru.netrunner.coursesmvp.errors.common.LessonNotExistsError;
import ru.netrunner.coursesmvp.models.LessonEntity;
import ru.netrunner.coursesmvp.repositories.LessonRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class EditorLessonService {
    LessonRepository lessonRepository;
    ModelMapper modelMapper;

    @Transactional
    public ResponseEntity<?> createLesson(LessonDto lessonDto){
        if(lessonRepository.existsByTitle(lessonDto.getTitle()))
            throw new LessonAlreadyExistsError();

        LessonEntity lessonEntity = modelMapper.map(lessonDto, LessonEntity.class);
        lessonRepository.save(lessonEntity);
        return new ResponseEntity<>(modelMapper.map(lessonEntity, LessonDto.class), HttpStatus.CREATED);
    }


    @Transactional
    public ResponseEntity<?> updateLesson(LessonDto lessonDto){
        LessonEntity lessonEntity = lessonRepository.findById(lessonDto.getId()).orElseThrow(LessonNotExistsError::new);
        lessonEntity.setTitle(lessonDto.getTitle());
        lessonEntity.setDescription(lessonDto.getDescription());
        lessonEntity.setBody(lessonDto.getBody());
        lessonEntity.setEnabled(lessonDto.getEnabled());
        lessonRepository.save(lessonEntity);
        return new ResponseEntity<>(modelMapper.map(lessonEntity, LessonDto.class), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<?> deleteLesson(LessonDto lessonDto){
        LessonEntity lessonEntity = lessonRepository.findById(lessonDto.getId()).orElseThrow(LessonNotExistsError::new);
        lessonRepository.delete(lessonEntity);
        return ResponseEntity.ok(Map.of("status", "success"));
    }


    public ResponseEntity<?> getAllLessons(){
        List<LessonEntity> lessonEntities = lessonRepository.findAll();
        List<LessonDto> lessonDtos = new ArrayList<>();
        for(LessonEntity lessonEntity: lessonEntities){
            lessonDtos.add(modelMapper.map(lessonEntity, LessonDto.class));
        }
        return new ResponseEntity<>(lessonDtos, HttpStatus.OK);
    }

}
