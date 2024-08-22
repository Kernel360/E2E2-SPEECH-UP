package com.speech.up.user.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import com.speech.up.user.entity.UserEntity;
import com.speech.up.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserScheduling {

	private final UserRepository userRepository;
	private final UserService userService;

	@Transactional
	@Scheduled(cron = "0 0 0 * * ?") // 매일 자정에 실행
	public void deleteLongAccessUser() {
		// 현재 시간 기준 일주일 전 시간 계산
		LocalDateTime oneWeekAgo = LocalDateTime.now().minusWeeks(1);

		// 일주일 이상 접속하지 않은 활성 사용자 조회
		List<UserEntity> inactiveUsers = userRepository.findAllByLastAccessedAtBeforeAndIsUse(oneWeekAgo, true);

		// 해당 사용자의 is_use 값을 0으로 설정
		for (UserEntity users : inactiveUsers) {
			userRepository.deleteBySocialId(users.getSocialId());
		}
	}
}
