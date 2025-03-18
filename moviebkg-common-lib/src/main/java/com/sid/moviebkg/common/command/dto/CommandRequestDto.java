package com.sid.moviebkg.common.command.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommandRequestDto<T> implements Serializable {
    private T requestObj;
    private String commandKey;
    private String groupKey;
    private String initiatedBy;
    private LocalDateTime dateTime;
}
