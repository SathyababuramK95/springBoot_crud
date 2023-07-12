package com.example.springBoot_crud.Employee.Services;

import com.example.springBoot_crud.Employee.models.RefreshToken;
import com.example.springBoot_crud.Employee.models.RefreshTokenRequest;
import com.example.springBoot_crud.Employee.repositories.EmployeeRepository;
import com.example.springBoot_crud.Employee.repositories.RefreshTokenRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private RefreshTokenRespository refreshTokenRespository;

    public RefreshToken createRefreshToken(String email){
        RefreshToken refreshToken = RefreshToken.builder()
                .employee(employeeRepository.findByEmail(email))
                .token(UUID.randomUUID().toString())
                .expiryDate(Date.from(Instant.now().plusMillis(600000)))
                .build();

        return refreshTokenRespository.save(refreshToken);
    }

    public Optional<RefreshToken> findByToken(RefreshTokenRequest refreshToken){
        return refreshTokenRespository.findByToken(refreshToken.getToken());
    }
    public RefreshToken checkValidityOfToken(RefreshToken refreshToken){
        if(refreshToken.getExpiryDate().compareTo(Date.from(Instant.now())) < 0){
            refreshTokenRespository.delete(refreshToken);
            throw new RuntimeException("Token Expired please login again");
        }
        return refreshToken;
    }
}
