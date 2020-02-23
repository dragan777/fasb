package com.fasb.api;


import com.fasb.api.requests.TransferMoneyReq;
import com.fasb.model.Account;
import com.fasb.model.Posting;
import com.fasb.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RequestMapping("api/v1/accounts")
@RestController
public class AccountController {


    @Autowired
    AccountService accountService;

    @GetMapping
    public List<Account> getAccounts() {
        return accountService.getAccounts();
    }


    @PostMapping(value = "/transfer")
    @Transactional
    public Posting transfer(@RequestBody TransferMoneyReq transferMoneyReq) {
        return accountService.transferMoneyBetweenAccounts(transferMoneyReq.getAccoundIDfrom(), transferMoneyReq.getAccoundIDTo(), transferMoneyReq.getTransferMoneyValue());
    }


    @GetMapping(value = "/{customerID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Account> getAccountsForCustomer(@PathVariable("customerID") final int customerID) {
        return accountService.getAccountsByCustomerId(customerID);
    }


}
