package com.tabajaracompany.imccalculator.repository;

import com.tabajaracompany.imccalculator.models.ImcData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ImcDataRepository extends JpaRepository<ImcData, UUID> {}
