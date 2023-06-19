package com.wk.banco.sangue.mapper;


import com.wk.banco.sangue.domain.entity.Pessoa;
import com.wk.banco.sangue.domain.model.DataToSave;
import com.wk.banco.sangue.domain.enums.GeneroType;
import com.wk.banco.sangue.domain.enums.TipoSanguineoType;
import com.wk.banco.sangue.utils.FormatDate;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PessoaMapper {

    private final ModelMapper modelMapper;

    private final EnderecoMapper enderecoMapper;

    private final ContatoMapper contatoMapper;

    public Pessoa dataToSave(DataToSave data) {
        var pessoaToSave = modelMapper.map(data, Pessoa.class);
        pessoaToSave.setDataNascimento(FormatDate.formatStringToDate(data.dataNasc()));
        pessoaToSave.setEndereco(enderecoMapper.dataToSave(data));
        pessoaToSave.setContato(contatoMapper.dataToSave(data));
        pessoaToSave.setSexo(GeneroType.getByDescription(data.sexo()));
        pessoaToSave.setTipoSanguineo(TipoSanguineoType.getByDescription(data.tipoSanguineo()));
        return pessoaToSave;
    }

    public List<Pessoa> mapperEntityListToSave(List<DataToSave> listData) {
        return listData.stream().map(this::dataToSave).toList();
    }

}
