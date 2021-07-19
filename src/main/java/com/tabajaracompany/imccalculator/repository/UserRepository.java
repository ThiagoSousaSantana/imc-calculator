package com.tabajaracompany.imccalculator.repository;

import com.tabajaracompany.imccalculator.models.UserIMC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserIMC, UUID> {

     Optional<UserIMC> findByEmail(String email);
}
