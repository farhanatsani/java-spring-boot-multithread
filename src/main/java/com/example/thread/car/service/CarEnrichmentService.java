package com.example.thread.car.service;

import com.example.thread.car.vo.CarVO;

import java.util.List;

public interface CarEnrichmentService {
    List<CarVO> enrichCarDescription(List<CarVO> carList);
    List<CarVO> enrichCarDescriptionFuture(List<CarVO> carList);
    List<CarVO> enrichCarDescriptionWithExecutor(List<CarVO> carList);
}
