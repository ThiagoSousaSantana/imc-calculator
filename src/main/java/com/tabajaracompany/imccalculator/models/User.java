package com.tabajaracompany.imccalculator.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class User implements UserDetails {

  @Id
  private UUID id;

  private String name;

  @Email
  @Column(unique = true)
  private String email;

  private String password;

  @JsonIgnore
  @ManyToMany(fetch = FetchType.EAGER)
  private List<Profile> profile = new ArrayList<>();

  public void generateId() {
    this.id = UUID.randomUUID();
  }

  public void addProfile(){
    this.profile.add(new Profile("USER"));
  }


  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.profile;
  }

  @Override
  public String getUsername() {
    return this.email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
