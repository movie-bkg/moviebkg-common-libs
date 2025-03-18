package com.sid.moviebkg.common.command.service;

import com.sid.moviebkg.common.command.dto.CommandDto;
import com.sid.moviebkg.common.command.dto.CommandRequestDto;
import com.sid.moviebkg.common.command.dto.OperationType;
import com.sid.moviebkg.common.command.dto.ServiceType;
import com.sid.moviebkg.common.dto.movie.MovieDto;
import com.sid.moviebkg.common.model.movie.Movie;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class MovieCommandServiceImpl implements CreateCommandService<Movie> {

    private final ModelMapper modelMapper;

    public MovieCommandServiceImpl(@Qualifier("movieModelMapper") ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public CommandDto create(CommandRequestDto<Movie> commandRequestDto, OperationType operationType, ServiceType serviceType) {
        CommandDto commandDto = null;
        if (commandRequestDto != null && commandRequestDto.getRequestObj() != null) {
            Movie movie = commandRequestDto.getRequestObj();
            MovieDto movieDto = modelMapper.map(movie, MovieDto.class);
            commandDto = CommandDto.builder()
                    .traceId(getTraceId()).commandKey(commandRequestDto.getCommandKey())
                    .groupKey(commandRequestDto.getGroupKey()).operationType(operationType)
                    .payload(movieDto)
                    .serviceType(serviceType)
                    .initiatedBy(commandRequestDto.getInitiatedBy())
                    .dateTime(commandRequestDto.getDateTime()).build();
        }
        return commandDto;
    }
}
