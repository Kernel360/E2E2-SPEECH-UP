package com.speech.up.user.controller;

import com.speech.up.user.service.UserService;
import com.speech.up.user.service.dto.UserGetInfoDto;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserController는 사용자와 관련된 API 엔드포인트를 제공합니다.
 * 이 컨트롤러는 현재 사용자 정보를 조회하고, 사용자를 삭제하는 기능을 처리합니다.
 * <p>
 * 모든 엔드포인트는 인증된 사용자(GENERAL_USER 또는 ADMIN_USER)만 접근할 수 있습니다.
 * </p>
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 현재 사용자의 정보를 조회합니다.
     *
     * @param request HTTP 요청 객체, 사용자 정보를 조회하는 데 필요한 인증 정보를 포함
     * @return 현재 사용자의 정보가 담긴 응답 객체
     */
    @GetMapping("/me")
    @PreAuthorize("hasAnyRole('GENERAL_USER', 'ADMIN_USER')")
    public ResponseEntity<UserGetInfoDto.Response> getUserInfo(HttpServletRequest request) {
        return ResponseEntity.ok(userService.getUserInfo(request));
    }

    /**
     * 현재 사용자를 삭제합니다. 실제로 데이터베이스에서 사용자 정보를 삭제합니다.
     *
     * @param request HTTP 요청 객체, 삭제할 사용자 정보를 포함
     */
    @DeleteMapping("/me")
    @PreAuthorize("hasAnyRole('GENERAL_USER', 'ADMIN_USER')")
    public void deleteUser(HttpServletRequest request) {
        userService.deleteUser(request);
    }

    /**
     * 벤 유저 여부를 검사합니다.
     *
     */
    @GetMapping("/banned/me")
    public String checkBanned() {
        return "ok";
    }

}
