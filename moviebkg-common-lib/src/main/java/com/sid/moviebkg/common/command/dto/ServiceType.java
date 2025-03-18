package com.sid.moviebkg.common.command.dto;

import lombok.Getter;

@Getter
public enum ServiceType {
    UPCOMING("Upcoming"),
    COMPLETED("Completed");

    public final String type;
    ServiceType(String type) {
        this.type = type;
    }
}
