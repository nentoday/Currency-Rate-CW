package com.example.demo.repository;

import com.example.demo.model.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRepository extends JpaRepository <ExchangeRate, Long> {
}
