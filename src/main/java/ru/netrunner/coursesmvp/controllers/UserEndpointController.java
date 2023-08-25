package ru.netrunner.coursesmvp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.netrunner.coursesmvp.services.UserService;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping("/user")
@Tag(name = "User Endpoint", description = "Эндпоинт для синхронизации пользователей")
public class UserEndpointController {

    UserService userService;

    @Operation(summary = "Сюда надо отправить запрос после логина")
    @PostMapping("/sync")
    public ResponseEntity<?> setup(@AuthenticationPrincipal Jwt jwt) {
        return userService.userSetup(jwt.getSubject());
    }


}
