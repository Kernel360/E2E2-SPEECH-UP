package com.speech.up.common.aspect;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

	@AfterThrowing(pointcut = "execution(* com.speech.up.user.service.*.*(..))", throwing = "exception")
	public void logAfterThrowing(Exception exception) {
		logger.error("Exception in method: {}", exception.getMessage(), exception);
	}
}