/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.ood.assessmentgroupa7.model.dto;

import java.util.Date;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


/**
 *
 * @author jorandall
 * 
 */

public class CreditCardTransaction {
    
    private String transactionId = UUID.randomUUID().toString();
    private CreditCard fromCard;
    private CreditCard toCard;
    private Date transactionDate = new Date();
    private Double amount;
    private CreditCardTransactionStatus status;
    private String message;

    @OneToOne
    public CreditCard getFromCard() {
        return fromCard;
    }

    public void setFromCard(CreditCard fromCard) {
        this.fromCard = fromCard;
    }

    @OneToOne
    public CreditCard getToCard() {
        return toCard;
    }

    public void setToCard(CreditCard toCard) {
        this.toCard = toCard;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
    
    public CreditCardTransactionStatus getStatus() {
        return status;
    }

    public void setStatus(CreditCardTransactionStatus status) {
        this.status = status;
    }
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    
}
