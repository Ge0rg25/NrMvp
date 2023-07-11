package ru.netrunner.coursesmvp.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.netrunner.coursesmvp.dto.objects.LessonDto;
import ru.netrunner.coursesmvp.models.LessonEntity;
import ru.netrunner.coursesmvp.repositories.LessonRepository;

import javax.ws.rs.ForbiddenException;
import java.util.UUID;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserLessonService {
    LessonRepository lessonRepository;
    ModelMapper modelMapper;
    public ResponseEntity<?> getLesson(String uuid){
        LessonEntity lessonEntity = lessonRepository.findLessonEntityByAccessCode(UUID.fromString(uuid)).orElseThrow(ForbiddenException::new);

        if(!lessonEntity.getEnabled()) throw new ForbiddenException();

        LessonDto response = modelMapper.map(lessonEntity, LessonDto.class);
        return ResponseEntity.ok(response);
    }
}
