package com.tabajaracompany.imccalculator.models;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public enum IMC {
    MAGREZA(0, BigDecimal.ZERO, BigDecimal.valueOf(18.5)),
    NORMAL(0, BigDecimal.valueOf(18.5), BigDecimal.valueOf(24.9)),
    SOBREPESO(1, BigDecimal.valueOf(24.9), BigDecimal.valueOf(29.9)),
    OBESIDADE(2, BigDecimal.valueOf(29.9), BigDecimal.valueOf(39.9)),
    OBESIDADE_GRAVE(3, BigDecimal.valueOf(39.9), BigDecimal.valueOf(900000000));


    private final Integer obesidade;
    private final BigDecimal minimo;
    private final BigDecimal maximo;

    IMC(Integer obesidade, BigDecimal minimo, BigDecimal maximo) {
        this.obesidade = obesidade;
        this.minimo = minimo;
        this.maximo = maximo;
    }

    public static IMC toEnum(BigDecimal result) {
        for (IMC imc : IMC.values()) {
            if (result.compareTo(imc.minimo) > 0 && result.compareTo(imc.maximo) < 0) {
                return imc;
            }
        }
        return null;
    }
}
