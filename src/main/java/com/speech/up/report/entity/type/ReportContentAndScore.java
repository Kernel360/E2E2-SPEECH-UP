package com.speech.up.report.entity.type;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ReportContentAndScore {
	private final String content;
	private final Double score;
	private final Long recordId;

	public ReportContentAndScore(final String content, final Double score, Long recordId) {
		if(content == null || score == null){
			throw new IllegalArgumentException("Content and score cannot be null");
		}
		this.content = content;
		this.score = score;
		this.recordId = recordId;
	}
}
