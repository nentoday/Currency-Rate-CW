package com.example.demo.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;


import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder

public class CurrencyDto {
    private Long id;
    @NotEmpty(message = "Enter currency name")
    private String title;
    @NotEmpty(message ="enter the url")
    private String url;
    private String details;
    @NotEmpty(message ="enter the country")
    private String country;
    @NotEmpty(message ="enter the currency symbol")
    private String symbol;
    private LocalDateTime updatedOn;
    private List<ExchangeRateDto> dates;

}
