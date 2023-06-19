package com.wk.banco.sangue.utils;

import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
public class FormatDate {

    public static LocalDate formatStringToDate(String stringDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(stringDate, formatter);
    }

}
