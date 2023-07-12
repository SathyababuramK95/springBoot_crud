package com.example.springBoot_crud.Employee.controllers;

import com.example.springBoot_crud.Employee.AuthenticationExceptionHandler;
import com.example.springBoot_crud.Employee.EmployeeNotFoundException;
import com.example.springBoot_crud.Employee.models.LoginRequest;
import com.example.springBoot_crud.Employee.Services.EmployeeService;
import com.example.springBoot_crud.Employee.Services.RefreshTokenService;
import com.example.springBoot_crud.Employee.models.Employee;
import com.example.springBoot_crud.Employee.models.JwtResponse;
import com.example.springBoot_crud.Employee.models.RefreshToken;
import com.example.springBoot_crud.Employee.models.RefreshTokenRequest;
import com.example.springBoot_crud.Employee.security.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/employee/auth")
public class AuthenticationController {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @PostMapping(path = "/login")
    public JwtResponse userLogin(@RequestBody LoginRequest loginRequest){
        logger.info("user trying to login with the email" + loginRequest.getEmail());
        Employee employee = employeeService.findEmployeeByEmail(loginRequest.getEmail());
        String employee_mail = employee.getEmail();
        if(employee_mail.isEmpty()){
             throw new EmployeeNotFoundException("Employee not found with the given mail");
        }
        String decryped_password = jwtUtils.DecryptString(employee.getPassword());
        String loginReqPassword = loginRequest.getPassword();

        if(decryped_password.equals(loginReqPassword)){
            return JwtResponse.builder()
                    .access_token(jwtUtils.generateJwtToken(employee))
                    .token(refreshTokenService.createRefreshToken(employee.getEmail()).getToken())
                    .build();
        }else{
            throw new AuthenticationExceptionHandler("Invalid Credentials");
        }

    }

    @PostMapping(path = "/refreshToken")
    public JwtResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){
        logger.info("user trying to refresh token" + refreshTokenRequest.getToken());
        return refreshTokenService.findByToken(refreshTokenRequest)
                .map(refreshTokenService::checkValidityOfToken)
                .map(RefreshToken::getEmployee)
                .map(employee -> {
                    String access_token = jwtUtils.generateJwtToken(employee);
                    return JwtResponse.builder()
                            .access_token(access_token)
                            .token(refreshTokenRequest.getToken())
                            .build();
                }).orElseThrow(() -> new RuntimeException("Invalid token"));
    }
}
