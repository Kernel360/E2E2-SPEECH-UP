package com.speech.up.oAuth.service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignUpRequestDto {
	@NotBlank
	private String id;

	@NotBlank
	@Pattern(regexp = "^(?=.[A-Za-z])(?=.\\d)(?=.[@$!%#?&])[A-Za-z\\d@$!%*#?&]{8,}$")
	private String password;

	@Email
	@NotBlank
	private String email;

	@NotBlank
	private String certificationNumber;
}
