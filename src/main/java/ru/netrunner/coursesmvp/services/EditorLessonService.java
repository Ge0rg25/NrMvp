package ru.netrunner.coursesmvp.services;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.netrunner.coursesmvp.dto.LessonDto;
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

    @Transactional
    public ResponseEntity<?> createLesson(LessonDto.Request.Create lessonDto){
        if(lessonRepository.existsByTitle(lessonDto.title()))
            throw new LessonAlreadyExistsError();

        LessonEntity lessonEntity = LessonEntity.builder()
                .title(lessonDto.title())
                .description(lessonDto.description())
                .body(lessonDto.body())
                .enabled(lessonDto.enabled())
                .build();
        lessonRepository.save(lessonEntity);

        LessonDto.Response.BaseResponse response = LessonDto.Response.BaseResponse.builder()
                .id(lessonEntity.getId())
                .title(lessonEntity.getTitle())
                .description(lessonEntity.getDescription())
                .body(lessonDto.body())
                .enabled(lessonDto.enabled())
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @Transactional
    public ResponseEntity<?> updateLesson(LessonDto.Request.Update lessonDto){
        LessonEntity lessonEntity = lessonRepository.findById(lessonDto.id()).orElseThrow(LessonNotExistsError::new);
        lessonEntity.setTitle(lessonDto.title());
        lessonEntity.setDescription(lessonDto.description());
        lessonEntity.setBody(lessonDto.body());
        lessonEntity.setEnabled(lessonDto.enabled());
        lessonRepository.save(lessonEntity);
        LessonDto.Response.BaseResponse response = LessonDto.Response.BaseResponse.builder()
                .id(lessonEntity.getId())
                .title(lessonEntity.getTitle())
                .description(lessonEntity.getDescription())
                .body(lessonEntity.getBody())
                .enabled(lessonEntity.getEnabled())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<?> deleteLesson(LessonDto.Request.Delete lessonDto){
        LessonEntity lessonEntity = lessonRepository.findById(lessonDto.id()).orElseThrow(LessonNotExistsError::new);
        lessonRepository.delete(lessonEntity);
        return ResponseEntity.ok(Map.of("status", "success"));
    }


    public ResponseEntity<?> getAllLessons(){
        List<LessonEntity> lessonEntities = lessonRepository.findAll();
        List<LessonDto.Response.BaseResponse> responses = new ArrayList<>();
        for(LessonEntity lessonEntity: lessonEntities){
            responses.add(
                    LessonDto.Response.BaseResponse.builder()
                            .id(lessonEntity.getId())
                            .title(lessonEntity.getTitle())
                            .description(lessonEntity.getDescription())
                            .body(lessonEntity.getBody())
                            .enabled(lessonEntity.getEnabled())
                            .build()
            );
        }
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

}
