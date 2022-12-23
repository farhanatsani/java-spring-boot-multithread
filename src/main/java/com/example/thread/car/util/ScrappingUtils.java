package com.example.thread.car.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class ScrappingUtils {

    private ScrappingUtils() {}

    public static String readWikiContentPage(String carName) {
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
