package com.sid.moviebkg.common.utils;

import com.sid.moviebkg.common.logging.MBkgLogger;
import com.sid.moviebkg.common.logging.MBkgLoggerFactory;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Service;

@Service
public class StopWatch {

    private MBkgLogger logger = MBkgLoggerFactory.getLogger(StopWatch.class);

    public void start(String operation, Exchange exchange) {
        exchange.setProperty(operation + "StartTime", System.currentTimeMillis());
    }

    public void stop(String operation, Exchange exchange) {
        try {
            exchange.setProperty(operation + "StopTime", System.currentTimeMillis());
            exchange.setProperty(operation + "ElapsedTime", elapsed(operation, exchange));
        } catch (Exception e) {
            logger.warn("Exception occurred while setting StopTime/ElapsedTime:{}. Exception:{}", operation, exchange, e);
        }
    }

    public long elapsed(String operation, Exchange exchange) {
        long startTime = 0L;
        long stopTime = 0L;

        try {
            stopTime = exchange.getProperty(operation + "StopTime", Long.TYPE);
            startTime = exchange.getProperty(operation + "StartTime", Long.TYPE);
        } catch (Exception e) {
            logger.warn("Exception occurred while setting ElapsedTime:{}. Exception:{}", operation, exchange, e);
        }
        return stopTime - startTime;
    }
}
