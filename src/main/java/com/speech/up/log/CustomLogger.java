package com.speech.up.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.speech.up.board.entity.BoardEntity;
import com.speech.up.board.service.dto.BoardAddDto;
import com.speech.up.report.entity.ReportEntity;
import com.speech.up.record.entity.RecordEntity;
import com.speech.up.script.entity.ScriptEntity;
import com.speech.up.record.service.dto.RecordAddDto;
import com.speech.up.script.service.dto.ScriptAddDto;
import com.speech.up.user.entity.UserEntity;

@Component
public class CustomLogger {
	private static final Logger logger = LoggerFactory.getLogger(CustomLogger.class);

	//어플리케이션
	public void startApplication() {
		logger.info("어플리케이션 시작 중 ...");
	}

	public void endApplication() {
		logger.info("어플리케이션 종료 중 ...");
	}

	//Request
	public void requestLog(ScriptAddDto.Request request) {
		logger.debug("Script Add Request Log : {}", request);
	}

	/*public void requestLog(ReportAddDto.Request request) {
		logger.debug("Report Add Request Log : {}", request);
	}*/

	public void requestLog(RecordAddDto.Request request) {
		logger.debug("Record Add Request Log : {}", request);
	}

	public void requestLog(BoardAddDto.Request request) {
		logger.debug("Board Add Request Log : {}", request);
	}

	//Response

	// Controller

	// Service

	// DataBase
	public void entityLog(UserEntity userEntity) {
		logger.debug("UserEntity: {}", userEntity);
	}

	public void ReportLog(ReportEntity reportEntity) {
		logger.debug("ReportEntity: {}", reportEntity);
	}

	public void boardLog(BoardEntity boardEntity) {
		logger.debug("BoardEntity: {}", boardEntity);
	}

	public void scriptLog(ScriptEntity scriptEntity) {
		logger.debug("ScriptEntity: {}", scriptEntity);
	}

	public void recordLog(RecordEntity recordEntity) {
		logger.debug("RecordEntity: {}", recordEntity);
	}

	//error
	public void error(Exception e) {
		logger.error("에러 발생 : {}", e.getMessage(), e);

	}

}
