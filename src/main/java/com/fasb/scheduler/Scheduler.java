package com.fasb.scheduler;


import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import com.fasb.api.requests.PayoffCreditReq;
import com.fasb.model.Account;
import com.fasb.model.Credit;
import com.fasb.service.AccountService;
import com.fasb.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

    @Autowired
    CreditService creditService;

    @Autowired
    AccountService accountService;


    @Scheduled(cron="${cronExpressionForAutoBooking}")
    public void makeAutoPayoffForCredit() {
        //TODO REFACTOR IMPROVE WHOLE CODE AND WHOLE LOGIC HERE
        List<Credit> credits = creditService.getCredits();
        credits.forEach((credit)->{
            Account accountToMakePayoff = accountService.getAccountWithHighestBalance(accountService.getAccountsByCustomerId(credit.getId()));
            //TODO ADD ADD MIN MONTHLY INSTALLMENT IN CREDIT
            //AT THIS MOMENT WE JUST USE 30
            creditService.payoffCredit(accountToMakePayoff, credit, 30l);
        });


    }
}