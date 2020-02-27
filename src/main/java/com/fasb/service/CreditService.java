package com.fasb.service;


import com.fasb.api.requests.PayoffCreditReq;
import com.fasb.dao.AccountDao;
import com.fasb.dao.CreditDao;
import com.fasb.dao.CustomerDao;
import com.fasb.model.Account;
import com.fasb.model.Credit;
import com.fasb.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CreditService {

    @Autowired
    CreditDao creditDao;


    @Autowired
    AccountDao accountDao;


    @Autowired
    CustomerDao customerDao;

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
        credit.setRemainingCreditAmount(credit.getRemainingCreditAmount() - sumToPayoff);
        credit.setRemainingTerm(credit.getRemainingTerm() -1);
        account.setBalance(account.getBalance() - sumToPayoff);
        accountDao.save(account);
        creditDao.save(credit);
        Customer customer = credit.getCustomer();

        if(credit.getRemainingTerm() <= 0 && customer.getCreditClass() > 1){
            credit.getCustomer().setCreditClass(customer.getCreditClass() - 1);
            customerDao.save(customer);
        }
        if(credit.getExpirationDate().isBefore(LocalDateTime.now()) && customer.getCreditClass() <= 4){
            credit.getCustomer().setCreditClass(customer.getCreditClass() + 1);
            customerDao.save(customer);
        }
        return credit;
    }
    public List<Credit> getExceededCredits(){
        return  creditDao.findExceededCredits();
    }

}
