package com.sid.moviebkg.common.utils;

import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.function.Predicate;

@Service
public class ExceptionUtils {

    public <T extends Exception, R extends RuntimeException> R getException(T exception, Function<T, R> getFlowExceptionFn,
                                                                            Predicate<T> isInstanceOfFn) {
        R exp;
        if (isInstanceOfFn.test(exception)) {
            exp = (R) exception;
        } else {
            exp = getFlowExceptionFn.apply(exception);
        }
        return exp;
    }
}
