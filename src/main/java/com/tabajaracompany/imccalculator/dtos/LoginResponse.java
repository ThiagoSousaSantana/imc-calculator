package com.tabajaracompany.imccalculator.dtos;

import lombok.Data;

@Data
public class LoginResponse {

    private String token;
    private String type;

    public LoginResponse(String token, String type) {
        this.token = token;
        this.type = type;
    }
}
