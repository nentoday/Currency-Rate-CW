package com.example.demo.mapper;

import com.example.demo.dto.CurrencyDto;
import com.example.demo.model.Currency;

import java.util.stream.Collectors;

import static com.example.demo.mapper.DateMapper.mapToDateDto;

public class CurrencyMapper {

    public static Currency mapToCurrency(CurrencyDto currency){
        Currency currencyDto= Currency.builder()
                .id(currency.getId())
                .title(currency.getTitle())
                .url(currency.getUrl())
                .country(currency.getCountry())
                .updatedOn(currency.getUpdatedOn())
                .symbol(currency.getSymbol())
                .details(currency.getDetails())
                .build();
        return currencyDto;

    }


    public static CurrencyDto mapToCurrencyDto(Currency currency){
        CurrencyDto currencyDto = CurrencyDto.builder()
                .id(currency.getId())
                .title(currency.getTitle())
                .url(currency.getUrl())
                .country(currency.getCountry())
                .updatedOn(currency.getUpdatedOn())
                .symbol(currency.getSymbol())
                .details(currency.getDetails())
                .dates(currency.getDate().stream().map((date) -> mapToDateDto(date)).collect(Collectors.toList()))
                .build();
        return currencyDto;
    }
}
