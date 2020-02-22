package com.fasb.model;


import com.fasterxml.jackson.databind.ser.Serializers;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Account extends BaseEntity {



    @NotNull
    private Long balance;

    @NotNull
    @ManyToOne
    private Customer customer;

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }



    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
