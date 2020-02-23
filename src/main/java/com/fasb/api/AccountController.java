package com.fasb.api;


import com.fasb.api.requests.TransferMoneyReq;
import com.fasb.model.Account;
import com.fasb.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RequestMapping("api/v1/accounts")
@RestController
public class AccountController {


    @Autowired
    AccountService accountService;

    @GetMapping
    public List<Account> getAccounts(){
        return accountService.getAccounts();
    }



    @PostMapping(value = "/transfer")
    @Transactional
    public Long transfer(@RequestBody TransferMoneyReq transferMoneyReq){
        return accountService.transferMoneyBetweenAccounts(transferMoneyReq.getAccoundIDfrom(), transferMoneyReq.getAccoundIDTo(), transferMoneyReq.getTransferMoneyValue());
    }


}
