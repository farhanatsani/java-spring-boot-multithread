package com.example.thread.car.service.impl;

import com.example.thread.car.callable.EnrichCarCallable;
import com.example.thread.car.service.CarEnrichmentService;
import com.example.thread.car.util.ScrappingUtils;
import com.example.thread.car.vo.CarVO;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service(value = "carEnrichmentService")
public class CarEnrichmentServiceImpl implements CarEnrichmentService {
    @Override
    public List<CarVO> enrichCarDescription(List<CarVO> carList) {
        long start = System.currentTimeMillis();

        for(CarVO carVO : carList) {
            String carWikiSearchKeyword = carVO.getCarBrand() + "_" + carVO.getCarType();
            String content = ScrappingUtils.readWikiContentPage(carWikiSearchKeyword);
            carVO.setDescription(content);
        }

        float elapsed = (System.currentTimeMillis() - start) / 1000F;
        System.out.println("enrich description, elapsed : " + elapsed + " seconds");

        return carList;
    }

    @Override
    public List<CarVO> enrichCarDescriptionFuture(List<CarVO> carList) {
        long start = System.currentTimeMillis();

        ExecutorService e = Executors.newFixedThreadPool(10);

        e.submit(() -> {
            for(CarVO carVO : carList) {
                String carWikiSearchKeyword = carVO.getCarBrand() + "_" + carVO.getCarType();
                String content = ScrappingUtils.readWikiContentPage(carWikiSearchKeyword);
                carVO.setDescription(content);
            }
        });

        float elapsed = (System.currentTimeMillis() - start) / 1000F;
        System.out.println("enrich description using future, elapsed : " + elapsed + " seconds");

        return carList;
    }

    @SneakyThrows
    @Override
    public List<CarVO> enrichCarDescriptionWithExecutor(List<CarVO> carList) {
        long start = System.currentTimeMillis();

        ExecutorService executor = Executors.newFixedThreadPool(10);

        List<EnrichCarCallable> enrichCarCallables = new ArrayList<>();
        for(CarVO carVO : carList) {
            enrichCarCallables.add(new EnrichCarCallable(carVO));
        }

        List<Future<CarVO>> carDTOEnrichmentResult = executor.invokeAll(enrichCarCallables);
        List<CarVO> carVOResults = new ArrayList<>();
        for(Future<CarVO> future: carDTOEnrichmentResult) {
            carVOResults.add(future.get());
        }

        float elapsed = (System.currentTimeMillis() - start) / 1000F;
        System.out.println("enrich elapsed time : " + elapsed + " seconds");
        return carVOResults;
    }
}
