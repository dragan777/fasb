package com.fasb.dao;


import com.fasb.model.Account;
import com.fasb.model.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditDao extends JpaRepository<Credit, Integer> {


    @Query("select a from Credit a where a.customer.id  = :customerId")
    List<Credit> findByCustomerId(int customerId);


    @Query("select a from Credit a where a.remainingCreditAmount  <= 0")
    List<Credit> findExceededCredits();
}
