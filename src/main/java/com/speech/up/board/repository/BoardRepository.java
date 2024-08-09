package com.speech.up.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.speech.up.board.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
	BoardEntity findByBoardIdAndIsUseTrue(Long boardId);
	List<BoardEntity> findAllByIsUseTrue();
}
