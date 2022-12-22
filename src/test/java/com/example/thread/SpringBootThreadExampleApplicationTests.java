package com.example.thread;

import com.example.thread.car.EnrichCarCallable;
import com.example.thread.car.CarDTO;
import com.example.thread.car.CarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@SpringBootTest
class SpringBootThreadExampleApplicationTests {

	@Autowired
	private CarService carService;

	@Test
	void contextLoads() {
		List<CarDTO> carList = carService.readCsvDummyCarData("car_result.csv");
		for(CarDTO carDTO: carList) {
			System.out.println(carDTO.toString());
		}
	}

//	@Test
	void testJsoup() {
		List<CarDTO> carList = carService.readCsvDummyCarData("car_result.csv");
		for(CarDTO carDTO: carList) {
			String carWikiSearchKeyword = carDTO.getCarBrand() + "_" + carDTO.getCarType();
			String content = carService.readWikiContentPage(carWikiSearchKeyword);
			System.out.println(carWikiSearchKeyword);
			System.out.println(content);
		}
	}

	@Test
	void testEnrichDescription() {
		List<CarDTO> carList = carService.readCsvDummyCarData("car_result.csv");
		carService.enrichCarDescription(carList);
		for(int i = 0; i < 5; i++) {
			System.out.println(carList.get(i).toString());
		}
	}

	@Test
	void testReadCsvUsingChunks() {
		List<CarDTO> carList = carService.readCsvUsingBuffer("car_result.csv");
		for(CarDTO carDTO: carList) {
			System.out.println(carDTO.toString());
		}
	}

	@Test
	void testEnrichDescriptionUsingFuture() throws ExecutionException, InterruptedException {
		List<CarDTO> carList = carService.readCsvUsingBuffer("car_result.csv");
		carService.enrichCarDescriptionWithExecutor(carList);
		for(int i = 0; i < 5; i++) {
			System.out.println(carList.get(i).toString());
		}
	}


}
