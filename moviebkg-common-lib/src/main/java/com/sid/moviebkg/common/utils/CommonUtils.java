package com.sid.moviebkg.common.utils;

import java.util.UUID;

public class CommonUtils {

    private CommonUtils() {}

    public static UUID getUUId() {
        return UUID.randomUUID();
    }

    public static String getUUIdWithSite(String dataCenterSite) {
        return dataCenterSite.concat(getUUId().toString());
    }

    public static String getUUIdStr() {
        return UUID.randomUUID().toString();
    }
}
