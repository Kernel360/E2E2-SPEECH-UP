package com.speech.up.user.service;

import com.speech.up.user.entity.UserEntity;
import com.speech.up.user.repository.UserRepository;
import com.speech.up.user.service.dto.UserGetInfoDto;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    public UserGetInfoDto.UserGetInfoResponseDto getUserInfo() {
        String socialId = httpSession.getAttribute("socialId").toString();
        UserEntity userEntity = userRepository.findBySocialId(socialId);
        httpSession.setAttribute("userId", userEntity.getUserId().toString());
        return UserGetInfoDto.UserGetInfoResponseDto.getUserInfo(userEntity);
    }

    // 조회?
    // 탈퇴

    public void deleteUser(String socialId) {
        userRepository.deleteBySocialId(socialId);
    }
}
