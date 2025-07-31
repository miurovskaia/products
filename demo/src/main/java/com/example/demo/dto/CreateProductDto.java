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
    private Instant timedate;


    public CreateProductDto(String name,String conditions, Integer tariffId, Instant timedate) {
        this.name = name;
        this.tariffId = tariffId;
        this.timedate = timedate;
    }

    public String getName() {
        return name;
    }


    public Integer getTariffId() {
        return tariffId;
    }

    public Instant getTimedate() {
        return timedate;
    }
}