package com.example.order2.handle;

import com.example.order2.exception.BusinessException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> handle(BusinessException ex) {
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(ex.getMsg());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handle(EntityNotFoundException ex) {
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorReponse> handle(MethodArgumentNotValidException ex) {

        var errors = new HashMap<String, String>();
        ex.getBindingResult().getAllErrors()
                .forEach(
                        error -> {
                            var fieldName = ((FieldError)error).getField();
                            var errorMessage = error.getDefaultMessage();
                            errors.put(fieldName, errorMessage);
                        }
                );
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST) // Hoặc NOT_FOUND nếu bạn cố tình
                .body(new ErrorReponse(errors));

    }
}
