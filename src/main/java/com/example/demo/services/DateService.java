package com.example.demo.services;

import com.example.demo.dto.DateDto;

public interface DateService {
    void createRate(Long currencyId, DateDto dateDto);
}
