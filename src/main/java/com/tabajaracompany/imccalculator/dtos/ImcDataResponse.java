package com.tabajaracompany.imccalculator.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class ImcDataResponse {

  private UUID id;
  private BigDecimal imc;
  private Integer grade;
  private String classification;

  @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
  private LocalDateTime createdDate;

  private UUID userIMC;
}
