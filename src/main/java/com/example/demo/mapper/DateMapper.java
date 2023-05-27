package com.example.demo.mapper;

import com.example.demo.dto.DateDto;
import com.example.demo.model.Date;

public class DateMapper {
    public static Date mapToDate(DateDto dateDto){
        return Date.builder()
                .dateId(dateDto.getDateId())
                .rate(dateDto.getRate())
                .rateDate(dateDto.getRateDate())
                .createdOn(dateDto.getCreatedOn())
                .build();
    }
}
