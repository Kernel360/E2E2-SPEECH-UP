package com.speech.up.admin.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.speech.up.user.service.UserService;
import com.speech.up.user.service.dto.UserGetInfoDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

	private final UserService userService;

	@GetMapping("/user/all")
	@PreAuthorize("hasRole('ADMIN_USER')")
	public List<UserGetInfoDto.UserGetInfoResponseDto> getUser(){
		return userService.getAllUsers();
	}
}
