package com.example.springBoot_crud.Employee;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class EmployeeExceptionController {
        @ExceptionHandler(value = EmployeeNotFoundException.class)
        public ResponseEntity<Object> exception(EmployeeNotFoundException employeeNotFoundException){
            return new ResponseEntity<>("Employee with the given ID was not available", HttpStatus.NOT_FOUND);
        }
}