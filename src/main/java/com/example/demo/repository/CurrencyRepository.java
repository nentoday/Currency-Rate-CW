package com.example.demo.repository;

import com.example.demo.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
   @Query("SELECT c FROM Currency c WHERE LOWER(c.title) LIKE CONCAT('%', LOWER(:query), '%')")
   List<Currency> searchCurrency(String query);

}
