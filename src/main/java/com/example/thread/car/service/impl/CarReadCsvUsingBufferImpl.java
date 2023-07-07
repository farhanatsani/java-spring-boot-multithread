package com.example.thread.car.service.impl;

import com.example.thread.car.service.CarReadCsvService;
import com.example.thread.car.util.CarParserUtils;
import com.example.thread.car.vo.CarVO;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service(value = "carReadCsvUsingBufferService")
public class CarReadCsvUsingBufferImpl implements CarReadCsvService {
    @SneakyThrows
    @Override
    public List<CarVO> readCsvCar(String fileName) {
        long start = System.currentTimeMillis();

        FileInputStream fileInputStream = new FileInputStream(fileName);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

        List<CarVO> carList = new ArrayList<>();
        String line;
        while((line = bufferedReader.readLine()) != null) {
            String[] tempLine = line.split(",");
            CarVO carVO = CarParserUtils.parseToCarDTO(tempLine);
            carList.add(carVO);
        }
        bufferedReader.close();

        float elapsed = (System.currentTimeMillis() - start) / 1000F;
        System.out.println("read csv with buffer, time : " + elapsed + " seconds");
        return carList;
    }

    @SneakyThrows
    @Override
    public List<CarVO> readCsvCar(MultipartFile file) {
        long start = System.currentTimeMillis();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream()));

        List<CarVO> carList = new ArrayList<>();
        String line;
        while((line = bufferedReader.readLine()) != null) {
            String[] tempLine = line.split(",");
            CarVO carVO = CarParserUtils.parseToCarDTO(tempLine);
            carList.add(carVO);
        }
        bufferedReader.close();

        float elapsed = (System.currentTimeMillis() - start) / 1000F;
        System.out.println("read csv with buffer, time : " + elapsed + " seconds");
        return carList;
    }

}
