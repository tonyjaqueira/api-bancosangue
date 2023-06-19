package com.wk.banco.sangue.domain.enums;


import com.wk.banco.sangue.exception.WkExceptionCustomer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum GeneroType {

    MASCULINO("Masculino"),
    FEMININO("Feminino");

    private final String description;

    public static GeneroType getByDescription(String description) {
        return Arrays.stream(GeneroType.values())
                .filter(type -> type.description.equals(description)).findFirst().orElseThrow(() -> new WkExceptionCustomer("Nenhum tipo de Sexo encontrado!"));
    }

    public static String getByType(String sexoType) {
        return Arrays.stream(GeneroType.values())
                .filter(type -> type.name().equals(sexoType)).findFirst().orElseThrow(() -> new WkExceptionCustomer("Nenhum tipo de Sexo encontrado!")).getDescription();
    }

}
