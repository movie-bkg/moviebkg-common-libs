package com.sid.moviebkg.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResponseMsgDto implements Serializable {
    private String exception;
    private List<MessageDto> messages;
}
