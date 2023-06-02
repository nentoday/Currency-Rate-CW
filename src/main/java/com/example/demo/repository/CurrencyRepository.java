package com.example.demo.repository;

import com.example.demo.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
   Optional<Currency> findByTitle(String url);
   @Query("SELECT c FROM Currency c WHERE LOWER(c.title) LIKE CONCAT('%', LOWER(:query), '%')")
   List<Currency> searchCurrency(String query);

}
