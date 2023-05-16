package com.example.demo.services.impl;

import com.example.demo.dto.CurrencyDto;
import com.example.demo.repository.CurrencyRepository;
import com.example.demo.services.CurrencyService;
import com.example.demo.model.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;
@Service
public class Currencyserviceimpl implements CurrencyService {
    private CurrencyRepository currencyRepository;
    @Autowired
    public Currencyserviceimpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public List<CurrencyDto> findAllCurrencies() {
        List <Currency> currencies = currencyRepository.findAll();
        return currencies.stream().map((currency) -> mapToCurrencyDto(currency)).collect(Collectors.toList());
    }
    private CurrencyDto mapToCurrencyDto(Currency currency){
        CurrencyDto currencyDto = CurrencyDto.builder()
                .id(currency.getId())
                .title(currency.getTitle())
                .url(currency.getUrl())
                .createdOn(currency.getCreatedOn())
                .updated(currency.getUpdated())
                .build();
        return currencyDto;
    }

}
//package com.example.demo.services.impl;
//
//import com.example.demo.dto.CurrencyDto;
//import com.example.demo.repository.CurrencyRepository;
//import com.example.demo.services.CurrencyService;
//import com.example.demo.model.Currency;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class CurrencyServiceImpl implements CurrencyService {
//    private CurrencyRepository currencyRepository;
//
//    @Autowired
//    public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
//        this.currencyRepository = currencyRepository;
//    }
//
//    @Override
//    public List<CurrencyDto> findAllCurrencies() {
//        return currencyRepository.findAll().stream()
//                .map(this::mapToCurrencyDto)
//                .collect(Collectors.toList());
//    }
//
//    private CurrencyDto mapToCurrencyDto(Currency currency){
//        return new CurrencyDto(
//                currency.getId(),
//                currency.getTitle(),
//                currency.getCreatedOn(),
//                currency.getUpdated()
//        );
//    }
//}
