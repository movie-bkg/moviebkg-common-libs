package com.sid.moviebkg.common.utils;

import java.sql.SQLException;

@FunctionalInterface
public interface BiFunction<T, U, R> {
    R apply(T t, U u) throws SQLException;
}
