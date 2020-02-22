package com.fasb.service;


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



    public Credit createCredit(Credit credit){
        creditDao.save(credit);
        return credit;
    }

    public List<Credit> getCredits(){
        return  creditDao.findAll();
    }
}
