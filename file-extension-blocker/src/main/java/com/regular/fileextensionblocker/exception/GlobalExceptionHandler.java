package com.regular.fileextensionblocker.exception;

import com.regular.fileextensionblocker.dto.ExtensionDtos;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidExtensionException.class)
    public ResponseEntity<ExtensionDtos.ErrorResponse> handleInvalid(InvalidExtensionException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ExtensionDtos.ErrorResponse("INVALID_EXTENSION", e.getMessage()));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExtensionDtos.ErrorResponse> handleBadRequest(BadRequestException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ExtensionDtos.ErrorResponse("BAD_REQUEST", e.getMessage()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExtensionDtos.ErrorResponse> handleNotFound(NotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ExtensionDtos.ErrorResponse("NOT_FOUND", e.getMessage()));
    }

    @ExceptionHandler(DuplicateExtensionException.class)
    public ResponseEntity<ExtensionDtos.ErrorResponse> handleDuplicate(DuplicateExtensionException e) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ExtensionDtos.ErrorResponse("DUPLICATE_EXTENSION", e.getMessage()));
    }

    @ExceptionHandler(CustomLimitExceededException.class)
    public ResponseEntity<ExtensionDtos.ErrorResponse> handleLimit(CustomLimitExceededException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ExtensionDtos.ErrorResponse("CUSTOM_LIMIT_EXCEEDED", e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExtensionDtos.ErrorResponse> handleValidation(MethodArgumentNotValidException e) {
        String msg = e.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .filter(fe -> fe.getDefaultMessage() != null)
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .orElse("요청 값이 올바르지 않습니다.");

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ExtensionDtos.ErrorResponse("VALIDATION_ERROR", msg));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExtensionDtos.ErrorResponse> handleIllegalArg(IllegalArgumentException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ExtensionDtos.ErrorResponse("BAD_REQUEST", e.getMessage()));
    }
}
