package com.speech.up.user.repository;

import com.speech.up.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	UserEntity findBySocialId(String socialId);
	boolean existsBySocialId(String socialId);
	void deleteBySocialId(String socialId);

	UserEntity findByUserId(Long userId);
}
