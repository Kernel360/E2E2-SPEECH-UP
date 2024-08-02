package com.speech.up.user.service;

import com.speech.up.user.entity.UserEntity;
import com.speech.up.user.repository.UserRepository;
import com.speech.up.user.service.dto.UserGetInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserGetInfoDto.UserGetInfoResponseDto getUserInfo(Long userId) {
        UserEntity userEntity = userRepository.findById(userId).get();
        return UserGetInfoDto.UserGetInfoResponseDto.getUserInfo(userEntity);
    }
}
