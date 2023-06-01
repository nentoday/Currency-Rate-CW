package com.example.demo.repository;

import com.example.demo.dto.DateDto;
import com.example.demo.model.Date;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;


public interface DateRepository extends JpaRepository <Date, Long> {
    List<Date> findByRateDateAndCurrency_Id(LocalDate dateRate, Long currencyId);
    List<Date> findAllByCurrency_Id(Long currencyId);
}
