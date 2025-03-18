package com.sid.moviebkg.common.command.service;

import com.sid.moviebkg.common.command.dto.CommandDto;
import com.sid.moviebkg.common.command.dto.CommandRequestDto;
import com.sid.moviebkg.common.command.dto.OperationType;
import com.sid.moviebkg.common.command.dto.ServiceType;
import org.slf4j.MDC;

public interface CreateCommandService<T> {
    CommandDto create(CommandRequestDto<T> payloadObj, OperationType operationType, ServiceType serviceType);

    default String getTraceId() {
        String traceId = null;
        if (MDC.getCopyOfContextMap().containsKey("trace_id")) {
            traceId = MDC.get("trace_id");
        } else if (MDC.getCopyOfContextMap().containsKey("traceId")) {
            traceId = MDC.get("traceId");
        }
        return traceId;
    }
}
