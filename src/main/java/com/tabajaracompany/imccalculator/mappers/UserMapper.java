package com.tabajaracompany.imccalculator.mappers;

import com.tabajaracompany.imccalculator.dtos.UserRequest;
import com.tabajaracompany.imccalculator.dtos.UserResponse;
import com.tabajaracompany.imccalculator.models.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

  @Autowired private ModelMapper modelMapper;

  public User convertToModel(UserRequest userRequest) {
    return modelMapper.map(userRequest, User.class);
  }

  public UserResponse convertToResponse(User user) {
    return modelMapper.map(user, UserResponse.class);
  }
}
