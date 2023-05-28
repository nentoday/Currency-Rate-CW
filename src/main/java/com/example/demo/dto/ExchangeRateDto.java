package com.example.demo.dto;

import com.example.demo.model.Currency;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data
@Builder
public class ExchangeRateDto {
    private Long id;
    private Currency currency;
    private double rate;
    private LocalDate startDay;
    private LocalDate endDay;
    private List<DateDto> dates;
    private List<CurrencyDto> currencies;
}

