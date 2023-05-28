package com.example.demo.services.impl;

import com.example.demo.repository.CurrencyRepository;
import com.example.demo.repository.DateRepository;
import com.example.demo.repository.ExchangeRepository;
import com.example.demo.services.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExchangeServiceimpl implements ExchangeService {
    private CurrencyRepository currencyRepository;
    private DateRepository dateRepository;
    private ExchangeRepository exchangeRepository;
    @Autowired
    public ExchangeServiceimpl(CurrencyRepository currencyRepository, DateRepository dateRepository, ExchangeRepository exchangeRepository) {
        this.currencyRepository = currencyRepository;
        this.dateRepository = dateRepository;
        this.exchangeRepository = exchangeRepository;
    }
}
