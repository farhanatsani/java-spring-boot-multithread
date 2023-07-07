package com.example.thread.car.service;

import com.example.thread.car.entity.CarEntity;
import com.example.thread.car.vo.CarVO;

import java.util.List;

public interface CarService {
    CarEntity save(CarEntity car);
    List<CarEntity> save(List<CarVO> cars);
    long count();
    void deleteAll();
}
