package com.sid.moviebkg.common.utils;

import com.sid.moviebkg.common.dto.MessageDto;
import com.sid.moviebkg.common.dto.ResponseMsgDto;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.*;

public class ResponseMsgDtoUtil {

    private ResponseMsgDtoUtil() {}

    public static MessageDto getMessageDto(String code, String message) {
        return MessageDto.builder().code(code).message(message).build();
    }

    public static ResponseMsgDto getRespMsgDto(String exception, List<MessageDto> messageDtoList) {
        return ResponseMsgDto.builder().exception(exception).messages(messageDtoList).build();
    }

    public static ResponseMsgDto getResponseMsgDto(String exception, String code, String message) {
        MessageDto messageDto = getMessageDto(code, message);
        return getRespMsgDto(exception, Arrays.asList(messageDto));
    }

    public static MessageDto findMsgDto(String key, List<MessageDto> messageDtos) {
        return messageDtos.stream().filter(messageDto -> messageDto.getCode() != null && StringUtils.hasText(messageDto.getCode())
                && messageDto.getCode().equals(key)).findAny().orElse(null);
    }

    public static ResponseMsgDto findMsgAndPopulateResponse(String exception, String key, List<MessageDto> messageDtos) {
        MessageDto messageDto = findMsgDto(key, messageDtos);
        return messageDto != null ? getRespMsgDto(exception, Arrays.asList(messageDto)) : null;
    }

    public static ResponseMsgDto getRespMsg(String errorCode, String expMsg, List<MessageDto> messageDtos, Object... arguments) {
        MessageDto msgDto = findMsgDto(errorCode, messageDtos);
        List<MessageDto> formattedMsgDtos = new ArrayList<>();
        if (msgDto != null && StringUtils.hasText(msgDto.getMessage())) {
            String message = msgDto.getMessage().replaceAll("'", "''");
            message = MessageFormat.format(message, arguments);
            MessageDto messageDto = getMessageDto(errorCode, message);
            messageDto.setParams(getKeys(arguments));
            formattedMsgDtos.add(messageDto);
        }
        return getRespMsgDto(expMsg, formattedMsgDtos);
    }

    public static ResponseMsgDto getRespMsg(Map<String, Object[]> errorCodeArgs, String expMsg, List<MessageDto> messageDtos) {
        List<MessageDto> formattedMsgDtos = new ArrayList<>();
        for (Map.Entry<String, Object[]> entry : errorCodeArgs.entrySet()) {
            String errorCode = entry.getKey();
            Object[] arguments = entry.getValue();
            formattedMsgDtos.add(getFormattedMsgDto(errorCode, messageDtos, arguments));
        }
        return getRespMsgDto(expMsg, formattedMsgDtos);
    }

    private static MessageDto getFormattedMsgDto(String errorCode, List<MessageDto> messageDtos, Object[] arguments) {
        MessageDto msgDto = findMsgDto(errorCode, messageDtos);
        if (msgDto != null && StringUtils.hasText(msgDto.getMessage())) {
            String message = msgDto.getMessage().replaceAll("'", "''");
            message = MessageFormat.format(message, arguments);
            MessageDto messageDto = getMessageDto(errorCode, message);
            messageDto.setParams(getKeys(arguments));
            msgDto = messageDto;
        }
        return msgDto;
    }

    private static Map<String, Object> getKeys(Object[] arguments) {
        Map<String, Object> keys = new HashMap<>();
        for (int index = 0; index < arguments.length; index++) {
            keys.put(String.valueOf(index), arguments[index]);
        }
        return keys;
    }
}
