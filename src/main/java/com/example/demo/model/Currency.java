package com.example.demo.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="Currency Table")
public class Currency {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY )
   private Long Id;
   private String title;
   private String url;
   @CreationTimestamp
   private LocalDate createdOn;
   @UpdateTimestamp
   private LocalDate updated;
}
