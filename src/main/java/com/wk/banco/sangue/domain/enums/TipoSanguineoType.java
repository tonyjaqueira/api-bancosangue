package com.wk.banco.sangue.domain.enums;

import com.wk.banco.sangue.exception.WkExceptionCustomer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum TipoSanguineoType {
    A_POSITIVO("A+"),
    A_NEGATIVO("A-"),
    B_POSITIVO("B+"),
    B_NEGATIVO("B-"),
    AB_POSITIVO("AB+"),
    AB_NEGATIVO("AB-"),
    O_POSITIVO("O+"),
    O_NEGATIVO("O-");

    private final String description;

    public static TipoSanguineoType getByDescription(String description) {
        return Arrays.stream(TipoSanguineoType.values())
                .filter(type -> type.description.equals(description)).findFirst().orElseThrow(() -> new WkExceptionCustomer("Nenhum tipo sanguineo encontrado!"));
    }

    public static String getByType(String tipoSanguineoType) {
        return Arrays.stream(TipoSanguineoType.values())
                .filter(type -> type.name().equals(tipoSanguineoType)).findFirst().orElseThrow(() -> new WkExceptionCustomer("Nenhum tipo sanguineo encontrado!")).getDescription();
    }

}
