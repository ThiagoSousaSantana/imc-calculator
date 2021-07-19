package com.tabajaracompany.imccalculator.controllers;

import com.tabajaracompany.imccalculator.dtos.UserRequest;
import com.tabajaracompany.imccalculator.dtos.UserResponse;
import com.tabajaracompany.imccalculator.mappers.UserMapper;
import com.tabajaracompany.imccalculator.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired private UserService userService;

  @Autowired private UserMapper userMapper;

  @PostMapping
  public ResponseEntity<UserResponse> insertUser(@RequestBody UserRequest userRequest) {
    var user = userService.insertUser(userMapper.convertToModel(userRequest));
    var response = userMapper.convertToResponse(user);

    var uri =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{Id}")
            .buildAndExpand(response.getId())
            .toUri();

    return ResponseEntity.created(uri).body(response);
  }

  @GetMapping("/{userId}")
  public ResponseEntity<UserResponse> findbyIdUser(@PathVariable UUID userId){
    var user = userService.findByUser(userId);
    return ResponseEntity.ok(userMapper.convertToResponse(user));
  }
}
