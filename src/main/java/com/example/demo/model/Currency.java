package com.example.demo.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="Currency")
public class Currency {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY )
   private Long id;
   private String title;
   private String url;
   private String country;
   private String symbol;
   @UpdateTimestamp
   private LocalDateTime updatedOn;

   @OneToMany(mappedBy = "currency", cascade = CascadeType.REMOVE)
   private List<Date> date = new ArrayList<>();

   @OneToMany(mappedBy = "currency", cascade = CascadeType.REMOVE)
   private List<ExchangeRate> rates=new ArrayList<>();
}
