package com.example.demotest.exception;

import com.example.demotest.dto.ResponseData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class ExceptionHandlers {

        @ResponseStatus(HttpStatus.BAD_REQUEST)
        @ExceptionHandler(ServiceExceptionHolder.ServerNotFoundException.class)
        @ResponseBody
        public ResponseEntity<ResponseData> handleServerNotFoundException(final ServiceExceptionHolder.ServerNotFoundException ex) {
                return new ResponseEntity<ResponseData>(
                        new ResponseData().failed(ex.getMessage(), ex.getCode()),
                        HttpStatus.NOT_FOUND);
        }

        @ResponseStatus(HttpStatus.BAD_REQUEST)
        @ExceptionHandler(ServiceExceptionHolder.NotFoundException.class)
        @ResponseBody
        public ResponseEntity<ResponseData> handleNotFoundException(final ServiceExceptionHolder.NotFoundException ex) {
                System.out.println("Not found is working");
                return new ResponseEntity<ResponseData>(
                        new ResponseData().failed(ex.getMessage(), ex.getCode()),
                        HttpStatus.BAD_REQUEST);
        }

        @ResponseStatus(HttpStatus.BAD_REQUEST)
        @ExceptionHandler(MethodArgumentNotValidException.class)
        @ResponseBody
        public ResponseEntity<ResponseData> methodArgumentNotValidExceptionEntity(MethodArgumentNotValidException ex) {
                HashMap<String, String> errors = new HashMap<>();
                ex.getBindingResult().getAllErrors().forEach(error -> {
                        errors.put(((FieldError) error).getField(), error.getDefaultMessage());
                });
                return new ResponseEntity<ResponseData>(
                        new ResponseData().error(errors, "Validation failed"),
                        HttpStatus.BAD_REQUEST);
        }


}
