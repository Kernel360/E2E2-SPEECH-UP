package com.speech.up.user.controller;

import com.speech.up.user.service.UserService;
import com.speech.up.user.service.dto.UserGetInfoDto;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    @PreAuthorize("hasAnyRole('GENERAL_USER', 'ADMIN_USER')")
    public ResponseEntity<UserGetInfoDto.UserGetInfoResponseDto> getUserInfo(HttpServletRequest request
    ) {
        return ResponseEntity.ok(userService.getUserInfo(request));
    }

    @DeleteMapping("/me")
    @PreAuthorize("hasAnyRole('GENERAL_USER', 'ADMIN_USER')")
    public void deleteUser(HttpServletRequest request
    ) {
      userService.deleteUser(request);
    }


    @DeleteMapping("/id")
    @PreAuthorize("hasAnyRole('ADMIN_USER')")
    public void unUsedUser(HttpServletRequest request){
        userService.unUsedUser(request);
    }
}
