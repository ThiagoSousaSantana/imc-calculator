package com.tabajaracompany.imccalculator.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ImcData {

  @Id private UUID id;
  private BigDecimal imc;
  private Integer grade;
  private String classification;

  @CreatedDate private LocalDateTime createdDate;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private UserIMC userIMC;

  public ImcData(BigDecimal result, IMC imc, UserIMC user) {
    generateId();
    this.imc = result;
    this.grade = imc.getObesidade();
    this.classification = imc.name();
    this.userIMC = user;
  }

  public void generateId() {
    this.id = UUID.randomUUID();
  }
}
