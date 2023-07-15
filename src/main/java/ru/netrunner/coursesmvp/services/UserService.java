package ru.netrunner.coursesmvp.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.netrunner.coursesmvp.dto.objects.LessonDto;
import ru.netrunner.coursesmvp.dto.objects.UserDto;
import ru.netrunner.coursesmvp.models.UserEntity;
import ru.netrunner.coursesmvp.repositories.UserRepository;

import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserService {

    ModelMapper modelMapper;
    UserRepository userRepository;

    public ResponseEntity<?> getUser(String userId) {
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


    public void syncUser(String userId){
        if(userRepository.existsById(userId)){
            return;
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        userRepository.save(userEntity);
    }
}
