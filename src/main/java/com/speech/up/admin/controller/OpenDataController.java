package com.speech.up.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.speech.up.admin.dto.StatisticsGet;
import com.speech.up.admin.service.StatisticsService;

import lombok.RequiredArgsConstructor;

/**
 * OpenDataController 는 스케줄링 한 데이터를 클라이언트들에게 오픈하기 위한 컨트롤러입니다.
 *
 * <p>이 컨트롤러는 매 정각마다 데이터를 스케줄링을 통해 통계로 집약된
 * 테이블을 접근 할 수 있는 엔드포인트를 제공합니다.</p>
 *
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/open/data")
public class OpenDataController {

	private final StatisticsService statisticsService;

	/**
	 * 통계 테이블의 가장 최근 데이터를 반환합니다.
	 *
	 */
	@GetMapping("")
	public StatisticsGet.Response getStatistics(){
		return statisticsService.getStatistics();
	}
}
