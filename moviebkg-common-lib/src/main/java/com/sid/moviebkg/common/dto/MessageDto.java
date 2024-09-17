package com.sid.moviebkg.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class MessageDto implements Serializable {
    private String code;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private transient Map<String, Object> params;
}
