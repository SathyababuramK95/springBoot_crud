package com.example.springBoot_crud.Employee;

public class AuthenticationExceptionHandler extends  RuntimeException{

    public AuthenticationExceptionHandler(String message){
        super(message);
    }
}
