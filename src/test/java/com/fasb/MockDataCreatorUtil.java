package com.fasb;

import com.fasb.model.Account;
import com.fasb.model.Credit;
import com.fasb.model.Customer;
import com.fasb.model.Posting;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;

public class MockDataCreatorUtil {




    public static  Customer createMockCustomer(TestEntityManager entityManager){
        Customer mockCustomer = new Customer("alex", "doe", "", 2);
        entityManager.persist(mockCustomer);
        entityManager.flush();
        return mockCustomer;
    }

    public static Credit createMockCreditForCustomer(TestEntityManager entityManager, Customer customer, boolean setExpiredDate){
        Credit mockCredit = new Credit();
        mockCredit.setRemainingTerm(10);
        mockCredit.setCustomer(customer);
        mockCredit.setOriginalCreditAmount(1000l);
        mockCredit.setRemainingCreditAmount(1000l);
        mockCredit.setOriginalTerm(10);

        //force mock credit with expiration date
        if(setExpiredDate){
            mockCredit.setExpirationDate(LocalDateTime.now().minusMonths(10));
        }else{
            mockCredit.setExpirationDate(LocalDateTime.now().plusMonths(10));
        }
        entityManager.persist(mockCredit);
        entityManager.flush();
        return mockCredit;
    }


    public static Posting createMockPosting(TestEntityManager entityManager, Account accountFrom, Account accountTo, LocalDateTime bookingDate){
        Posting mockPosting = new Posting();
        mockPosting.setTransferMoneyValue(200l);
        mockPosting.setAccountFrom(accountFrom);
        mockPosting.setAccountFrom(accountTo);
        mockPosting.setBookingDate(bookingDate);
        entityManager.persist(mockPosting);
        entityManager.flush();
        return mockPosting;
    }

    public static Account createMockAccount(TestEntityManager entityManager, Customer customer, Long balance){
        Account account = new Account();
        account.setBalance(balance);
        account.setCustomer(customer);
        entityManager.persist(account);
        entityManager.flush();
        return account;
    }
}
