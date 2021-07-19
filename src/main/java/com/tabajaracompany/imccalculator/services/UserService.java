package com.tabajaracompany.imccalculator.services;

import com.tabajaracompany.imccalculator.models.User;
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

  public User insertUser(User user){
    user.generateId();
    user.setPassword(passwordEncoder.bCryptPasswordEncoder().encode(user.getPassword()));
    return userRepository.save(user);
  }

  public User findByUser(UUID id){
    return userRepository.findById(id)
            .orElseThrow(() -> new ObjectNotFoundException(id, "User"));
  }


}
