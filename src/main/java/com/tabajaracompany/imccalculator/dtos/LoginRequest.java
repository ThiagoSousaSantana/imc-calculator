package com.tabajaracompany.imccalculator.dtos;

import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Data
public class LoginRequest {

  private String email;
  private String password;

  public UsernamePasswordAuthenticationToken authenticationToken() {
    return new UsernamePasswordAuthenticationToken(email, password);
  }
}
