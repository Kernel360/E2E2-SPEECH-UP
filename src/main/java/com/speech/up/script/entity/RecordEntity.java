package com.speech.up.script.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.speech.up.script.service.dto.RecordAddDto;
import com.speech.up.script.service.dto.RecordGetDto;
import com.speech.up.script.service.dto.RecordIsUseDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "record")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RecordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recordId;

    private String audioPath;

    private String languageCode;

    private LocalDateTime createdAt;

    private boolean isUse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "script_id", nullable = false)
    @JsonBackReference
    private ScriptEntity script;

    // 녹음 생성
    public RecordEntity(RecordAddDto.RecordAddRequestDto recordAddRequestDto) {
        this.audioPath = recordAddRequestDto.getAudioPath();
        this.languageCode = recordAddRequestDto.getLanguageCode();
        this.createdAt = LocalDateTime.now();
        this.isUse = true;
        this.script = recordAddRequestDto.getScript();
    }

    // 녹음 삭제
    public RecordEntity(RecordIsUseDto.RecordIsUseRequestDto recordIsUseRequestDto) {
        this.recordId = recordIsUseRequestDto.getRecordId();
        this.audioPath = recordIsUseRequestDto.getAudioPath();
        this.languageCode = recordIsUseRequestDto.getLanguageCode();
        this.createdAt = recordIsUseRequestDto.getCreatedAt();
        this.isUse = recordIsUseRequestDto.isUse();
        this.script = recordIsUseRequestDto.getScript();
    }
}
