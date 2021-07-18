package com.tabajaracompany.imccalculator.dtos;

import com.tabajaracompany.imccalculator.models.IMC;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CalculationResponse {
    private BigDecimal resultado;
    private IMC imc;
}
