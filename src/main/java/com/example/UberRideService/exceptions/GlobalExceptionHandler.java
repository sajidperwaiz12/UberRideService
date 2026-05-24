package com.example.UberRideService.exceptions;

import com.example.UberRideService.dto.ErrorResponseDto;
import com.example.UberRideService.mapper.ErrorResponseMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final ErrorResponseMapper errorResponseMapper;

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponseDto> handleBadRequestException(BadRequestException e, HttpServletRequest request) {
        ErrorResponseDto errorResponse = errorResponseMapper.toErrorResponseDto(e.getMessage(), request.getRequestURI(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException e, HttpServletRequest request) {
        ErrorResponseDto errorResponse = errorResponseMapper.toErrorResponseDto(e.getMessage(), request.getRequestURI(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGenericException(Exception e, HttpServletRequest request) {
        ErrorResponseDto errorResponse = errorResponseMapper.toErrorResponseDto(e.getMessage(), request.getRequestURI(), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

