package com.example.thread.car.controller;

import com.example.thread.car.service.CarEnrichmentService;
import com.example.thread.car.service.CarReadCsvService;
import com.example.thread.car.service.CarService;
import com.example.thread.car.service.impl.CarEnrichmentServiceImpl;
import com.example.thread.car.service.impl.CarReadCsvUsingBufferImpl;
import com.example.thread.car.service.impl.CarServiceImpl;
import com.example.thread.car.vo.CarVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/api/cars")
public class CarController {

    private CarReadCsvService carReadCsvService;
    private CarEnrichmentService carEnrichmentService;
    private CarService carService;
    public CarController(CarReadCsvUsingBufferImpl carReadCsvService,
                         CarEnrichmentServiceImpl carEnrichmentService,
                         CarServiceImpl carService) {

        this.carReadCsvService = carReadCsvService;
        this.carEnrichmentService = carEnrichmentService;
        this.carService = carService;
    }
    @PostMapping
    public ResponseEntity<?> uploadCsvCar(@RequestParam MultipartFile file) {
        long countCars = carService.count();
        if(countCars > 0) {
            carService.deleteAll();
        }
        List<CarVO> carList = carReadCsvService.readCsvCar(file);
        carEnrichmentService.enrichCarDescriptionWithExecutor(carList);
        carService.save(carList);
        return ResponseEntity.ok(carList.size());
    }
}
