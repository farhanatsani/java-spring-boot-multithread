package com.example.thread.car.service;

import com.example.thread.car.vo.CarVO;

import java.util.List;

public interface CarReadCsvService {

    List<CarVO> readCsvCar(String fileName);

    List<CarVO> readCsvCarUsingBuffer(String fileName);

}
