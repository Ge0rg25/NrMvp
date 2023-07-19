package ru.netrunner.coursesmvp.controllers;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.netrunner.coursesmvp.dto.objects.AdminDto;
import ru.netrunner.coursesmvp.services.AdminService;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping("/admin/")
public class AdminController {

    AdminService adminService;

    @PostMapping("/give")
    public ResponseEntity<?> giveCourse(@RequestBody AdminDto adminDto){
        return adminService.giveCourse(adminDto);
    }
}
