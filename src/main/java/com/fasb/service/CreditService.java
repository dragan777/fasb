package com.fasb.service;


import com.fasb.api.requests.PayoffCreditReq;
import com.fasb.dao.AccountDao;
import com.fasb.dao.CreditDao;
import com.fasb.model.Account;
import com.fasb.model.Credit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditService {

    @Autowired
    CreditDao creditDao;


    @Autowired
    AccountDao accountDao;


    public Credit createCredit(Credit credit){
        creditDao.save(credit);
        return credit;
    }

    public List<Credit> getCredits(){
        return  creditDao.findAll();
    }

    public List<Credit> getCreditsByCustomerId(int customerId){
        return  creditDao.findByCustomerId(customerId);
    }

    public Credit payoffCredit(PayoffCreditReq payoffCreditReq){
        Credit credit = creditDao.findById(payoffCreditReq.getCreditID()).get();
        Account account = accountDao.findById(payoffCreditReq.getAccointID()).get();
        return  payoffCredit(account, credit, payoffCreditReq.getSumToPayoff());
    }

    public Credit payoffCredit(Account account, Credit credit, Long sumToPayoff){
        credit.setRemainingCreditAmount(credit.getOriginalCreditAmount() - sumToPayoff);
        credit.setRemainingTerm(credit.getOriginalTerm() -1);
        account.setBalance(account.getBalance() - sumToPayoff);
        accountDao.save(account);
        creditDao.save(credit);
        return credit;
    }
    public List<Credit> getExceededCredits(){
        return  creditDao.findExceededCredits();
    }

}
