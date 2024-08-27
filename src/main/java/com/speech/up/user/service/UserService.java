package com.speech.up.user.service;

import java.util.List;
import java.util.NoSuchElementException;

import com.speech.up.common.exception.http.BadRequestException;
import com.speech.up.auth.provider.JwtProvider;
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

    public List<UserGetInfoDto.Response> getAllUsers() {
        return UserGetInfoDto.Response.getUsers(userRepository.findAll());
    }

    public UserGetInfoDto.Response getUserInfo(HttpServletRequest request) {
        String socialId = jwtProvider.getHeader(request);
        UserEntity userEntity = userRepository.findBySocialId(socialId)
            .orElseThrow(() -> new NoSuchElementException("not found UserEntity by socialId: " + socialId));

        return UserGetInfoDto.Response.getUserInfo(userEntity);
    }

    public void deleteUser(HttpServletRequest request) {
        String socialId = jwtProvider.getHeader(request);

        userRepository.deleteBySocialId(socialId);
    }

    public void unUsedUser(Long userId) {
        userRepository.customDeleteUser(userId,false);
    }

    public void restoreUser(Long userId) {
        userRepository.customDeleteUser(userId,true);
    }
}
