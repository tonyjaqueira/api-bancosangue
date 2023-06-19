package com.wk.banco.sangue.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DataToSave(

        String nome,
        String cpf,
        String rg,
        @JsonProperty("data_nasc")
        String dataNasc,
        String sexo,
        String mae,
        String pai,
        String email,
        String cep,
        String endereco,
        Integer numero,
        String bairro,
        String cidade,
        String estado,
        @JsonProperty("telefone_fixo")
        String telefoneFixo,
        String celular,
        @JsonProperty("tipo_sanguineo")
        String tipoSanguineo,
        Double altura,
        Double peso

) {}
