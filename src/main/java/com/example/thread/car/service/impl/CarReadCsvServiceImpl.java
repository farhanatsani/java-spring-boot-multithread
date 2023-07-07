package com.example.thread.car.service.impl;

import com.example.thread.car.service.CarReadCsvService;
import com.example.thread.car.util.CarParserUtils;
import com.example.thread.car.vo.CarVO;
import lombok.SneakyThrows;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
            CarVO carVO = CarParserUtils.parseToCarDTO(record);
            carList.add(carVO);
        }

        float elapsed = (System.currentTimeMillis() - start) / 1000F;
        System.out.println("read csv, time : " + elapsed + " seconds");
        return carList;
    }

    @Override
    public List<CarVO> readCsvCar(MultipartFile file) {
        // unsupported
        return null;
    }

}
