package com.fasb.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
public class Posting extends BaseEntity {

    @NotNull
    private LocalDateTime bookingDate;


    @ManyToOne
    private Account accountFrom;

    @ManyToOne
    private Account accountTo;

    private Long transferMoneyValue;

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Account getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(Account accountIdFrom) {
        this.accountFrom = accountIdFrom;
    }

    public Account getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(Account accountIdTo) {
        this.accountTo = accountIdTo;
    }

    public Long getTransferMoneyValue() {
        return transferMoneyValue;
    }

    public void setTransferMoneyValue(Long transferMoneyValue) {
        this.transferMoneyValue = transferMoneyValue;
    }
}
