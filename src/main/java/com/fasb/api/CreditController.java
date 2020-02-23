package com.fasb.api;


import com.fasb.api.requests.PayoffCreditReq;
import com.fasb.api.requests.TransferMoneyReq;
import com.fasb.model.Account;
import com.fasb.model.Credit;
import com.fasb.model.Posting;
import com.fasb.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
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

    @GetMapping(value = "/{customerID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Credit> getCreditsForCustomer(@PathVariable("customerID") final int customerID){
        return creditService.getCreditsByCustomerId(customerID);
    }

    @PostMapping(value = "/payoff")
    @Transactional
    public Credit payoffCredit(@RequestBody PayoffCreditReq payoffCreditReq) {
        return creditService.payoffCredit(payoffCreditReq);
    }


    @GetMapping(value = "/exceeded")
    public List<Credit> getExceededCredits(){
        return creditService.getExceededCredits();
    }
}
