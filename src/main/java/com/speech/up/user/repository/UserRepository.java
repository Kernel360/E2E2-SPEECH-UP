package com.speech.up.user.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.speech.up.user.entity.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	Optional<UserEntity> findBySocialId(String socialId);

	boolean existsBySocialId(String socialId);

	@Transactional
	void deleteBySocialId(String socialId);

	Optional<UserEntity> findByUserId(Long userId);

	List<UserEntity> findAllByLastAccessedAtBeforeAndIsUseTrue(LocalDateTime oneWeekAgo);

	//TODO :  이 부분을 제거하고 save 로 변경하는 방안을 생각하는 것이 좋을 것 같습니다.
	@Transactional
	@Modifying
	@Query("update UserEntity u set u.isUse = :use where u.userId = :userId ")
	void customDeleteUser(@Param("userId") Long userId, @Param("use") boolean use);
}
