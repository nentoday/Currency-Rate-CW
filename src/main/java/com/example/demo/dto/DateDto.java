package com.example.demo.dto;


import com.example.demo.model.Currency;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@Builder
public class DateDto {
    private Long dateId;
    @Min(value = 0, message = "The rate must be a positive number")
    private float rate;
    @NotNull(message = "Rate date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "Date must be today or in the past")
    private LocalDate rateDate;
    private LocalDateTime createdOn;
    private Currency currency;
}
