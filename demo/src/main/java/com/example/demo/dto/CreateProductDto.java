package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductDto {
    private String name;
    private Integer tariffId;
    private Instant timeAndDate;


    public CreateProductDto(String name,String conditions, Integer tariffId, Instant timeAndDate) {
        this.name = name;
        this.tariffId = tariffId;
        this.timeAndDate = timeAndDate;
    }

    public String getName() {
        return name;
    }


    public Integer getTariffId() {
        return tariffId;
    }

    public Instant getTimeAndDate() {
        return timeAndDate;
    }
}