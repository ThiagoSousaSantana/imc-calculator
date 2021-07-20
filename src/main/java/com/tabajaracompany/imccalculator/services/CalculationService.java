package com.tabajaracompany.imccalculator.services;

import com.tabajaracompany.imccalculator.dtos.CalculationRequest;
import com.tabajaracompany.imccalculator.dtos.CalculationResponse;
import com.tabajaracompany.imccalculator.dtos.ImcDataResponse;
import com.tabajaracompany.imccalculator.models.IMC;
import com.tabajaracompany.imccalculator.models.ImcData;
import com.tabajaracompany.imccalculator.repository.ImcDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

@Service
public class CalculationService {

  @Autowired private UserService userService;

  @Autowired private ImcDataRepository imcDataRepository;

  public CalculationResponse calculate(CalculationRequest request) {
    var result = calculate(request.getAltura(), request.getPeso());
    return CalculationResponse.builder().resultado(result).imc(IMC.toEnum(result)).build();
  }

  public ImcDataResponse calculateByUser(CalculationRequest request, UUID idUser) {
    var result = calculate(request.getAltura(), request.getPeso());
    var user = userService.findByUser(idUser);
    var imcData = imcDataRepository.save(new ImcData(result, IMC.toEnum(result), user));
    return ImcDataResponse.builder()
        .id(imcData.getId())
        .grade(imcData.getGrade())
        .imc(result)
        .classification(imcData.getClassification())
        .createdDate(imcData.getCreatedDate())
        .userIMC(imcData.getUserIMC().getId())
        .build();
  }

  private BigDecimal calculate(BigDecimal altura, BigDecimal peso) {
    return peso.divide(altura.pow(2), RoundingMode.HALF_EVEN);
  }
}
