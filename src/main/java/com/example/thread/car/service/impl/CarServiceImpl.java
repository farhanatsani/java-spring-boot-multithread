package com.example.thread.car.service.impl;

import com.example.thread.car.entity.CarEntity;
import com.example.thread.car.repository.CarRepository;
import com.example.thread.car.service.CarService;
import com.example.thread.car.vo.CarVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    @Override
    public CarEntity save(CarEntity car) {
        return carRepository.save(car);
    }
    @Override
    public List<CarEntity> save(List<CarVO> cars) {
        List<CarEntity> carEntities = cars.stream().map(car ->
            CarEntity.builder()
                    .carBrand(car.getCarBrand())
                    .carType(car.getCarType())
                    .yearProduction(car.getYear())
                    .sellingPrice(car.getSellingPrice().toString())
                    .kmDriven(car.getKmDriven())
                    .fuelType(car.getFuelType())
                    .transmission(car.getTransmission())
                    .vin(car.getVin())
                    .description(car.getDescription().substring(0, 60))
                    .build()
        ).collect(Collectors.toList());
        return carRepository.saveAll(carEntities);
    }

    @Override
    public long count() {
        return carRepository.count();
    }
    public void deleteAll() {
        carRepository.deleteAll();
    }
}
