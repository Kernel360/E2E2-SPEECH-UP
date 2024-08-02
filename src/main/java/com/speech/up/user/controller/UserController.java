package com.speech.up.user.controller;

import com.speech.up.user.service.UserService;
import com.speech.up.user.service.dto.UserGetInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserGetInfoDto.UserGetInfoResponseDto> getUserInfo(
            @PathVariable Long userId
    ) {
        return ResponseEntity.ok(userService.getUserInfo(userId));
    }
}
