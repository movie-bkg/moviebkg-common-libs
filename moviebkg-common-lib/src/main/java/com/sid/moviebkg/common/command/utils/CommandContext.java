package com.sid.moviebkg.common.command.utils;

import com.sid.moviebkg.common.command.dto.CommandDto;

import java.util.ArrayList;
import java.util.List;

public class CommandContext {
    private static final ThreadLocal<List<CommandDto>> commandsThreadLocal = ThreadLocal.withInitial(ArrayList::new);

    private CommandContext() {}

    public static void setCommands(List<CommandDto> list) {
        commandsThreadLocal.set(list);
    }

    public static void unset() {
        commandsThreadLocal.remove();
    }

    public static List<CommandDto> getCommands() {
        return commandsThreadLocal.get();
    }
}
