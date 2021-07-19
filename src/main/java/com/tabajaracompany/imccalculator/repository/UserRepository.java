package com.tabajaracompany.imccalculator.repository;

import com.tabajaracompany.imccalculator.dtos.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

     Optional<User> findByEmail(String email);
}
