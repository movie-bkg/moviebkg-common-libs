package com.sid.moviebkg.common.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ResponseMsgDto implements Serializable {
    private String exception;
    private List<MessageDto> messages;
}
