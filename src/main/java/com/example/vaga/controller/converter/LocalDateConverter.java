package com.example.vaga.controller.converter;


import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateConverter implements Converter<String, LocalDate> {

    private static final DateTimeFormatter yearMonthFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public LocalDate convert(@NonNull String stringValue) {
        return LocalDate.parse(stringValue, yearMonthFormat);
    }

}
