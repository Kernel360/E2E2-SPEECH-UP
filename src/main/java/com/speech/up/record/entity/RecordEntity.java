package com.speech.up.record.entity;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.web.multipart.MultipartFile;

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
import lombok.ToString;

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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "script_id", nullable = false)
	@JsonBackReference
	private ScriptEntity script;

	private boolean isUse;

	@OneToOne(mappedBy = "recordId", cascade = CascadeType.ALL)
	@JsonManagedReference
	private ReportEntity report;

	private RecordEntity(byte[] audio, String languageCode, ScriptEntity script, boolean isUse) {
		this.audio = audio;
		this.languageCode = languageCode;
		this.isUse = isUse;
		this.script = script;
	}

	private RecordEntity(byte[] audio, RecordAddDto.Request request, ScriptEntity scriptEntity) {
		this(audio, request.getLanguageCode(), scriptEntity, true);
	}

	public static RecordEntity create(byte[] audio, RecordAddDto.Request request, ScriptEntity scriptEntity) {
		return new RecordEntity(audio, request, scriptEntity);
	}

	private RecordEntity(RecordIsUseDto.Request recordIsUseRequestDto) {
		this(recordIsUseRequestDto.getRecordEntity().getAudio(),
			recordIsUseRequestDto.getRecordEntity()
				.getLanguageCode(),
			recordIsUseRequestDto.getRecordEntity().getScript(), false);
		this.recordId = recordIsUseRequestDto.getRecordEntity().getRecordId();
	}

	public static RecordEntity delete(RecordIsUseDto.Request recordIsUseRequestDto) {
		return new RecordEntity(recordIsUseRequestDto);
	}
}
