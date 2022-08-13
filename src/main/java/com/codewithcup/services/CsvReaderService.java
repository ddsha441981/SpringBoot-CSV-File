package com.codewithcup.services;

import com.codewithcup.entity.CsvReader;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CsvReaderService {
    public void saveFile(MultipartFile file);
    public List<CsvReader> getAllCsvData();
}
