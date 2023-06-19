package com.wk.banco.sangue.configuration;


import com.wk.banco.sangue.service.pessoa.PessoaService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/*
    Classe criada para ser gerenciada pelo Sping e anotada para que depois da aplicação a mesma dara uma carga inicial dos possiveis doadores de sangue a parit de um json enviado.
* */

@Component
public class InitialDataConfiguration {

    private final PessoaService pessoaService;

    public InitialDataConfiguration(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostConstruct
    public void initialData() {
        if(pessoaService.listAllPessoas().isEmpty()){
            pessoaService.loadInitialData();
        }
    }


}
