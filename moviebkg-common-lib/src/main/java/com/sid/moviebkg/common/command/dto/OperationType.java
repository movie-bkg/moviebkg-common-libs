package com.sid.moviebkg.common.command.dto;

import lombok.Getter;

@Getter
public enum OperationType {
    INSERT("Insert"),
    UPDATE("Update"),
    DELETE("Delete");

    public final String type;
    OperationType(String type) {
        this.type = type;
    }
}
