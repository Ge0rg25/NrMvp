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
import ru.netrunner.coursesmvp.dto.objects.UserDto;
import ru.netrunner.coursesmvp.errors.common.UserNotExistsException;
import ru.netrunner.coursesmvp.keycloak.KeycloakUtils;
import ru.netrunner.coursesmvp.models.CourseEntity;
import ru.netrunner.coursesmvp.models.UserEntity;
import ru.netrunner.coursesmvp.repositories.CourseRepository;
import ru.netrunner.coursesmvp.repositories.UserRepository;
import org.keycloak.representations.idm.UserRepresentation;

import javax.ws.rs.NotFoundException;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AdminUserService {

    ModelMapper modelMapper;
    UserRepository userRepository;
    CourseRepository courseRepository;
    KeycloakUtils keycloakUtils;

    public ResponseEntity<?> getUser(String email) {

        UserRepresentation userRepresentation = keycloakUtils.findUserByEmail(email);

        String userId = userRepresentation.getId();
        Optional<UserEntity> u = userRepository.findById(userId);

        if(u.isEmpty()) {
            UserEntity userEntity = new UserEntity();
            userEntity.setId(userId);
            userRepository.save(userEntity);
            UserDto response = modelMapper.map(userEntity, UserDto.class);
            return ResponseEntity.ok(response);
        }
        UserDto response = modelMapper.map(u.get(), UserDto.class);
        return ResponseEntity.ok(response);
    }

    @Transactional
    public ResponseEntity<?> addCourseAccessToUser(UserDto userDto, CourseDto courseDto){
        CourseEntity courseEntity = courseRepository.findById(courseDto.getId()).orElseThrow(NotFoundException::new);
        courseEntity.getUsers().add(userRepository.findById(userDto.getId()).orElseThrow(UserNotExistsException::new));
        courseRepository.save(courseEntity);
        return new ResponseEntity<>(modelMapper.map(courseEntity, CourseDto.class), HttpStatus.OK);
    }
}
