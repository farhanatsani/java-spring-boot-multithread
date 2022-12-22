package com.example.thread.car;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.concurrent.Callable;

public class EnrichCarCallable implements Callable<CarDTO> {

    private CarDTO carDTO;

    public EnrichCarCallable(CarDTO carDTO) {
        this.carDTO = carDTO;
    }

    @Override
    public CarDTO call() throws Exception {
        String carWikiSearchKeyword = carDTO.getCarBrand() + "_" + carDTO.getCarType();
        String content = readWikiContentPage(carWikiSearchKeyword);
        carDTO.setDescription(content);
        return carDTO;
    }

    private String readWikiContentPage(String carName) {
        String text = "";
        try {
            Document doc = Jsoup
                    .connect("https://en.wikipedia.org/wiki/" + carName)
                    .get();
            text = doc.body().text();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

}
