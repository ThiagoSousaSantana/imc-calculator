package com.tabajaracompany.imccalculator.services;


import com.tabajaracompany.imccalculator.dtos.CalculationRequest;
import com.tabajaracompany.imccalculator.dtos.CalculationResponse;
import com.tabajaracompany.imccalculator.models.IMC;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class CalculationService {

    public CalculationResponse calculate(CalculationRequest request) {
        var result = calculate(request.getAltura(), request.getPeso());
        return CalculationResponse.builder()
                .resultado(result)
                .imc(IMC.toEnum(result))
                .build();
    }

    private BigDecimal calculate(BigDecimal altura, BigDecimal peso) {
        return peso.divide(altura.pow(2), RoundingMode.HALF_EVEN);
    }
}
