package com.fasb.service;


import com.fasb.dao.AccountDao;

import com.fasb.model.Account;
import com.fasb.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    AccountDao accountDao;

    public Long transferMoneyBetweenAccounts(int accountIDFrom, int accountIdTo, int transferValue){
        Account accountFrom =  accountDao.findById(accountIDFrom).get();
        Account accountTo = accountDao.findById(accountIdTo).get();

        accountFrom.setBalance(accountFrom.getBalance() - transferValue);
        accountTo.setBalance(accountTo.getBalance() + transferValue);
        accountDao.save(accountFrom);
        accountDao.save(accountTo);
        return 1l;
    }

    public Account createAccount(Account account){
        accountDao.save(account);
        return account;
    }

    public List<Account> getAccounts(){
        return  accountDao.findAll();
    }
}
