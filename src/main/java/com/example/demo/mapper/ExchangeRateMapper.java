package com.example.demo.mapper;

import com.example.demo.dto.ExchangeRateDto;
import com.example.demo.model.ExchangeRate;

public class ExchangeRateMapper {
    public static ExchangeRate mapToDate(ExchangeRateDto dateDto){
        return ExchangeRate.builder()
                .dateId(dateDto.getDateId())
                .rate(dateDto.getRate())
                .rateDate(dateDto.getRateDate())
                .createdOn(dateDto.getCreatedOn())
                .currency(dateDto.getCurrency())
                .build();
    }
    public static ExchangeRateDto mapToDateDto(ExchangeRate date){
        return ExchangeRateDto.builder()
                .dateId(date.getDateId())
                .rate(date.getRate())
                .rateDate(date.getRateDate())
                .createdOn(date.getCreatedOn())
                .currency(date.getCurrency())
                .build();
    }
}
