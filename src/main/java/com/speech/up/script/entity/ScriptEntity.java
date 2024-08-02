package com.speech.up.script.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.speech.up.script.service.dto.ScriptAddDto;
import com.speech.up.script.service.dto.ScriptIsUseDto;
import com.speech.up.script.service.dto.ScriptUpdateDto;
import com.speech.up.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

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

	private boolean isUse;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	@JsonBackReference
	private UserEntity user;

	@OneToMany(mappedBy = "script", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<RecordEntity> recordEntity;

	// 대본 생성
    public ScriptEntity(ScriptAddDto.ScriptAddRequestDto scriptAddRequestDto) {
        this.content = scriptAddRequestDto.getContent();
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
		this.user = scriptAddRequestDto.getUser();
		this.isUse = true;
    }

	// 대본 업데이트
	public ScriptEntity(ScriptUpdateDto.ScriptUpdateRequestDto scriptUpdateRequestDto) {
		this.scriptId = scriptUpdateRequestDto.getScriptId();
		this.content = scriptUpdateRequestDto.getContent();
		this.createdAt = scriptUpdateRequestDto.getCreatedAt();
		this.modifiedAt = LocalDateTime.now();
		this.user = scriptUpdateRequestDto.getUser();
		this.isUse = true;
	}

	// 대본 삭제
	public ScriptEntity(ScriptIsUseDto.ScriptIsUseRequestDto scriptIsUseRequestDto) {
		this.scriptId = scriptIsUseRequestDto.getScriptId();
		this.user = scriptIsUseRequestDto.getUser();
		this.content = scriptIsUseRequestDto.getContent();
		this.createdAt = scriptIsUseRequestDto.getCreatedAt();
		this.modifiedAt = scriptIsUseRequestDto.getModifiedAt();
		this.isUse = scriptIsUseRequestDto.isUse();
	}
}
