package com.speech.up.user.service;

import com.speech.up.common.exception.http.BadRequestException;
import com.speech.up.oAuth.provider.JwtProvider;
import com.speech.up.user.entity.UserEntity;
import com.speech.up.user.repository.UserRepository;
import com.speech.up.user.service.dto.UserGetInfoDto;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    public UserGetInfoDto.UserGetInfoResponseDto getUserInfo(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if(authorization == null) {
            throw new BadRequestException("Authorization header is missing");
        }
        if(authorization.startsWith("Bearer ")) {
            authorization = authorization.substring(7);
        }
        String socialId = jwtProvider.validate(authorization);
        UserEntity userEntity = userRepository.findBySocialId(socialId);

        return UserGetInfoDto.UserGetInfoResponseDto.getUserInfo(userEntity);
    }

    // 조회?
    // 탈퇴

    public void deleteUser(String socialId) {
        userRepository.deleteBySocialId(socialId);
    }
}
