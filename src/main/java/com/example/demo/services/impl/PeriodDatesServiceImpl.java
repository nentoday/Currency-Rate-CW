package com.example.demo.services.impl;

import com.example.demo.model.PeriodDates;
import com.example.demo.repository.PeriodDatesRepository;
import com.example.demo.services.PeriodDatesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class PeriodDatesServiceImpl implements PeriodDatesService {
    private PeriodDatesRepository periodDatesRepository;
    @Override
    public PeriodDates savePeriodDate(PeriodDates periodDates) {
        return periodDatesRepository.save(periodDates);
    }
}
