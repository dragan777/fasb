package com.fasb.dao;


import com.fasb.model.Account;
import com.fasb.model.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditDao extends JpaRepository<Credit, Integer> {
}
