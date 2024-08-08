package com.speech.up.oAuth.service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignInRequestDto {
	@NotBlank
	private String socialId;

	@NotBlank
	private String password;
}
