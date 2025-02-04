package com.fasb.service;


import com.fasb.dao.AccountDao;
import com.fasb.model.Account;
import com.fasb.model.Posting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    AccountDao accountDao;

    @Autowired
    PostingService postingService;

    public Account createAccount(Account account){
        accountDao.save(account);
        return account;
    }

    public List<Account> getAccounts(){
        return  accountDao.findAll();
    }

    public List<Account> getAccountsByCustomerId(int customerId){
        return  accountDao.findByCustomerId(customerId);
    }

    public Posting transferMoneyBetweenAccounts(int accountIDFrom, int accountIdTo, Long transferValue){
        Account accountFrom =  accountDao.findById(accountIDFrom).get();
        Account accountTo = accountDao.findById(accountIdTo).get();
        Posting posting = createPostingFromMoneyTransfer(accountFrom,accountTo, transferValue);
        accountDao.save(accountFrom);
        accountDao.save(accountTo);
        postingService.createPosting(posting);
        return posting;
    }

    public Account getAccountWithHighestBalance(List<Account> accounts){
        Account maxByBalance = accounts
                .stream()
                .min(Comparator.comparing(Account::getBalance)).get();
        return maxByBalance;
    }

    public Posting createPostingFromMoneyTransfer(Account accountFrom, Account accountTo, Long transferValue){
        Posting posting = new Posting();
        accountFrom.setBalance(accountFrom.getBalance() - transferValue);
        accountTo.setBalance(accountTo.getBalance() + transferValue);
        posting.setAccountFrom(accountFrom);
        posting.setAccountTo(accountTo);
        posting.setTransferMoneyValue(transferValue);
        posting.setBookingDate(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        return posting;
    }
}
