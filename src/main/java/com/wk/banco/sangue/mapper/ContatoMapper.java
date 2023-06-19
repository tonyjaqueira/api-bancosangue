package com.wk.banco.sangue.mapper;

import com.wk.banco.sangue.domain.model.DataToSave;
import com.wk.banco.sangue.domain.entity.Contato;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ContatoMapper {

    private final ModelMapper modelMapper;

    public Contato dataToSave(DataToSave data) {
        return modelMapper.map(data, Contato.class);
    }

}
