package com.example.demo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;


import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder

public class CurrencyDto {
    private Long id;
    @NotEmpty(message = "Enter currency name")
    private String title;
    @NotEmpty(message ="enter the url")
    private String url;
    @NotEmpty(message ="enter the country")
    private String country;
    @NotEmpty(message ="enter the currency symbol")
    private String symbol;
    private LocalDateTime updatedOn;


}
