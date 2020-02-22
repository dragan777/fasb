package com.fasb.api;


import com.fasb.api.requests.AccountCreationReq;
import com.fasb.api.requests.TransferMoneyReq;
import com.fasb.model.Account;
import com.fasb.model.Customer;
import com.fasb.service.AccountService;
import com.fasb.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RequestMapping("api/v1/accounts")
@RestController
public class AccountController {


    @Autowired
    AccountService accountService;



    @Autowired
    CustomerService customerService;


    @PostMapping(value = "/create")
    @Transactional
    public Account create(@RequestBody AccountCreationReq accountCreationReq){

        Account account = new Account();
        Customer customer = customerService.getCustomerById(accountCreationReq.getCustomerID());
        account.setBalance(accountCreationReq.getBalance());
        account.setCustomer(customer);
        accountService.createAccount(account);
        return account;
    }


    @GetMapping
    public List<Account> getAccounts(){
        return accountService.getAccounts();
    }


    @PostMapping(value = "/transfer")
    @Transactional
    public Long create(@RequestBody TransferMoneyReq transferMoneyReq){
        return accountService.transferMoneyBetweenAccounts(transferMoneyReq.getAccoundIDfrom(), transferMoneyReq.getAccoundIDTo(), transferMoneyReq.getTransferMoneyValue());
    }
}
