package com.tabajaracompany.imccalculator.services;

import com.tabajaracompany.imccalculator.dtos.ImcDataResponse;
import com.tabajaracompany.imccalculator.repository.ImcDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ImcDataService {

  @Autowired private ImcDataRepository imcDataRepository;

  @Autowired private UserService userService;

  public Page<ImcDataResponse> findAllImc(UUID idUser, Pageable pageable) {
    userService.findByUser(idUser);
    var imc = imcDataRepository.findAllByUserIMCId(idUser, pageable);
    return imc.map(
        obj ->
            ImcDataResponse.builder()
                .id(obj.getId())
                .imc(obj.getImc())
                .grade(obj.getGrade())
                .classification(obj.getClassification())
                .createdDate(obj.getCreatedDate())
                .userIMC(obj.getUserIMC().getId())
                .build());
  }
}
