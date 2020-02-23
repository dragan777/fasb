package com.fasb.dao;


import com.fasb.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountDao extends JpaRepository<Account, Integer> {



    @Query("select a from Account a where a.customer.id  = :customerId")
    List<Account> findByCustomerId(int customerId);
}
