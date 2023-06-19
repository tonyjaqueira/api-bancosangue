package com.wk.banco.sangue.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wk.banco.sangue.domain.enums.GeneroType;
import com.wk.banco.sangue.domain.enums.TipoSanguineoType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalDate;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pessoa_id")
    private Long pessoaId;

    private String nome;

    private String cpf;

    private String rg;

    @Column(name = "data_nasc")
    @JsonProperty("data_nasc")
    private LocalDate dataNascimento;

    @Enumerated(EnumType.STRING)
    private GeneroType sexo;

    private String mae;

    private String pai;

    private Double peso;

    private Double altura;

    @Column(name = "tipo_sanguineo")
    @Enumerated(EnumType.STRING)
    @JsonProperty("tipo_sanguineo")
    private TipoSanguineoType tipoSanguineo;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "contato_id", referencedColumnName = "contato_id")
    private Contato contato;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "endereco_id")
    private Endereco endereco;

}
