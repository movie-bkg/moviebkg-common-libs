package com.sid.moviebkg.common.utils;

import java.util.function.Function;

public class FetchChildUtil {

    private FetchChildUtil() {

    }

    public static <T, R> R getChildData(T data, Function<T, R> getChild) {
        R response = null;
        if (data != null) {
            response = getChild.apply(data);
        }
        return response;
    }
}
