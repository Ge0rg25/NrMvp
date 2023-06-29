package ru.netrunner.coursesmvp.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.netrunner.coursesmvp.dto.objects.CourseDto;
import ru.netrunner.coursesmvp.dto.objects.UserDto;
import ru.netrunner.coursesmvp.errors.common.UserNotFoundException;
import ru.netrunner.coursesmvp.models.CourseEntity;
import ru.netrunner.coursesmvp.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserService {

    UserRepository userRepository;
    ModelMapper modelMapper;

    public ResponseEntity<?> getAllUsersCourses(UserDto userDto){
        List<CourseDto> courseDtos = new ArrayList<>();

        try {
            for (CourseEntity courseEntity: userRepository.findById(userDto.getId()).get().getCourses())
                courseDtos.add(modelMapper.map(courseEntity, CourseDto.class));
            return new ResponseEntity<>(courseDtos, HttpStatus.OK);
        } catch (NoSuchElementException exception){
            throw new UserNotFoundException();
        }
    }
}
