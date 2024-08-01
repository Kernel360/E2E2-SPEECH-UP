package com.speech.up.script.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.speech.up.script.service.dto.ScriptAddDto;
import com.speech.up.script.service.dto.ScriptUpdateDto;
import com.speech.up.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "script")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ScriptEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long scriptId;

	private String content;

	private LocalDateTime createdAt;

	private LocalDateTime modifiedAt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	@JsonBackReference
	private UserEntity user;

    public ScriptEntity(ScriptAddDto.ScriptAddRequestDto scriptAddRequestDto) {
        this.content = scriptAddRequestDto.getContent();
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
		this.user = scriptAddRequestDto.getUser();
    }

	public ScriptEntity(ScriptUpdateDto.ScriptUpdateRequestDto scriptUpdateRequestDto) {
		this.scriptId = scriptUpdateRequestDto.getScriptId();
		this.content = scriptUpdateRequestDto.getContent();
		this.createdAt = scriptUpdateRequestDto.getCreatedAt();
		this.modifiedAt = LocalDateTime.now();
		this.user = scriptUpdateRequestDto.getUser();
	}
}
