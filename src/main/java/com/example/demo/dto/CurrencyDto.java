package com.example.demo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import lombok.Builder;
import lombok.Data;


import java.time.LocalDate;
@Data
@Builder

public class CurrencyDto {
    private Long id;
    @NotEmpty(message = "Enter currency name")
    private String title;
    @Min(value = 0, message = "Currency must be bigger than zero")
    private double rate;
    @NotEmpty(message ="enter the url")
    private String url;
    private LocalDate currencyDate;
}
