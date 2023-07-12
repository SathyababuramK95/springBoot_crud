package com.example.springBoot_crud.Employee.models;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class JwtResponse {

    private String access_token;
    private String token;
}
