package com.example.thread.mstb;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class MstbController {

    private MstbService mstbService;

    public MstbController(MstbService mstbService) {
        this.mstbService = mstbService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {

//        mstbService.readXlsData(file);

        List<MstbEntity> mstbList = mstbService.readCsvMstbUsingBuffer(file);
        mstbService.saveAll(mstbList);

        return ResponseEntity.status(HttpStatus.OK)
                .body("Upload " + file.getOriginalFilename() + " file success."
                );

    }

}
