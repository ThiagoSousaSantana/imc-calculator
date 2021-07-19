package com.tabajaracompany.imccalculator.dtos;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Email;

@Data
public class UserRequest {

  private String name;

  @Email
  @Column(unique = true)
  private String email;

  private String password;
}
