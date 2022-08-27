package com.ptithcm.clothing_store.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class LocalDateUntils {
    public static LocalDate convertStringToLocalDate(String str){
        String convert ="";
        if(!Objects.isNull(str)){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            convert= LocalDate.parse(str,formatter1).format(formatter);
        }
        return LocalDate.parse(convert);
    }
}
