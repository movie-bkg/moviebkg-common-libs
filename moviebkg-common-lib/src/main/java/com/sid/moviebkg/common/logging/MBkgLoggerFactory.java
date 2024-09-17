package com.sid.moviebkg.common.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MBkgLoggerFactory {

    private MBkgLoggerFactory() {}

    public static MBkgLogger getLogger(Class<?> clazz) {
        return getLogger(clazz.getName());
    }

    public static MBkgLogger getLogger(String name) {
        Logger slf4jLogger = LoggerFactory.getLogger(name);
        return new MBkgLogger(slf4jLogger);
    }
}
