// GlobalExceptionHandler.java
package com.ahmed.media_sense_api.exception;

import com.ahmed.media_sense_api.dto.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ApiError> handleUserAlreadyExists(UserAlreadyExistsException ex, HttpServletRequest request) {
        System.out.println("Handling UserAlreadyExistsException: {}" + ex.getMessage());
        return buildError(HttpStatus.BAD_REQUEST, ex.getMessage(), request);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ApiError> handleInvalidCredentials(InvalidCredentialsException ex, HttpServletRequest request) {
        return buildError(HttpStatus.UNAUTHORIZED, ex.getMessage(), request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleOther(Exception ex, HttpServletRequest request) {
        return buildError(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error", request);
    }

    private ResponseEntity<ApiError> buildError(HttpStatus status, String message, HttpServletRequest request) {
        ApiError error = new ApiError(
                status.value(),
                status.getReasonPhrase(),
                message,
                request.getRequestURI()
        );
        System.out.println("Handling Error: {}" + error);
        return ResponseEntity.status(status).body(error);
    }
}
