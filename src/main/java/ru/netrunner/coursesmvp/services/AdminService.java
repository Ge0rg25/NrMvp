package ru.netrunner.coursesmvp.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.netrunner.coursesmvp.dto.AdminDto;
import ru.netrunner.coursesmvp.keycloak.KeycloakUtils;
import ru.netrunner.coursesmvp.models.CourseEntity;
import ru.netrunner.coursesmvp.models.UserEntity;
import ru.netrunner.coursesmvp.repositories.CourseRepository;
import ru.netrunner.coursesmvp.repositories.UserRepository;

import java.util.List;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Slf4j
public class AdminService {

    CourseRepository courseRepository;
    UserRepository userRepository;
    KeycloakUtils keycloakUtils;

    public ResponseEntity<?> giveCourse(AdminDto.Request.Give adminDto){
        CourseEntity courseEntity = courseRepository.findById(adminDto.courseId()).orElseThrow(RuntimeException::new);
        UserEntity userEntity = userRepository.findById(keycloakUtils.getUserIdByEmail(adminDto.userEmail())).orElseThrow(RuntimeException::new);
        List<UserEntity> courseUsers = courseEntity.getUsers();
        courseUsers.add(userEntity);
        courseEntity.setUsers(courseUsers);
        courseRepository.save(courseEntity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
