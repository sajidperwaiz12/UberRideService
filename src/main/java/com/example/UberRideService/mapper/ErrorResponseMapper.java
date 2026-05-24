package com.example.UberRideService.mapper;

import com.example.UberRideService.dto.ErrorResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Mapper(
        componentModel = "spring",
        imports = LocalDateTime.class
)
public interface ErrorResponseMapper {
    @Mapping(target = "timestamp", expression = "java(LocalDateTime.now())")
    @Mapping(target = "status", expression = "java(status.value())")
    @Mapping(target = "error", expression = "java(status.getReasonPhrase())")
    @Mapping(target = "message", source = "message")
    @Mapping(target = "path", source = "path")
    ErrorResponseDto toErrorResponseDto(
            String message,
            String path,
            HttpStatus status
    );
}