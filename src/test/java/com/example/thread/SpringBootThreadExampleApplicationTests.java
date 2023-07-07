package com.example.thread;

import com.example.thread.car.service.CarEnrichmentService;
import com.example.thread.car.service.CarReadCsvService;
import com.example.thread.car.util.ScrappingUtils;
import com.example.thread.car.vo.CarVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.*;

@SpringBootTest
class SpringBootThreadExampleApplicationTests {
	@Autowired
	@Qualifier(value = "carReadCsvService")
	private CarReadCsvService carReadCsvService;

	@Autowired
	@Qualifier(value = "carReadCsvUsingBufferService")
	private CarReadCsvService carReadCsvUsingBufferService;

	@Autowired
	@Qualifier(value = "carEnrichmentService")
	private CarEnrichmentService carEnrichmentService;
	@Test
	void testReadCsv() {
		List<CarVO> carList = carReadCsvService.readCsvCar("car_result.csv");
		for(CarVO carVO : carList) {
			System.out.println(carVO.toString());
		}
	}
	@Test
	void testReadCsvUsingBuffer() {
		List<CarVO> carList = carReadCsvUsingBufferService.readCsvCar("car_result.csv");
		for(CarVO carVO : carList) {
			System.out.println(carVO.toString());
		}
	}
	@Test
	void testWebScrapping() {
		List<CarVO> carList = carReadCsvUsingBufferService.readCsvCar("car_result.csv");
		for(CarVO carVO : carList) {
			String carWikiSearchKeyword = carVO.getCarBrand() + "_" + carVO.getCarType();
			String content = ScrappingUtils.readWikiContentPage(carWikiSearchKeyword);
			System.out.println(carWikiSearchKeyword);
			System.out.println(content);
		}
	}
	@Test
	void testEnrichDescription() {
		List<CarVO> carList = carReadCsvUsingBufferService.readCsvCar("car_result.csv");
		carEnrichmentService.enrichCarDescription(carList);
//		for(int i = 0; i < carList.size(); i++) {
//			System.out.println(carList.get(i).toString());
//		}
	}
	@Test
	void testEnrichDescriptionUsingFuture() {
		List<CarVO> carList = carReadCsvUsingBufferService.readCsvCar("car_result.csv");
		carEnrichmentService.enrichCarDescriptionWithExecutor(carList);
//		for(int i = 0; i < carList.size(); i++) {
//			System.out.println(carList.get(i).toString());
//		}
	}
}
