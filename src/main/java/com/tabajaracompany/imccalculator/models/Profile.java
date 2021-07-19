package com.tabajaracompany.imccalculator.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Profile implements GrantedAuthority {

  private static final long serialVersionUID = 1L;

  @Id
  private UUID id;

  private String name;

  public Profile(String name) {
    this.id = UUID.randomUUID();
    this.name = name;
  }


  @Override
  public String getAuthority() {
    return name;
  }
}
