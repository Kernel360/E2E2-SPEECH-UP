package com.speech.up.reply.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.speech.up.reply.entity.ReplyEntity;

public interface ReplyRepository extends JpaRepository<ReplyEntity, Long> {
	ReplyEntity findByReplyIdAndIsUseTrue(Long replyId);

	boolean existsByReplyIdAndIsUseTrue(Long replyId);
}
