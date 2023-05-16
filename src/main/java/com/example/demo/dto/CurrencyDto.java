package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;


import java.time.LocalDate;
@Data
@Builder

public class CurrencyDto {
    private Long id;
    private String title;
    private String url;
    private LocalDate createdOn;
    private LocalDate updated;
}
