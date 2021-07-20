package com.tabajaracompany.imccalculator.dtos;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class CalculationRequest {

  @NotNull private BigDecimal altura;

  @NotNull private BigDecimal peso;
}
