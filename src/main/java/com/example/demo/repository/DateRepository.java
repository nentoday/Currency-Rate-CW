package com.example.demo.repository;

import com.example.demo.dto.DateDto;
import com.example.demo.model.Date;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DateRepository extends JpaRepository <Date, Long> {
    Optional<Date> findByRateDateAndCurrency_Id(LocalDate dateRate, Long currencyId);
    List<Date> findAllByCurrency_Id(Long currencyId);
    List<Date> findAllByCurrency_Title(String title);
//    @Query("SELECT d FROM Date d WHERE d.currency.title LIKE CONCAT('%', :query, '%') AND d.rateDate >= :startDate AND d.rateDate <= :endDate")
//    List<DateDto> searchCurrencyWithinDateRange(String query, LocalDate startDate, LocalDate endDate);


}
