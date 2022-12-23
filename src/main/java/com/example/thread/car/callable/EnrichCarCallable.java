package com.example.thread.car.callable;

import com.example.thread.car.util.ScrappingUtils;
import com.example.thread.car.vo.CarVO;

import java.util.concurrent.Callable;

public class EnrichCarCallable implements Callable<CarVO> {
    private CarVO carVO;
    public EnrichCarCallable(CarVO carVO) {
        this.carVO = carVO;
    }
    @Override
    public CarVO call() throws Exception {
        String carWikiSearchKeyword = carVO.getCarBrand() + "_" + carVO.getCarType();
        String content = ScrappingUtils.readWikiContentPage(carWikiSearchKeyword);
        carVO.setDescription(content);
        return carVO;
    }

}
