package com.tabajaracompany.imccalculator.services;

import com.tabajaracompany.imccalculator.dtos.UserRequest;
import com.tabajaracompany.imccalculator.exceptions.AuthorizationException;
import com.tabajaracompany.imccalculator.models.UserIMC;
import com.tabajaracompany.imccalculator.repository.UserRepository;
import com.tabajaracompany.imccalculator.security.SecuritySetting;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

  @Autowired private SecuritySetting passwordEncoder;

  @Autowired private UserRepository userRepository;

  public UserIMC insertUser(UserIMC userIMC) {
    userIMC.generateId();
    userIMC.setPassword(passwordEncoder.bCryptPasswordEncoder().encode(userIMC.getPassword()));
    return userRepository.save(userIMC);
  }

  public UserIMC findByUser(UUID id) {
    var user = authenticated();
    if (user == null || !id.equals(user.getId())) {
      throw new AuthorizationException("Access denied");
    }
    return userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, "User"));
  }

  public UserIMC updateUser(UUID id, UserRequest userRequest) {
    var user = findByUser(id);
    return userRepository.save(new UserIMC(user, userRequest));
  }

  public void deleteUser(UUID id) {
    var user = findByUser(id);
    userRepository.delete(user);
  }

  private static UserIMC authenticated() {
    try {
      return (UserIMC) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    } catch (Exception e) {
      return null;
    }
  }
}
