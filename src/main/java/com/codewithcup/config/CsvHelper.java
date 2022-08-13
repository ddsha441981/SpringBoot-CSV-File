package com.codewithcup.config;

import com.codewithcup.entity.CsvReader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CsvHelper {
    public static String TYPE = "text/csv";
    static String[] HEADERs = {"CardTypeFullName","IssuingBank","CardNumber","CardHolderName","CVV","IssueDate","ExpiryDate","BillingDate","CardPIN","CreditLimit"};
    public static boolean hasCSVFormat(MultipartFile file){
        if (!TYPE.equals(file.getContentType())){
            return false;
        }
        return true;
    }

    public static List<CsvReader> csvToExcel(InputStream is){
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
            List<CsvReader> tutorials = new ArrayList<CsvReader>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord csvRecord : csvRecords) {
                CsvReader csvReader = new CsvReader(
//                        Long.parseLong(csvRecord.get("cardId")),
                        csvRecord.get("cardTypeFullName"),
                        csvRecord.get("issuingBank"),
                        csvRecord.get("cardNumber"),
                        csvRecord.get("cardHolderName"),
                        Long.parseLong(csvRecord.get("cvv")),
                        csvRecord.get("issueDate"),
                        csvRecord.get("expiryDate"),
                        csvRecord.get("billingDate"),
                        Double.parseDouble(csvRecord.get("cardPIN")),
                        Double.parseDouble(csvRecord.get("creditLimit"))
                );
                tutorials.add(csvReader);
            }
            return tutorials;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }
    }
