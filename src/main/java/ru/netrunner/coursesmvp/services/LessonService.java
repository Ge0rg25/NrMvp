package ru.netrunner.coursesmvp.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.netrunner.coursesmvp.dto.LessonDto;
import ru.netrunner.coursesmvp.errors.common.LessonNotExistsError;
import ru.netrunner.coursesmvp.models.LessonEntity;
import ru.netrunner.coursesmvp.repositories.LessonRepository;

import java.util.UUID;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class LessonService {
    LessonRepository lessonRepository;

    public ResponseEntity<?> getLesson(String uuid) {
        LessonEntity lessonEntity = lessonRepository.findLessonEntityByAccessCode(uuid).orElseThrow(LessonNotExistsError::new);

        if (!lessonEntity.getEnabled()) throw new LessonNotExistsError();

        LessonDto.Response.BaseResponse response = new LessonDto.Response.BaseResponse(
                lessonEntity.getId(),
                lessonEntity.getTitle(),
                lessonEntity.getDescription(),
                lessonEntity.getBody(),
                true,
                lessonEntity.getAccessCode()
        );
        return ResponseEntity.ok(response);
    }
}
