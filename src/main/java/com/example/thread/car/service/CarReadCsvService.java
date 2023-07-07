package com.example.thread.car.service;

import com.example.thread.car.vo.CarVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CarReadCsvService {
    List<CarVO> readCsvCar(String fileName);
    List<CarVO> readCsvCar(MultipartFile file);
}
