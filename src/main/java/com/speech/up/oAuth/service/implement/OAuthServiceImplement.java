package com.speech.up.oAuth.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.speech.up.oAuth.entity.CertificationEntity;
import com.speech.up.oAuth.provider.JwtProvider;
import com.speech.up.oAuth.repository.CertificationRepository;
import com.speech.up.oAuth.service.OAuthService;
import com.speech.up.oAuth.service.dto.IdCheckRequestDto;
import com.speech.up.oAuth.service.dto.IdCheckResponseDto;
import com.speech.up.oAuth.service.dto.ResponseDto;
import com.speech.up.oAuth.service.dto.SignInRequestDto;
import com.speech.up.oAuth.service.dto.SignInResponseDto;
import com.speech.up.oAuth.service.dto.SignUpResponseDto;
import com.speech.up.oAuth.service.dto.SignUpRequestDto;
import com.speech.up.user.entity.UserEntity;
import com.speech.up.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuthServiceImplement implements OAuthService {

	// OAuth 아님
	private final UserRepository userRepository;
	private final JwtProvider jwtProvider;
	private final CertificationRepository certificationRepository;
	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	public ResponseEntity<? super IdCheckResponseDto> idCheck(IdCheckRequestDto dto) {
		try {
			String socialId = dto.getId();
			boolean isExistId = userRepository.existsBySocialId(socialId);
			if(isExistId) {
				return IdCheckResponseDto.duplicateId();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseDto.databaseError();
		}
		return IdCheckResponseDto.success();
	}

	@Override
	public ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto) {
		try{
			String socialId = dto.getId();
			boolean isExistId = userRepository.existsBySocialId(socialId);
			if(isExistId){
				return SignUpResponseDto.duplicated();
			}
			String email = dto.getEmail();
			String certificationNumber = dto.getCertificationNumber();
			CertificationEntity certificationEntity = certificationRepository.findBySocialId(socialId);
			boolean isMatched = certificationEntity.getEmail().equals(email) && certificationEntity.getCertificationNumber().equals(certificationNumber);
			if(!isMatched) {
				return SignUpResponseDto.certificationFail();
			}
			String password = dto.getPassword();
			String encodePassword = passwordEncoder.encode(password);
			dto.setPassword(encodePassword);
			UserEntity userEntity = new UserEntity(dto);

			userRepository.save(userEntity);
			certificationRepository.deleteBySocialId(socialId);
		}catch (Exception exception){
			return ResponseDto.databaseError();
		}
		return SignUpResponseDto.success();
	}

	@Override
	public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {
		String token = null;
		try{
			String socialId = dto.getSocialId();
			UserEntity userEntity = userRepository.findBySocialId(socialId);
			if(userEntity == null) {
				return SignInResponseDto.signInFail();
			}

			token = jwtProvider.createToken(socialId);
		}catch (Exception exception){;
			log.info("OAuthServiceImplement 가 잘못됨 : ", exception.fillInStackTrace());
			return ResponseDto.databaseError();
		}
		return SignInResponseDto.success(token);
	}
}
