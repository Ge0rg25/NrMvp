package ru.netrunner.coursesmvp.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.netrunner.coursesmvp.dto.objects.AdminDto;
import ru.netrunner.coursesmvp.keycloak.KeycloakUtils;
import ru.netrunner.coursesmvp.models.CourseEntity;
import ru.netrunner.coursesmvp.models.UserEntity;
import ru.netrunner.coursesmvp.repositories.CourseRepository;
import ru.netrunner.coursesmvp.repositories.UserRepository;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class AdminService {

    CourseRepository courseRepository;
    UserRepository userRepository;
    KeycloakUtils keycloakUtils;

    public ResponseEntity<?> giveCourse(AdminDto adminDto){
        CourseEntity courseEntity = courseRepository.findById(adminDto.getCourseId()).get();
        UserEntity userEntity = userRepository.findById(keycloakUtils.getUserIdByEmail(adminDto.getUserEmail())).get();
        userEntity.getCourses().add(courseEntity);
        userRepository.save(userEntity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
