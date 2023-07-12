package com.example.springBoot_crud.Employee;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class EmployeeExceptionController {
        @ExceptionHandler
        public ResponseEntity<Object> exception(EmployeeNotFoundException e){
            return new ResponseEntity<>("Employee with the given ID was not available", HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler
        public ResponseEntity<Object> exception(AuthenticationExceptionHandler e){
            Map<String,Object> errorResponse = new HashMap<>();
            errorResponse.put("Status",HttpStatus.UNAUTHORIZED.value());
            errorResponse.put("message",e);
            return ResponseEntity.status(403).body(errorResponse);
        }
}