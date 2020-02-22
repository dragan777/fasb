package com.fasb.dao;


import com.fasb.model.Customer;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDao extends AbstractJpaDAO<Customer>{

    public CustomerDao(){
        setClazz(Customer.class );
    }
}
