package ru.netrunner.coursesmvp.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.netrunner.coursesmvp.dto.AdminDto;
import ru.netrunner.coursesmvp.services.AdminService;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping("/admin/")
@Tag(name = "Admin Requests")
public class AdminController {

    AdminService adminService;

    @Operation(summary = "Выдача пользователю доступ к курсу")
    @ApiResponse(responseCode = "200", description = "course successfully issued",
            content = {@Content})
    @PostMapping("/give")
    public ResponseEntity<?> giveCourse(@Validated @RequestBody AdminDto.Request.Give adminDto){
        return adminService.giveCourse(adminDto);
    }
}
