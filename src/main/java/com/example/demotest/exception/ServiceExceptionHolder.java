package com.example.demotest.exception;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

public class ServiceExceptionHolder {

    @Getter
    @RequiredArgsConstructor
    public static class ServiceException extends RuntimeException{
        private final int code;
        private final String message;
        private final JsonNode error;
    }

    public static class ResourceNotFoundException extends ServiceException{
        public ResourceNotFoundException(int code, String message, JsonNode error){
            super(code,message,error);
        }
    }

    public static class ServerNotFoundException extends ResourceNotFoundException{
        public ServerNotFoundException(final String message,final int code){
            super(code,message,null);
        }
    }

    public static class ClientNotFoundException extends ResourceNotFoundException{
        public ClientNotFoundException(final String message, final int code){
            super(code,message,null);
        }
    }

    public static class NotFoundException extends ResourceNotFoundException{
        public NotFoundException(final String message){
            super(HttpStatus.BAD_REQUEST.value(),message,null);
        }
    }

    public static class CustomException extends ResourceNotFoundException {
        public CustomException(final String msg, final int code) {
            super(code, msg,null);
        }
    }
}
