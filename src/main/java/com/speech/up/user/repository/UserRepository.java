package com.speech.up.user.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.speech.up.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.transaction.Transactional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	UserEntity findBySocialId(String socialId);

	boolean existsBySocialId(String socialId);

	@Transactional
	void deleteBySocialId(String socialId);

	UserEntity findByUserId(Long userId);

	List<UserEntity> findAllByLastAccessedAtBeforeAndIsUse(LocalDateTime oneWeekAgo, boolean isUse);
}
