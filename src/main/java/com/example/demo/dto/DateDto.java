package com.example.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DateDto {
    private Long dateId;
    private float rate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate rateDate;
    private LocalDateTime createdOn;
}
