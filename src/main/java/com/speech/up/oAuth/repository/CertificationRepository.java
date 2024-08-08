package com.speech.up.oAuth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.speech.up.oAuth.entity.CertificationEntity;

@Repository
public interface CertificationRepository extends JpaRepository<CertificationEntity, String> {
	CertificationEntity findBySocialId(String socialId);

	void deleteBySocialId(String socialId);
}
