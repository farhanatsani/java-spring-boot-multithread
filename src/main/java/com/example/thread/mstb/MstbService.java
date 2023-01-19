package com.example.thread.mstb;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MstbService {

    private MstbRepository mstbRepository;

    public MstbService(MstbRepository mstbRepository) {
        this.mstbRepository = mstbRepository;
    }

    @SneakyThrows
    public String readXlsData(MultipartFile file) {
//        Workbook workbook = new XSSFWorkbook(file.getInputStream()); // Unexpected record signature: 0X7974733C
        Workbook workbook = new HSSFWorkbook(file.getInputStream()); // Invalid header signature; read 0x743E656C7974733C, expected 0xE11AB1A1E011CFD0 - Your file appears not to be a valid OLE2 document
        return null;
    }

    @SneakyThrows
    public List<MstbEntity> readCsvMstbUsingBuffer(MultipartFile file) {

        long start = System.currentTimeMillis();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream()));

        List<MstbEntity> mstbList = new ArrayList<>();

        String line;
        StringBuffer contents = new StringBuffer();
        while((line = bufferedReader.readLine()) != null) {
            contents.append(line);
        }
        mstbList.addAll(mapContentToMstb(contents.toString()));

        log.info("data size {}", mstbList.size());
        for(MstbEntity mstb: mstbList) {
            System.out.println(mstb.toString());
        }

        float elapsed = (System.currentTimeMillis() - start) / 1000F;
        System.out.println("read csv with buffer, time : " + elapsed + " seconds");
        return mstbList;
    }

    private List<MstbEntity> mapContentToMstb(String contents) {
        Document doc = Jsoup.parse(contents);
        Elements table = doc.getElementsByTag("table");
        Elements tr = table.select("tr");

        List<MstbEntity> mstbEntities = new ArrayList<>();
        for (Element e : tr.next()) {
            mstbEntities.add(MstbEntity.builder()
                    .branchId(e.select("td").get(0).text())
                    .branchName(e.select("td").get(1).text())
                    .cabangBpr(e.select("td").get(2).text())
                    .areaNameId(e.select("td").get(3).text())
                    .areaName(e.select("td").get(4).text())
                    .zipCode(e.select("td").get(5).text())
                    .objGroupDesc(e.select("td").get(6).text())
                    .objectId(e.select("td").get(7).text())
                    .job(e.select("td").get(8).text())
                    .pic(e.select("td").get(9).text())
                    .emailCrh(e.select("td").get(10).text())
                    .ccEmail1(e.select("td").get(11).text())
                    .ccEmail2(e.select("td").get(12).text())
                    .ccEmail3(e.select("td").get(13).text())
                    .build());
        }
        return mstbEntities;
    }

    public void saveAll(List<MstbEntity> mstbList) {
        mstbRepository.saveAll(mstbList);
    }

}
