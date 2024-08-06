package com.speech.up.script.entity;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.speech.up.script.service.dto.RecordAddDto;
import com.speech.up.script.service.dto.RecordIsUseDto;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@DynamicUpdate
@Table(name = "record")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class  RecordEntity extends BaseRecordEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long recordId;

	private String audioPath;

	private String languageCode;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "script_id", nullable = false)
	@JsonBackReference
	private ScriptEntity script;

	private boolean isUse;

	private RecordEntity(String audioPath, String languageCode, ScriptEntity script, boolean isUse) {
		this.audioPath = audioPath;
		this.languageCode = languageCode;
		this.isUse = isUse;
		this.script = script;
	}

	// 녹음 생성
	private RecordEntity(RecordAddDto.Request recordAddRequestDto) {
		this(recordAddRequestDto.getAudioPath(), recordAddRequestDto.getLanguageCode(), recordAddRequestDto.getScript(),
			true);
	}

	// 녹음 삭제
	private RecordEntity(RecordIsUseDto.Request recordIsUseRequestDto) {
		this(recordIsUseRequestDto.getAudioPath(), recordIsUseRequestDto.getLanguageCode(),
			recordIsUseRequestDto.getScript(), false);
		this.recordId = recordIsUseRequestDto.getRecordId();
	}

	public static RecordEntity create(RecordAddDto.Request recordAddRequestDto) {
		return new RecordEntity(recordAddRequestDto);
	}

	public static RecordEntity delete(RecordIsUseDto.Request recordIsUseRequestDto) {
		return new RecordEntity(recordIsUseRequestDto);
	}
}
