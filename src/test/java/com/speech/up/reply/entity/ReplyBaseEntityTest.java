package com.speech.up.reply.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReplyBaseEntityTest {

	ReplyBaseEntity replyBaseEntity;

	@BeforeEach
	void setUp() {
		replyBaseEntity = new ReplyBaseEntity() {
		}; // 익명 클래스를 통해 추상 클래스를 인스턴스화
	}

	@DisplayName("ReplyBaseEntity 생성 시 createdAt과 modifiedAt 필드가 올바르게 설정되는지 테스트")
	@Test
	void testCreatedAtAndModifiedAt() throws IllegalAccessException, NoSuchFieldException {
		// given
		LocalDateTime now = LocalDateTime.now();

		// 리플렉션을 통해 createdAt 필드 설정
		Field createdAtField = ReplyBaseEntity.class.getDeclaredField("createdAt");
		createdAtField.setAccessible(true);
		createdAtField.set(replyBaseEntity, now);

		// 리플렉션을 통해 modifiedAt 필드 설정
		Field modifiedAtField = ReplyBaseEntity.class.getDeclaredField("modifiedAt");
		modifiedAtField.setAccessible(true);
		modifiedAtField.set(replyBaseEntity, now);

		// when & then
		assertEquals(now, createdAtField.get(replyBaseEntity));
		assertEquals(now, modifiedAtField.get(replyBaseEntity));
	}
}

