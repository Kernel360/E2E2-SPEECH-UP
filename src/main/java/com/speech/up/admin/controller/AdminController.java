package com.speech.up.admin.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.speech.up.user.repository.UserRepository;
import com.speech.up.user.service.UserService;
import com.speech.up.user.service.dto.UserGetInfoDto;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

	private final UserService userService;
	private final UserRepository userRepository;

	@GetMapping("/user/all")
	@PreAuthorize("hasRole('ADMIN_USER')")
	public List<UserGetInfoDto.Response> getUser(){
		return userService.getAllUsers();
	}

	@GetMapping("/me")
	@PreAuthorize("hasRole('ADMIN_USER')")
	public ResponseEntity<UserGetInfoDto.Response> checkAdmin(HttpServletRequest request){
		return ResponseEntity.ok(userService.getUserInfo(request));
	}

	@DeleteMapping("/drop/{userId}")
	@PreAuthorize("hasRole('ADMIN_USER')")
	public void dropUser(@PathVariable Long userId){
		// 서비스를 통해 삭제되도록 수정
		userRepository.deleteById(userId);
	}

	@DeleteMapping("/{userId}")
	@PreAuthorize("hasAnyRole('ADMIN_USER')")
	public void unUsedUser(@PathVariable Long userId){
		userService.unUsedUser(userId);
	}

	@PostMapping("/{userId}")
	@PreAuthorize("hasAnyRole('ADMIN_USER')")
	public void restoreUser(@PathVariable Long userId){
		userService.restoreUser(userId);
	}
}
