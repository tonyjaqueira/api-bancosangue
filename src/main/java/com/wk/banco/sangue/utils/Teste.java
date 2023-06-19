package com.wk.banco.sangue.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wk.banco.sangue.domain.entity.Pessoa;
import com.wk.banco.sangue.domain.model.DataToSave;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
public class Teste {

    public static void main(String[] args) {
        try {
            ClassLoader classLoader = Teste.class.getClassLoader();

            InputStream inputStream = classLoader.getResourceAsStream("data_1.json");

            ObjectMapper objectMapper = new ObjectMapper();
            var listData = objectMapper.readValue(inputStream, Object[].class);
            Optional.ofNullable(listData).ifPresent(list -> Arrays.stream(list).forEach(data -> {
                var pessoaData = objectMapper.convertValue(data, DataToSave.class);
                log.info(pessoaData.toString());
            }));
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*List<Pessoa> listPessoas = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        var url = "https://communication-assets.gupy.io/production/companies/52441/emails/1686945524215/e8330670-6f23-11ed-91a8-05f5cf6759fb/data_1.json";
        var listData = restTemplate.getForObject(url, Object[].class);
        Optional.ofNullable(listData).ifPresent(list -> Arrays.stream(list).forEach(data -> {
            var pessoaData = objectMapper.convertValue(data, DataToSave.class);
            log.info(pessoaData.toString());
        }));*/

    }

}
