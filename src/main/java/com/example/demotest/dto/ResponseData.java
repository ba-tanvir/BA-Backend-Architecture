package com.example.demotest.dto;


import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.HashMap;

@Data
public class ResponseData<T> {
    private int responseCode;
    private String message;
    private boolean success;
    private T data;

    public ResponseData success(T data){
        ResponseData object=new ResponseData<>();

        object.responseCode= HttpStatus.OK.value();
        object.success=true;
        object.data=data;
        return object;
    }

    public ResponseData failed(String message, int code){
        ResponseData object=new ResponseData();
        object.responseCode=code;
        object.success=false;
        object.message=message;
        return object;

    }

    public ResponseData error(HashMap<String, String> fieldErrors, String msg) {
        ResponseData obj = new ResponseData();
        obj.responseCode = HttpStatus.BAD_REQUEST.value();
        obj.data = fieldErrors;
        obj.message = msg;
        return obj;
    }

    public ResponseData exception(int responseCode, JsonNode message) {
        ResponseData obj = new ResponseData();
        obj.responseCode = responseCode;
        obj.success = false;
        obj.data = message;
        return obj;
    }
}


