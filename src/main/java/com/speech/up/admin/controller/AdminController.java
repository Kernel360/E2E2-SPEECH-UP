package com.speech.up.admin.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.speech.up.user.repository.UserRepository;
import com.speech.up.user.service.UserService;
import com.speech.up.user.service.dto.UserGetInfoDto;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

/**
 * AdminController는 사용자 관리와 관련된 관리 작업을 처리하는 컨트롤러입니다.
 *
 * <p>이 컨트롤러는 모든 사용자 정보를 가져오거나, 관리자의 정보를 확인하거나,
 * 사용자를 삭제하거나 복원하는 등의 엔드포인트를 제공합니다.</p>
 *
 * <p>이 컨트롤러의 모든 메서드는 'ADMIN_USER' 역할을 가진 사용자만 접근할 수 있습니다.</p>
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

	private final UserService userService;
	private final UserRepository userRepository;

	/**
	 * 모든 사용자 정보를 가져옵니다.
	 *
	 * @return {@link UserGetInfoDto.Response} 객체의 리스트로, 모든 사용자 정보를 포함합니다.
	 */
	@GetMapping("/user/all")
	@PreAuthorize("hasRole('ADMIN_USER')")
	public List<UserGetInfoDto.Response> getUser() {
		return userService.getAllUsers();
	}

	/**
	 * 현재 관리자의 사용자 정보를 확인합니다.
	 *
	 * @param request 현재 HTTP 요청 정보
	 * @return 현재 관리자의 사용자 정보를 담고 있는 {@link UserGetInfoDto.Response} 객체를 포함하는 ResponseEntity
	 */
	@GetMapping("/me")
	@PreAuthorize("hasRole('ADMIN_USER')")
	public ResponseEntity<UserGetInfoDto.Response> checkAdmin(HttpServletRequest request) {
		return ResponseEntity.ok(userService.getUserInfo(request));
	}

	/**
	 * 지정된 사용자 ID를 가진 사용자를 삭제합니다.
	 *
	 * @param userId 삭제할 사용자의 ID
	 */
	@DeleteMapping("/drop/{userId}")
	@PreAuthorize("hasRole('ADMIN_USER')")
	public void dropUser(@PathVariable Long userId) {
		// 사용자 삭제를 서비스 메서드를 통해 수행
		userRepository.deleteById(userId);
	}

	/**
	 * 지정된 사용자 ID를 가진 사용자를 비활성화합니다.
	 *
	 * @param userId 비활성화할 사용자의 ID
	 */
	@DeleteMapping("/{userId}")
	@PreAuthorize("hasAnyRole('ADMIN_USER')")
	public void unUsedUser(@PathVariable Long userId) {
		userService.unUsedUser(userId);
	}

	/**
	 * 지정된 사용자 ID를 가진 사용자를 복원합니다.
	 *
	 * @param userId 복원할 사용자의 ID
	 */
	@PostMapping("/{userId}")
	@PreAuthorize("hasAnyRole('ADMIN_USER')")
	public void restoreUser(@PathVariable Long userId) {
		userService.restoreUser(userId);
	}
}
