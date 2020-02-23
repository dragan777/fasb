package com.fasb.model;


import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Credit  extends BaseEntity{


    //months
    private int originalTerm;

    private int remainingTerm;

    private Long originalCreditAmount;

    private Long remainingCreditAmount;

    private LocalDateTime expirationDate;

    @NotNull
    @ManyToOne
    private Customer customer;

    public int getOriginalTerm() {
        return originalTerm;
    }

    public void setOriginalTerm(int originalTerm) {
        this.originalTerm = originalTerm;
    }

    public int getRemainingTerm() {
        return remainingTerm;
    }

    public void setRemainingTerm(int remainingTerm) {
        this.remainingTerm = remainingTerm;
    }

    public Long getOriginalCreditAmount() {
        return originalCreditAmount;
    }

    public void setOriginalCreditAmount(Long originalCreditAmount) {
        this.originalCreditAmount = originalCreditAmount;
    }

    public Long getRemainingCreditAmount() {
        return remainingCreditAmount;
    }

    public void setRemainingCreditAmount(Long remainingCreditAmount) {
        this.remainingCreditAmount = remainingCreditAmount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Credit() {
    }

    public Credit(int originalTerm, int remainingTerm, Long originalCreditAmount, Long remainingCreditAmount, Customer customer, LocalDateTime expirationDate) {
        this.originalTerm = originalTerm;
        this.remainingTerm = remainingTerm;
        this.originalCreditAmount = originalCreditAmount;
        this.remainingCreditAmount = remainingCreditAmount;
        this.customer = customer;
        this.expirationDate = expirationDate;
    }

}
