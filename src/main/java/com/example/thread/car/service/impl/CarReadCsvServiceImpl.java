package com.example.thread.car.service.impl;

import com.example.thread.car.service.CarReadCsvService;
import com.example.thread.car.vo.CarVO;
import lombok.SneakyThrows;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service(value = "carReadCsvService")
public class CarReadCsvServiceImpl implements CarReadCsvService {

    @SneakyThrows
    @Override
    public List<CarVO> readCsvCar(String fileName) {
        long start = System.currentTimeMillis();

        Reader in = new FileReader(fileName);

        List<CarVO> carList = new ArrayList<>();
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
        for (CSVRecord record: records) {
            CarVO carVO = parseToCarDTO(record);
            carList.add(carVO);
        }

        float elapsed = (System.currentTimeMillis() - start) / 1000F;
        System.out.println("read csv, time : " + elapsed + " seconds");
        return carList;
    }

    @SneakyThrows
    @Override
    public List<CarVO> readCsvCarUsingBuffer(String fileName) {
        long start = System.currentTimeMillis();

        FileInputStream fileInputStream = new FileInputStream(fileName);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

        List<CarVO> carList = new ArrayList<>();
        String line;
        while((line = bufferedReader.readLine()) != null) {
            String[] tempLine = line.split(",");
            CarVO carVO = parseToCarDTO(tempLine);
            carList.add(carVO);
        }
        bufferedReader.close();

        float elapsed = (System.currentTimeMillis() - start) / 1000F;
        System.out.println("read csv with buffer, time : " + elapsed + " seconds");
        return carList;
    }

    private CarVO parseToCarDTO(String[] lines) {
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

    private CarVO parseToCarDTO(CSVRecord record) {
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
