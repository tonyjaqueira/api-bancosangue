package com.wk.banco.sangue.mapper;

import com.wk.banco.sangue.domain.model.DataToSave;
import com.wk.banco.sangue.domain.entity.Cidade;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CidadeMapper {

    private final ModelMapper modelMapper;

    public Cidade dataToSave(DataToSave data) {
        var cidadeToSave = modelMapper.map(data, Cidade.class);
        cidadeToSave.setNomeCidade(data.cidade());
        return cidadeToSave;
    }

}
