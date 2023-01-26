package com.example.thread.car.util;

import com.example.thread.car.vo.CarVO;
import org.apache.commons.csv.CSVRecord;

import java.math.BigDecimal;

public class CarParserUtils {

    public static CarVO parseToCarDTO(String[] lines) {
        String brand = lines[0];
        if(brand.equalsIgnoreCase("Skoda")) {
            brand = "Škoda";
        } else if(brand.equalsIgnoreCase("Chevy")) {
            brand = "Chevrolet";
        } else if(brand.equalsIgnoreCase("Xpeng")) {
            brand = "XPeng";
        }

        String type = lines[1];
        if(type.equalsIgnoreCase("Model")) {
            type = "Model Y";
        } else if(type.equalsIgnoreCase("G3i")) {
            type = "G3";
        } else if(type.equalsIgnoreCase("ES7")) {
            type = "ET7";
        } else if(type.equalsIgnoreCase("Camero")) {
            type = "Camaro";
        } else if(type.equalsIgnoreCase("Riveria")) {
            type = "Riviera";
        } else if(type.equalsIgnoreCase("IONIQ")) {
            type = "Ioniq";
        } else if(type.equalsIgnoreCase("Pathfiner")) {
            type = "Pathfinder";
        }

        CarVO carVO = CarVO.builder()
                .carBrand(brand)
                .carType(type)
                .year(lines[2])
                .sellingPrice(new BigDecimal(lines[3]))
                .kmDriven(lines[4])
                .fuelType(lines[5])
                .transmission(lines[6])
                .vin(lines[7])
                .build();
        return carVO;
    }

    public static CarVO parseToCarDTO(CSVRecord record) {
        String brand = record.get(0);
        if(brand.equalsIgnoreCase("Skoda")) {
            brand = "Škoda";
        } else if(brand.equalsIgnoreCase("Chevy")) {
            brand = "Chevrolet";
        } else if(brand.equalsIgnoreCase("Xpeng")) {
            brand = "XPeng";
        }

        String type = record.get(1);
        if(type.equalsIgnoreCase("Model")) {
            type = "Model Y";
        } else if(type.equalsIgnoreCase("G3i")) {
            type = "G3";
        } else if(type.equalsIgnoreCase("ES7")) {
            type = "ET7";
        } else if(type.equalsIgnoreCase("Camero")) {
            type = "Camaro";
        } else if(type.equalsIgnoreCase("Riveria")) {
            type = "Riviera";
        } else if(type.equalsIgnoreCase("IONIQ")) {
            type = "Ioniq";
        } else if(type.equalsIgnoreCase("Pathfiner")) {
            type = "Pathfinder";
        }

        CarVO carVO = CarVO.builder()
                .carBrand(brand)
                .carType(type)
                .year(record.get(2))
                .sellingPrice(new BigDecimal(record.get(3)))
                .kmDriven(record.get(4))
                .fuelType(record.get(5))
                .transmission(record.get(6))
                .vin(record.get(7))
                .build();
        return carVO;
    }

}
