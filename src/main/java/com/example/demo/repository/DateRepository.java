package com.example.demo.repository;

import com.example.demo.model.Date;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DateRepository extends JpaRepository<Date, Long> {
    Optional<Date> findByRateDateAndCurrency_Id(LocalDate dateRate, Long currencyId);
}
