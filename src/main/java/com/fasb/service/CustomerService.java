package com.fasb.service;

import com.fasb.dao.CustomerDao;
import com.fasb.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CustomerService {



    private final CustomerDao customerDao;

    @Autowired
    public CustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;

    }

    public Customer createCustomer(Customer customer){

        customerDao.save(customer);
        return customer;
    }

    public List<Customer> getCustomers( String orderPropName, Sort.Direction sortDirection){
        return  customerDao.findAll(Sort.by(sortDirection, orderPropName));
    }

    public List<Customer> getCustomersByLastName(String lastName, String orderPropName, Sort.Direction sortDirection){
        return  customerDao.findByLastName(lastName, Sort.by(sortDirection, orderPropName));
    }
}
