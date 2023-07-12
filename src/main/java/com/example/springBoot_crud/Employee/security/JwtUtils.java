package com.example.springBoot_crud.Employee.security;

import com.example.springBoot_crud.Employee.AuthenticationExceptionHandler;
import com.example.springBoot_crud.Employee.models.Employee;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.file.AccessDeniedException;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtils {

    public String EncryptString(String str){
        return Base64.getEncoder().encodeToString(str.getBytes());
    }

    public String DecryptString(String str){
        byte[] decodedBytes = Base64.getDecoder().decode(str);
        return  new String(decodedBytes);
    }
    @Value("${app.jwtSecretKey}")
    String jwtSecretKey;

    @Value("${app.jwtKeyExpiration}")
    long expriyDuration;
    public String generateJwtToken(Employee employee){
        long currentTime = System.currentTimeMillis();
        long tokenExpiryTime = currentTime + expriyDuration;

        Date issuedAt = new Date(currentTime);
        Date expiryAt = new Date(tokenExpiryTime);

        Claims claims = Jwts.claims()
                .setIssuer(employee.getName())
                .setIssuedAt(issuedAt)
                .setExpiration(expiryAt);


        claims.put("name",employee.getName());
        claims.put("email",employee.getEmail());
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512,jwtSecretKey)
                .compact();
    }

    public void validateJwtToken(String token) throws Exception {
        try{

//            Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJwt(token);
            Jwts.parser().setSigningKey(jwtSecretKey).parse(token);

        }
        catch (Exception e){
            throw new AuthenticationExceptionHandler("Invalid Token , Access Denied");
        }

    }

}
