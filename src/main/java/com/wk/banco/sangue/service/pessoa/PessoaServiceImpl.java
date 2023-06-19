package com.wk.banco.sangue.service.pessoa;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.wk.banco.sangue.domain.entity.Pessoa;
import com.wk.banco.sangue.domain.model.DataToSave;
import com.wk.banco.sangue.mapper.PessoaMapper;
import com.wk.banco.sangue.repository.PessoaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class PessoaServiceImpl implements PessoaService {

    private final PessoaRepository pessoaRepository;

    private final PessoaMapper pessoaMapper;

    @Override
    public void loadInitialData() {
        List<Pessoa> listPessoas = new ArrayList<>();
        try {
            ClassLoader classLoader = PessoaServiceImpl.class.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("data_1.json");
            ObjectMapper objectMapper = new ObjectMapper();
            var listData = objectMapper.readValue(inputStream, Object[].class);
            Optional.ofNullable(listData).ifPresent(list -> Arrays.stream(list).forEach(data -> {
                var pessoaData = objectMapper.convertValue(data, DataToSave.class);
                var pessoa = pessoaMapper.dataToSave(pessoaData);
                listPessoas.add(pessoa);
            }));
            savaAllPessoas(listPessoas);
        } catch (IOException e) {
            log.error("Erro ao importar carga inicial de dados: ".concat(e.getMessage()));
        }
    }

    @Override
    public List<Pessoa> listAllPessoas() {
        return pessoaRepository.findAll();
    }

    private void savaAllPessoas(List<Pessoa> listPessoas) {
        pessoaRepository.saveAll(listPessoas);
    }

}
