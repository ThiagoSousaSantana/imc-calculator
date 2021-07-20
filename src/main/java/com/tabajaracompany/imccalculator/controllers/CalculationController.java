package com.tabajaracompany.imccalculator.controllers;

import com.tabajaracompany.imccalculator.dtos.CalculationRequest;
import com.tabajaracompany.imccalculator.dtos.CalculationResponse;
import com.tabajaracompany.imccalculator.dtos.ImcDataResponse;
import com.tabajaracompany.imccalculator.services.CalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/calculation")
public class CalculationController {

  @Autowired private CalculationService service;

  @PostMapping
  public ResponseEntity<CalculationResponse> calculate(@RequestBody CalculationRequest request) {
    var result = service.calculate(request);
    return ResponseEntity.ok(result);
  }

  @PostMapping("/{idUser}")
  public ResponseEntity<ImcDataResponse> calculateByUser(
      @RequestBody CalculationRequest request, @PathVariable UUID idUser) {
    var result = service.calculateByUser(request, idUser);
    return ResponseEntity.ok(result);
  }
}
