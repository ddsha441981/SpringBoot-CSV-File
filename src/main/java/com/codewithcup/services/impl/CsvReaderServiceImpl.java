package com.codewithcup.services.impl;

import com.codewithcup.config.CsvHelper;
import com.codewithcup.entity.CsvReader;
import com.codewithcup.repository.CsvReaderRepository;
import com.codewithcup.services.CsvReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Service
public class CsvReaderServiceImpl implements CsvReaderService {
    @Autowired
    private CsvReaderRepository csvReaderRepository;

    @Override
    public void saveFile(MultipartFile file) {
        try{
            List<CsvReader> csvReaderList = CsvHelper.csvToExcel(file.getInputStream());
            csvReaderRepository.saveAll(csvReaderList);
        }catch (IOException e){
            throw new RuntimeException("Fail to store csv data:" + e.getMessage());
        }
    }

    @Override
    public List<CsvReader> getAllCsvData() {
        return csvReaderRepository.findAll();
    }
}
