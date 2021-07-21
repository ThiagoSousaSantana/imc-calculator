package com.tabajaracompany.imccalculator.controllers;

import com.tabajaracompany.imccalculator.dtos.ImcDataResponse;
import com.tabajaracompany.imccalculator.services.ImcDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/imc")
public class ImcDataController {

  @Autowired private ImcDataService imcDataService;

  @GetMapping("/{idUser}")
  public ResponseEntity<Page<ImcDataResponse>> findAllImc(
      @PathVariable UUID idUser, Pageable pageable) {
    var imc = imcDataService.findAllImc(idUser, pageable);
    return ResponseEntity.ok(imc);
  }
}
