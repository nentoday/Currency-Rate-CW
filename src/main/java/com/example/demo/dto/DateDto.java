package com.example.demo.dto;

import com.example.demo.model.Currency;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DateDto {
    private Long id;
    private float rate;
    private LocalDate today;
    private LocalDate startDate;
    private LocalDate localDate;
    private LocalDateTime createdOn;
}
