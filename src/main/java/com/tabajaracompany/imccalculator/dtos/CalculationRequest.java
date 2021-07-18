package com.tabajaracompany.imccalculator.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CalculationRequest {

    private BigDecimal altura;
    private BigDecimal peso;

}
