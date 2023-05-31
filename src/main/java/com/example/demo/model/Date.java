package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name= "date table")
public class Date {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dateId;
    private float rate;
    private LocalDate rateDate;

    @CreationTimestamp
    private LocalDateTime createdOn;
    @ManyToOne
    @JoinColumn(name = "currency_id",nullable = false)
    private Currency currency;
//    private LocalDate from = LocalDate.parse(localDate);
}
