package ru.netrunner.coursesmvp.services;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import ru.netrunner.coursesmvp.dto.ModuleDto;
import ru.netrunner.coursesmvp.errors.common.CourseNotExistsError;
import ru.netrunner.coursesmvp.errors.common.ModuleAlreadyExistsError;
import ru.netrunner.coursesmvp.errors.common.ModuleNotExistsError;
import ru.netrunner.coursesmvp.models.CourseEntity;
import ru.netrunner.coursesmvp.models.ModuleEntity;
import ru.netrunner.coursesmvp.repositories.CourseRepository;
import ru.netrunner.coursesmvp.repositories.ModuleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class ModuleService {

    ModuleRepository moduleRepository;
    CourseRepository courseRepository;

    @Transactional
    public ResponseEntity<?> createModule(ModuleDto.Request.Create moduleDto) {
        CourseEntity moduleCourse = courseRepository.findById(moduleDto.courseId()).orElseThrow(CourseNotExistsError::new);
        if (moduleRepository.existsByTitle(moduleDto.title()) && moduleRepository.existsByCourse(moduleCourse)) {
            throw new ModuleAlreadyExistsError();
        }
        ModuleEntity moduleEntity = ModuleEntity.builder()
                .title(moduleDto.title())
                .description(moduleDto.description())
                .course(moduleCourse)
                .build();
        moduleRepository.save(moduleEntity);
        ModuleDto.Response.BaseResponse response = new ModuleDto.Response.BaseResponse(
                moduleEntity.getId(),
                moduleEntity.getTitle(),
                moduleEntity.getDescription()
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @Transactional
    public ResponseEntity<?> updateModule(ModuleDto.Request.Update moduleDto) {
        ModuleEntity moduleEntity = moduleRepository.findById(moduleDto.id()).orElseThrow(ModuleNotExistsError::new);
        moduleEntity.setTitle(moduleDto.title());
        moduleEntity.setDescription(moduleDto.description());
        moduleRepository.save(moduleEntity);
        ModuleDto.Response.BaseResponse response = new ModuleDto.Response.BaseResponse(
                moduleEntity.getId(),
                moduleEntity.getTitle(),
                moduleEntity.getDescription()
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<?> deleteModule(ModuleDto.Request.Delete moduleDto) {
        ModuleEntity moduleEntity = moduleRepository.findById(moduleDto.id()).orElseThrow(ModuleNotExistsError::new);
        moduleRepository.delete(moduleEntity);
        return ResponseEntity.ok(Map.of("status", "success"));
    }


    public ResponseEntity<?> getAllModules(ModuleDto.Request.GetAll moduleDto) {

        CourseEntity courseEntity = courseRepository.findById(moduleDto.courseId()).orElseThrow(CourseNotExistsError::new);
        List<ModuleEntity> moduleEntities = courseEntity.getModules();
        List<ModuleDto.Response.BaseResponse> courseDtos = new ArrayList<>();
        for (ModuleEntity moduleEntity : moduleEntities) {
            courseDtos.add(
                    new ModuleDto.Response.BaseResponse(
                            moduleEntity.getId(),
                            moduleEntity.getTitle(),
                            moduleEntity.getDescription()
                    )
            );
        }
        return new ResponseEntity<>(courseDtos, HttpStatus.OK);
    }
}
