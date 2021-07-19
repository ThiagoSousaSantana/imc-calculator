package com.tabajaracompany.imccalculator.services;

import com.tabajaracompany.imccalculator.models.UserIMC;
import com.tabajaracompany.imccalculator.repository.UserRepository;
import com.tabajaracompany.imccalculator.security.SecuritySetting;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

  @Autowired private SecuritySetting passwordEncoder;

  @Autowired private UserRepository userRepository;

  public UserIMC insertUser(UserIMC userIMC){
    userIMC.generateId();
    userIMC.setPassword(passwordEncoder.bCryptPasswordEncoder().encode(userIMC.getPassword()));
    return userRepository.save(userIMC);
  }

  public UserIMC findByUser(UUID id){
    return userRepository.findById(id)
            .orElseThrow(() -> new ObjectNotFoundException(id, "User"));
  }


}
