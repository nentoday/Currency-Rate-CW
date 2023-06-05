package com.example.demo.repository;

import com.example.demo.dto.ExchangeRateDto;
import com.example.demo.model.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;


public interface ExchangeRateRepository extends JpaRepository <ExchangeRate, Long> {
    List<ExchangeRate> findByRateDateAndCurrency_Id(LocalDate dateRate, Long currencyId);
    List<ExchangeRate> findAllByCurrency_Id(Long currencyId);
    ExchangeRateDto findByDateId(Long dateId);
  }
