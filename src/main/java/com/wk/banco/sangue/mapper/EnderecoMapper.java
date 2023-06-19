package com.wk.banco.sangue.mapper;

import com.wk.banco.sangue.domain.model.DataToSave;
import com.wk.banco.sangue.domain.entity.Endereco;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EnderecoMapper {

    private final ModelMapper modelMapper;

    private final CidadeMapper cidadeMapper;

    public Endereco dataToSave(DataToSave data) {
        var enderecoToSave = modelMapper.map(data, Endereco.class);
        enderecoToSave.setEnderecoDescricao(data.endereco());
        enderecoToSave.setCidade(cidadeMapper.dataToSave(data));
        return enderecoToSave;
    }

}
