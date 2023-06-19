package com.wk.banco.sangue.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DecimalFormatDate {

    public static double arrendondarDecimalParaCima(double numero) {
        BigDecimal arredondado = BigDecimal.valueOf(numero).setScale(2, RoundingMode.HALF_UP);
        return arredondado.doubleValue();
    }

    public static int arrendondarDecimalParaCimaInteiro(double numero){
        return (int) Math.round(numero);
    }

}
