package com.fasb.api;

import com.fasb.dao.CustomerDao;
import com.fasb.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("api/v1/customers")
@RestController
public class CustomerController {

    @Autowired
    CustomerDao customerDao;
    //TODO LIST ALL CUSTOMERS
    @GetMapping
    @Transactional
    public List<String> getAllCustomers(){
        List<String> res = new ArrayList<>();
        res.add("just test");
        Customer customer = new Customer();
        customer.setFirstName("Dragan");
        customer.setLastName("Velkovski");
        customer.setAddress("test");

        customerDao.create(customer);
        return res;
    }


}
