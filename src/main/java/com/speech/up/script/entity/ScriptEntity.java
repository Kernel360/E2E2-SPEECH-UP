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

	private ScriptEntity(ScriptAddDto.Request scriptAddRequestDto) {
		this(scriptAddRequestDto.getContent(), scriptAddRequestDto.getUser(), scriptAddRequestDto.getTitle(), true);
	}

	private ScriptEntity(ScriptUpdateDto.Request scriptUpdateRequestDto) {
		this(scriptUpdateRequestDto.getContent(), scriptUpdateRequestDto.getUser(), scriptUpdateRequestDto.getTitle(),true);
		this.scriptId = scriptUpdateRequestDto.getScriptId();
	}

	private ScriptEntity(ScriptIsUseDto.Request scriptIsUseRequestDto) {
		this(scriptIsUseRequestDto.getContent(), scriptIsUseRequestDto.getUser(), scriptIsUseRequestDto.getTitle(), false);
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
