package com.example.thread.car;

import lombok.SneakyThrows;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

@Service
public class CarService {

    public List<CarDTO> readCsvUsingBuffer(String fileName) {
        long start = System.currentTimeMillis();
        List<CarDTO> carList = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

            String line;
            while((line = bufferedReader.readLine()) != null) {
                 String[] tempLine = line.split(",");

                String brand = tempLine[0];
                if(brand.equalsIgnoreCase("Skoda")) {
                    brand = "Škoda";
                } else if(brand.equalsIgnoreCase("Chevy")) {
                    brand = "Chevrolet";
                } else if(brand.equalsIgnoreCase("Xpeng")) {
                    brand = "XPeng";
                }

                String type = tempLine[1];
                if(type.equalsIgnoreCase("Model")) {
                    type = "Model Y";
                } else if(type.equalsIgnoreCase("G3i")) {
                    type = "G3";
                } else if(type.equalsIgnoreCase("ES7")) {
                    type = "ET7";
                } else if(type.equalsIgnoreCase("Camero")) {
                    type = "Camaro";
                } else if(type.equalsIgnoreCase("Riveria")) {
                    type = "Riviera";
                } else if(type.equalsIgnoreCase("IONIQ")) {
                    type = "Ioniq";
                } else if(type.equalsIgnoreCase("Pathfiner")) {
                    type = "Pathfinder";
                }

                CarDTO carDTO = CarDTO.builder()
                        .carBrand(brand)
                        .carType(type)
                        .year(tempLine[2])
                        .sellingPrice(new BigDecimal(tempLine[3]))
                        .kmDriven(tempLine[4])
                        .fuelType(tempLine[5])
                        .transmission(tempLine[6])
                        .vin(tempLine[7])
                        .build();
                carList.add(carDTO);
            }
            bufferedReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        float elapsed = (System.currentTimeMillis() - start) / 1000F;
        System.out.println("read csv time : " + elapsed + " seconds");
        return carList;
    }

    @SneakyThrows
    public List<CarDTO> readCsvDummyCarData(String fileName) {
        long start = System.currentTimeMillis();
        Reader in = new FileReader(fileName);
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
        List<CarDTO> carList = new ArrayList<>();
        for (CSVRecord record : records) {

            String brand = record.get(0);
            if(brand.equalsIgnoreCase("Skoda")) {
                brand = "Škoda";
            } else if(brand.equalsIgnoreCase("Chevy")) {
                brand = "Chevrolet";
            } else if(brand.equalsIgnoreCase("Xpeng")) {
                brand = "XPeng";
            }

            String type = record.get(1);
            if(type.equalsIgnoreCase("Model")) {
                type = "Model Y";
            } else if(type.equalsIgnoreCase("G3i")) {
                type = "G3";
            } else if(type.equalsIgnoreCase("ES7")) {
                type = "ET7";
            } else if(type.equalsIgnoreCase("Camero")) {
                type = "Camaro";
            } else if(type.equalsIgnoreCase("Riveria")) {
                type = "Riviera";
            } else if(type.equalsIgnoreCase("IONIQ")) {
                type = "Ioniq";
            } else if(type.equalsIgnoreCase("Pathfiner")) {
                type = "Pathfinder";
            }

            CarDTO carDTO = CarDTO.builder()
                    .carBrand(brand)
                    .carType(type)
                    .year(record.get(2))
                    .sellingPrice(new BigDecimal(record.get(3)))
                    .kmDriven(record.get(4))
                    .fuelType(record.get(5))
                    .transmission(record.get(6))
                    .vin(record.get(7))
                    .build();
            carList.add(carDTO);
        }
        float elapsed = (System.currentTimeMillis() - start) / 1000F;
        System.out.println(elapsed + " seconds");
        return carList;
    }

    public String readWikiContentPage(String carName) {
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

    public List<CarDTO> enrichCarDescriptionFuture(List<CarDTO> carList) {
        long start = System.currentTimeMillis();

        ExecutorService e = Executors.newFixedThreadPool(10);

//        e.invokeAll()

        e.submit(() -> {
            for(CarDTO carDTO: carList) {
                String carWikiSearchKeyword = carDTO.getCarBrand() + "_" + carDTO.getCarType();
                String content = readWikiContentPage(carWikiSearchKeyword);
                carDTO.setDescription(content);
            }
        });


        float elapsed = (System.currentTimeMillis() - start) / 1000F;
        System.out.println("enrich elapsed : " + elapsed + " seconds");
        return carList;
    }

    public List<CarDTO> enrichCarDescription(List<CarDTO> carList) {
        long start = System.currentTimeMillis();
        for(CarDTO carDTO: carList) {
            String carWikiSearchKeyword = carDTO.getCarBrand() + "_" + carDTO.getCarType();
            String content = readWikiContentPage(carWikiSearchKeyword);
            carDTO.setDescription(content);
        }
        float elapsed = (System.currentTimeMillis() - start) / 1000F;
        System.out.println("enrich elapsed : " + elapsed + " seconds");
        return carList;
    }

    public List<CarDTO> enrichCarDescriptionWithExecutor(List<CarDTO> carList) throws ExecutionException, InterruptedException {
        List<CarDTO> carDTOResults = new ArrayList<>();
        long start = System.currentTimeMillis();
        ExecutorService executor = Executors.newFixedThreadPool(10);

//        for(CarDTO carDTO: carList) {
//            Future<CarDTO> carDTOFuture = executor.submit(new EnrichCarCallable(carDTO));
//            carDTO.setDescription(carDTOFuture.get().getDescription());
//        }
//        executor.shutdown();

        List<EnrichCarCallable> enrichCarCallables = new ArrayList<>();
        for(CarDTO carDTO: carList) {
            enrichCarCallables.add(new EnrichCarCallable(carDTO));
        }

        List<Future<CarDTO>> carDTOEnrichmentResult = executor.invokeAll(enrichCarCallables);
        for(Future<CarDTO> future: carDTOEnrichmentResult) {
            carDTOResults.add(future.get());
        }

        float elapsed = (System.currentTimeMillis() - start) / 1000F;
        System.out.println("enrich elapsed time : " + elapsed + " seconds");
        return carDTOResults;
    }

}
