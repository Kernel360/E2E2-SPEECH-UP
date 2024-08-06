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

import java.util.List;

import org.hibernate.annotations.DynamicUpdate;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@DynamicUpdate
@Table(name = "script")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ScriptEntity extends BaseScriptEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long scriptId;

	private String content;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	@JsonBackReference
	private UserEntity user;

	private boolean isUse;

	@OneToMany(mappedBy = "script", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<RecordEntity> recordEntity; // => 어따 쓰는건지 확인해봐야함

	public ScriptEntity(String content, UserEntity user, boolean isUse) {
		this.content = content;
		this.user = user;
		this.isUse = isUse;
	}

	// 대본 생성
	private ScriptEntity(ScriptAddDto.Request scriptAddRequestDto) {
		this(scriptAddRequestDto.getContent(), scriptAddRequestDto.getUser(), true);
	}

	// 대본 업데이트
	private ScriptEntity(ScriptUpdateDto.Request scriptUpdateRequestDto) {
		this(scriptUpdateRequestDto.getContent(), scriptUpdateRequestDto.getUser(), true);
		this.scriptId = scriptUpdateRequestDto.getScriptId();
	}

	// 대본 삭제
	private ScriptEntity(ScriptIsUseDto.Request scriptIsUseRequestDto) {
		this(scriptIsUseRequestDto.getContent(), scriptIsUseRequestDto.getUser(), false);
		this.scriptId = scriptIsUseRequestDto.getScriptId();
	}

	public static ScriptEntity create(ScriptAddDto.Request scriptAddRequestDto) {
		return new ScriptEntity(scriptAddRequestDto);
	}

	public static ScriptEntity update(ScriptUpdateDto.Request scriptUpdateRequestDto) {

		return new ScriptEntity(scriptUpdateRequestDto);
	}

	public static ScriptEntity delete(ScriptIsUseDto.Request scriptIsUseRequestDto) {
		return new ScriptEntity(scriptIsUseRequestDto);
	}
}
