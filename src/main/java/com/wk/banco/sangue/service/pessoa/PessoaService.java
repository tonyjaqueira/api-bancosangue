package com.wk.banco.sangue.service.pessoa;

import com.wk.banco.sangue.domain.entity.Pessoa;

import java.util.List;

public interface PessoaService {
    void loadInitialData();

    List<Pessoa> listAllPessoas();

}
