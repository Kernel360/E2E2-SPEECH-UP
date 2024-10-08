package com.speech.up.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.speech.up.board.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
	Page<BoardEntity> findAllByIsUseTrueOrderByCreatedAtDesc(Pageable pageable);

	Long countByIsUseTrue();

	BoardEntity findByBoardIdAndIsUseTrue(Long id);

	Long countByUserUserIdAndIsUseTrue(Long userId);
}
