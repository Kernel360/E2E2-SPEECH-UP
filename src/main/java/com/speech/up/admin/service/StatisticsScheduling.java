package com.speech.up.admin.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.speech.up.admin.dto.StatisticsAdd;
import com.speech.up.admin.entity.StatisticsEntity;
import com.speech.up.admin.repository.StatisticsRepository;
import com.speech.up.record.repository.RecordRepository;
import com.speech.up.report.entity.dto.ReportGetDto;
import com.speech.up.report.repository.ReportRepository;
import com.speech.up.script.repository.ScriptRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@EnableScheduling
@RequiredArgsConstructor
public class StatisticsScheduling {
	private final ScriptRepository scriptRepository;
	private final ReportRepository reportRepository;
	private final RecordRepository recordRepository;
	private final StatisticsRepository statisticsRepository;


	@Scheduled(cron = "0 0 1 * * *")
	@Transactional
	public void runStatisticsSetter() {
		statisticsSetter();
	}

	void statisticsSetter(){
		final long scriptCount = scriptRepository.count();
		final long reportCount = reportRepository.count();
		final long recordCount = recordRepository.count();

		StatisticsAdd.Request request = StatisticsAdd.Request
			.builder()
			.report(reportCount)
			.record(recordCount)
			.script(scriptCount)
			.score(getAvgScore())
			.createAt(LocalDateTime.now())
			.build();
		StatisticsEntity statisticsEntity = StatisticsEntity.create(request);

		statisticsRepository.save(statisticsEntity);
	}

	double getAvgScore(){
		List<ReportGetDto.Response> result = reportRepository.findAllBy();
		return result.stream().mapToDouble(ReportGetDto.Response::getScore)
			.average().orElse(0.0);
	}

}
