package com.fasb.api;


import com.fasb.model.Customer;
import com.fasb.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RequestMapping("api/v1/customers")
@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;


    @GetMapping
    public List<Customer> getAllCustomers(@RequestParam(required = false) String lastName, @RequestParam(required = false, defaultValue = "firstName") String sortBy, @RequestParam(defaultValue = "asc", required = false) String sortDir){
        Sort.Direction sortDirection = Sort.Direction.ASC;
        if(sortDir.equals("desc")){
            sortDirection = Sort.Direction.DESC;
        }
        if(lastName != null){
            return customerService.getCustomersByLastName(lastName,sortBy, sortDirection);
        }else{
            return customerService.getCustomers(sortBy, sortDirection);
        }
    }



    @PostMapping(value = "/create")
    @Transactional
    public Customer create(@RequestBody Customer customer){
        customerService.createCustomer(customer);
        return customer;
    }



}
