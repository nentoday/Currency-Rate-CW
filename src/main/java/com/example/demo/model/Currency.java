package com.example.demo.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="Currency Data")
public class Currency {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY )
   private Long id;
   private String title;
   private double rate;
   private String url;
   private LocalDate currencyDate;
}
