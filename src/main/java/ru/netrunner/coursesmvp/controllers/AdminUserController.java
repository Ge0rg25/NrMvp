package ru.netrunner.coursesmvp.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.netrunner.coursesmvp.dto.objects.CourseDto;
import ru.netrunner.coursesmvp.dto.objects.UserDto;
import ru.netrunner.coursesmvp.dto.rules.CourseValidationRules;
import ru.netrunner.coursesmvp.dto.rules.UserValidationRules;
import ru.netrunner.coursesmvp.services.AdminUserService;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/admin/users")
public class AdminUserController {

    AdminUserService adminService;
    @PostMapping(value = "/get", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUser(@RequestBody String email){
        return adminService.getUser(email);
    }
    @PostMapping(value = "/courses/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addCourseAccessToUser(@Validated(UserValidationRules.Get.class) @RequestBody UserDto userDto, @Validated(CourseValidationRules.Get.class) @RequestBody CourseDto courseDto){
        return adminService.addCourseAccessToUser(userDto, courseDto);
    }
}