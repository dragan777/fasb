package com.fasb.dao;

import com.fasb.model.Customer;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer> {



    @Query("select c from Customer c where lower(c.lastName) like lower(:lastName)")
    List<Customer> findByLastName(String lastName, Sort sort);
}