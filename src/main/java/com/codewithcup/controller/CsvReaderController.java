package com.codewithcup.controller;


import com.codewithcup.config.CsvHelper;
import com.codewithcup.entity.CsvReader;
import com.codewithcup.helper.ResponseMessage;
import com.codewithcup.services.CsvReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/doc/csv")
public class CsvReaderController {
    @Autowired
    private CsvReaderService csvReaderService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        if (CsvHelper.hasCSVFormat(file)) {
            try {
                csvReaderService.saveFile(file);
                message = "Upload the file Successfully:" + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }
        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

    @GetMapping("/data")
    public ResponseEntity<List<CsvReader>> getAllTutorials() {
        try {
            List<CsvReader> tutorials = csvReaderService.getAllCsvData();
            if (tutorials.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
