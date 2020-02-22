package com.fasb.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("api/v1/customers")
@RestController
public class CustomerController {


    //TODO LIST ALL CUSTOMERS
    @GetMapping
    private List<String> getAllCustomers(){
        List<String> res = new ArrayList<>();
        res.add("just test");
        return res;
    }


}
