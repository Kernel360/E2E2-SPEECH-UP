package com.speech.up.user.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.speech.up.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	UserEntity findBySocialId(String socialId);

	boolean existsBySocialId(String socialId);

	@Transactional
	void deleteBySocialId(String socialId);

	UserEntity findByUserId(Long userId);

	List<UserEntity> findAllByLastAccessedAtBeforeAndIsUseTrue(LocalDateTime oneWeekAgo);

	@Transactional
	@Modifying
	@Query("update UserEntity u set u.isUse = :use where u.userId = :userId and u.isUse = true ")
	void customDeleteUser(@Param("userId") Long userId, @Param("use") boolean use);
}
