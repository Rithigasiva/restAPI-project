package com.examly.springapp.aspects;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
    private static final String LOG_DIRECTORY = "logs";
    private static final String LOG_FILE = "logs/application.log";

    public LoggingAspect() {
        createLogFolderAndFile();
    }

    private void createLogFolderAndFile() {
        try {
            File logDir = new File(LOG_DIRECTORY);
            if (!logDir.exists()) {
                logDir.mkdir();
                logger.info("Log folder created: {}", LOG_DIRECTORY);
            }
            if (!Files.exists(Paths.get(LOG_FILE))) {
                Files.createFile(Paths.get(LOG_FILE));
                logger.info("Log file created: {}", LOG_FILE);
            }
        } catch (Exception e) {
            logger.error("Error creating log folder or file", e);
        }
    }

    @Pointcut("execution(* com.examly.springapp.eventservice.EventService.getAllEvents(..))")
    public void getAllEventsMethod() {}

    @Before("getAllEventsMethod()")
    public void logBefore() {
        logger.info("Fetching all events...");
    }

    @AfterReturning(pointcut = "getAllEventsMethod()", returning = "result")
    public void logAfterReturning(Object result) {
        if (result instanceof List<?>) {
            logger.info("Successfully fetched all events, count: {}", ((List<?>) result).size());
        } else if (result == null) {
            logger.warn("Fetched events, but result is null");
        } else {
            logger.warn("Unexpected return type: {}", result.getClass().getName());
        }
    }
}