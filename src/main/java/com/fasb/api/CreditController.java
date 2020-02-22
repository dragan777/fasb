package com.fasb.api;


import com.fasb.model.Credit;
import com.fasb.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("api/v1/credits")
@RestController
public class CreditController {



    @Autowired
    CreditService creditService;


    @GetMapping
    public List<Credit> getCredits(){
        return creditService.getCredits();
    }
}
