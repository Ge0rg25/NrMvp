package ru.netrunner.coursesmvp.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.netrunner.coursesmvp.services.UserService;

@RestController
@RequestMapping("/user/endpoint")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserEndpoint {

    UserService userService;

    @GetMapping
    public ResponseEntity<?> endpoint(@AuthenticationPrincipal Jwt jwt){
        userService.syncUser(jwt.getSubject());
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
