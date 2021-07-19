package com.tabajaracompany.imccalculator.services;

import com.tabajaracompany.imccalculator.repository.UserRepository;
import com.tabajaracompany.imccalculator.security.SecuritySetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired private SecuritySetting passwordEncoder;

  @Autowired private UserRepository userRepository;
}
