package com.speech.up.common.aspect;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.read.ListAppender;

public class LoggingAspectTest {

	private ListAppender<ch.qos.logback.classic.spi.ILoggingEvent> listAppender;

	@InjectMocks
	private LoggingAspect loggingAspect;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		LoggerContext loggerContext = (LoggerContext)LoggerFactory.getILoggerFactory();
		listAppender = new ListAppender<>();
		listAppender.setContext(loggerContext);
		listAppender.start();
		((ch.qos.logback.classic.Logger)LoggerFactory.getLogger(LoggingAspect.class)).addAppender(listAppender);
	}

	@Test
	void testLogAfterThrowing() {
		// Given
		RuntimeException exception = new RuntimeException("Test Exception");

		// When
		loggingAspect.logAfterThrowing(exception);

		// Then
		var logs = listAppender.list;
		assertTrue(logs.stream()
			.anyMatch(event -> event.getFormattedMessage().contains("Exception in method: Test Exception")));
	}
}
