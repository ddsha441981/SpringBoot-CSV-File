package com.codewithcup.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "hradata")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CsvReader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cardId;
    private String cardTypeFullName;
    private String issuingBank;
    private String cardNumber;
    private String cardHolderName;
    private Long cvv;
    private String issueDate;
    private String expiryDate;
    private String billingDate;
    private Double cardPIN;
    private Double creditLimit;

    public CsvReader(String cardTypeFullName, String issuingBank, String cardNumber, String cardHolderName, Long cvv, String issueDate, String expiryDate, String billingDate, Double cardPIN, Double creditLimit) {
        this.cardTypeFullName = cardTypeFullName;
        this.issuingBank = issuingBank;
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.cvv = cvv;
        this.issueDate = issueDate;
        this.expiryDate = expiryDate;
        this.billingDate = billingDate;
        this.cardPIN = cardPIN;
        this.creditLimit = creditLimit;
    }
}
