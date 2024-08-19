package com.speech.up.script.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.speech.up.record.entity.RecordEntity;
import com.speech.up.script.service.dto.ScriptAddDto;
import com.speech.up.script.service.dto.ScriptIsUseDto;
import com.speech.up.script.service.dto.ScriptUpdateDto;
import com.speech.up.user.entity.UserEntity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import org.hibernate.annotations.DynamicUpdate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@DynamicUpdate
@Table(name = "script")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ScriptEntity extends BaseScriptEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "script_id")
	private Long scriptId;

	private String title;

	private String content;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	@JsonBackReference
	private UserEntity user;

	private boolean isUse;

	@OneToMany(mappedBy = "script", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<RecordEntity> recordId;

	public ScriptEntity(String content, UserEntity user, String title, boolean isUse) {
		this.content = content;
		this.user = user;
		this.title = title;
		this.isUse = isUse;
	}

	// 대본 생성
	private ScriptEntity(ScriptAddDto.Request scriptAddRequestDto) {
		this(scriptAddRequestDto.getContent(), scriptAddRequestDto.getUser(), scriptAddRequestDto.getTitle(), true);
	}

	// 대본 업데이트
	private ScriptEntity(ScriptUpdateDto.Request scriptUpdateRequestDto) {
		this(scriptUpdateRequestDto.getContent(), scriptUpdateRequestDto.getUser(), scriptUpdateRequestDto.getTitle(),true);
		this.scriptId = scriptUpdateRequestDto.getScriptId();
	}

	// 대본 삭제
	private ScriptEntity(ScriptIsUseDto.Request scriptIsUseRequestDto) {
		this(scriptIsUseRequestDto.getContent(), scriptIsUseRequestDto.getUser(), scriptIsUseRequestDto.getTitle(), false);
		this.scriptId = scriptIsUseRequestDto.getScriptId();
	}

	public static ScriptEntity create(ScriptAddDto.Request scriptAddRequestDto) {
		return new ScriptEntity(scriptAddRequestDto);
	}

	/*// 정적 메서드 사용시 기존 엔티티 값을 받아올 수 없어 response 에 있는 createdAt 이 null 로 뜨게됨
	public ScriptEntity update(ScriptUpdateDto.Request scriptUpdateRequestDto) {
		return new ScriptEntity(scriptUpdateRequestDto);
	}*/

	public static ScriptEntity update(ScriptUpdateDto.Request scriptUpdateRequestDto) {
		return new ScriptEntity(scriptUpdateRequestDto);
	}

	public static ScriptEntity delete(ScriptIsUseDto.Request scriptIsUseRequestDto) {
		return new ScriptEntity(scriptIsUseRequestDto);
	}
}
