package com.tabajaracompany.imccalculator.mappers;

import com.tabajaracompany.imccalculator.dtos.UserRequest;
import com.tabajaracompany.imccalculator.dtos.UserResponse;
import com.tabajaracompany.imccalculator.models.UserIMC;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

  @Autowired private ModelMapper modelMapper;

  public UserIMC convertToModel(UserRequest userRequest) {
    return modelMapper.map(userRequest, UserIMC.class);
  }

  public UserResponse convertToResponse(UserIMC userIMC) {
    return modelMapper.map(userIMC, UserResponse.class);
  }
}
