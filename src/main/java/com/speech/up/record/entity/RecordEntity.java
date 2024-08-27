package com.speech.up.record.entity;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.speech.up.report.entity.ReportEntity;
import com.speech.up.script.entity.ScriptEntity;
import com.speech.up.record.service.dto.RecordAddDto;
import com.speech.up.record.service.dto.RecordIsUseDto;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@DynamicUpdate
@Table(name = "record")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RecordEntity extends BaseRecordEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long recordId;

	private byte[] audio;

	private String languageCode;

	private boolean isAnalyzed;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "script_id", nullable = false)
	@JsonBackReference
	private ScriptEntity script;

	private boolean isUse;

	@OneToOne(mappedBy = "recordId", cascade = CascadeType.ALL)
	@JsonManagedReference
	private ReportEntity report;

	private RecordEntity(byte[] audio, String languageCode, ScriptEntity script, boolean isUse, boolean isAnalyzed) {
		this.audio = audio;
		this.languageCode = languageCode;
		this.isUse = isUse;
		this.script = script;
		this.isAnalyzed = isAnalyzed;
	}

	private RecordEntity(byte[] audio, RecordAddDto.Request request, ScriptEntity scriptEntity) {
		this(audio, request.getLanguageCode(), scriptEntity, true, false);
	}

	private RecordEntity(RecordIsUseDto.Request recordIsUseRequestDto) {
		this(recordIsUseRequestDto.getRecordEntity().getAudio(),
			recordIsUseRequestDto.getRecordEntity()
				.getLanguageCode(),
			recordIsUseRequestDto.getRecordEntity().getScript(), true, false);
		this.recordId = recordIsUseRequestDto.getRecordEntity().getRecordId();
	}

	private RecordEntity(RecordEntity recordEntity) {
		this.recordId = recordEntity.getRecordId();
		this.audio = recordEntity.getAudio();
		this.languageCode = recordEntity.getLanguageCode();
		this.script = recordEntity.getScript();
		this.isUse = recordEntity.isUse();
		this.report = recordEntity.getReport();
		this.isAnalyzed = true;
	}

	public static void analyze(RecordEntity recordEntity) {
		new RecordEntity(recordEntity);
	}

	public static RecordEntity create(byte[] audio, RecordAddDto.Request request, ScriptEntity scriptEntity) {
		return new RecordEntity(audio, request, scriptEntity);
	}

	public static RecordEntity delete(RecordIsUseDto.Request recordIsUseRequestDto) {
		return new RecordEntity(recordIsUseRequestDto);
	}
}
