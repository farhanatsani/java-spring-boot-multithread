package com.example.thread.car.vo;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@Builder
@ToString
public class CarVO {
    private String carBrand;
    private String carType;
    private String year;
    private BigDecimal sellingPrice;
    private String kmDriven;
    private String fuelType;
    private String transmission;
    private String vin;
    private String description;
}
