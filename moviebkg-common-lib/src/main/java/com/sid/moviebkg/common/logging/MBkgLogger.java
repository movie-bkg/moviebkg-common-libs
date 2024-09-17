package com.sid.moviebkg.common.logging;

import com.sid.moviebkg.common.exceptions.utils.ErrorDetails;
import org.apache.camel.Exchange;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;

public class MBkgLogger {

    Logger logger;

    public MBkgLogger(Logger slf4jLogger) {
        this.logger = slf4jLogger;
    }

    public void trace(String s) {
        logger.trace(s);
    }

    public void trace(String s, Object o) {
        logger.trace(s, o);
    }

    public void trace(String s, Object o1, Object o2) {
        logger.trace(s, o1, o2);
    }

    public void trace(String s, Object... objects) {
        logger.trace(s, objects);
    }

    public void trace(String s, Throwable throwable) {
        logger.trace(s, throwable);
    }

    public void debug(String s) {
        logger.debug(s);
    }

    public void debug(String s, Object o) {
        logger.debug(s, o);
    }

    public void debug(String s, Object o1, Object o2) {
        logger.debug(s, o1, o2);
    }

    public void debug(String s, Object... objects) {
        logger.debug(s, objects);
    }

    public void debug(String s, Throwable throwable) {
        logger.debug(s, throwable);
    }

    public void info(String s) {
        logger.trace(s);
    }

    public void info(String s, Object o) {
        logger.info(s, o);
    }

    public void info(String s, Object o1, Object o2) {
        logger.info(s, o1, o2);
    }

    public void info(String s, Object... objects) {
        logger.info(s, objects);
    }

    public void info(String s, Throwable throwable) {
        logger.info(s, throwable);
    }

    public void warn(String s) {
        logger.warn(s);
    }

    public void warn(String s, Object o) {
        logger.warn(s, o);
    }

    public void warn(String s, Object o1, Object o2) {
        logger.warn(s, o1, o2);
    }

    public void warn(String s, Object... objects) {
        logger.warn(s, objects);
    }

    public void warn(String s, Throwable throwable) {
        logger.warn(s, throwable);
    }

    public void error(String s) {
        logger.error(s);
    }

    public void error(String s, Object o) {
        logger.error(s, o);
    }

    public void error(String s, Object o1, Object o2) {
        logger.error(s, o1, o2);
    }

    public void error(String s, Object... objects) {
        logger.error(s, objects);
    }

    public void error(String s, Throwable throwable) {
        logger.error(s, throwable);
    }

    private void commonErrorCode(String message, Exchange exchange, Throwable cause) {
        String errDesc = "";
        ErrorDetails errorDetails = new ErrorDetails();
        if (cause != null) {
            errorDetails.setErrorCode(ExceptionUtils.getMessage(cause));
            errorDetails.setErrorDesc(ExceptionUtils.getRootCauseMessage(cause));
            errorDetails.setStackTrace(ExceptionUtils.getStackTrace(cause));
            errDesc = errorDetails.getErrorDesc();
        }
        logger.error("{} {} {}", message, errorDetails, errDesc, cause);
    }
}
